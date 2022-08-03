package wutian.coppercraft.menus;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import wutian.coppercraft.blocks.ModBlocks;
import wutian.coppercraft.items.ModItemRegistryHandler;
import wutian.coppercraft.sounds.ModSoundEvents;
import wutian.coppercraft.util.ModTags;

public class ReductionMenu extends ItemCombinerMenu {
    private final Level level;
    private final ContainerLevelAccess access;
    private final Player player;



    public ReductionMenu(int pContainerId, Inventory pPlayerInventory) {
        this(pContainerId, pPlayerInventory, ContainerLevelAccess.NULL);
    }
    public ReductionMenu(int pContainerId, Inventory pPlayerInventory,ContainerLevelAccess pAccess) {
        super(ModMenuType.REDUCTION_TABLE.get(), pContainerId,pPlayerInventory,pAccess);
        this.level = pPlayerInventory.player.getLevel();
        this.access = pAccess;
        this.player = pPlayerInventory.player;
    }

    public void slotsChanged(Container pInventory) {
        if (pInventory.getItem(1).is(ModItemRegistryHandler.DERUSTING_SHOVEL.get()) && !pInventory.getItem(0).is(Items.AIR)) {
            this.createResult();
        }
        else
            resultSlots.setItem(0,new ItemStack(Items.AIR));
        //LogUtils.getLogger().debug(String.valueOf(inputSlots));
    }

    @Override
    protected boolean mayPickup(Player pPlayer, boolean pHasStack) {
        //LogUtils.getLogger().debug(String.valueOf(this.resultSlots.getItem(0).is(ModTags.Items.COPPER_EQUIPMENTS)));
        return this.resultSlots.getItem(0).is(ModTags.Items.COPPER_EQUIPMENTS);
        //return true;
    }

    protected void onTake(Player p_150663_, ItemStack p_150664_) {
//        LogUtils.getLogger().debug(String.valueOf("onTake"));
//        p_150664_.onCraftedBy(p_150663_.level, p_150663_, p_150664_.getCount());
//        this.resultSlots.awardUsedRecipes(p_150663_);
        //LogUtils.getLogger().debug(String.valueOf(inputSlots.getItem(1).getDamageValue()));
        if(!this.inputSlots.getItem(1).getOrCreateTag().contains("Unbreakable") || !(inputSlots.getItem(1).getOrCreateTag().getInt("Unbreakable") == 1))
        {
            if(this.inputSlots.getItem(0).is(ModTags.Items.DAMAGE_WHOLE_SHOVEL))
            {
                shrinkStackInSlot(1);
                level.playLocalSound(player.getOnPos().getX(),player.getOnPos().getY(),player.getOnPos().getZ(),SoundEvents.ITEM_BREAK, SoundSource.BLOCKS,1,1,true);
            }
            else if(inputSlots.getItem(1).getDamageValue() == inputSlots.getItem(1).getMaxDamage()-1)
            {
                shrinkStackInSlot(1);
                level.playLocalSound(player.getOnPos().getX(),player.getOnPos().getY(),player.getOnPos().getZ(),SoundEvents.ITEM_BREAK, SoundSource.BLOCKS,1,1,true);
            }
            else
            {
                inputSlots.getItem(1).setDamageValue(inputSlots.getItem(1).getDamageValue()+1);
            }
        }
        shrinkStackInSlot(0);
//        this.access.execute((p_40263_, p_40264_) -> {
//            p_40263_.levelEvent(1042, p_40264_, 0);
//        });
        player.playSound(ModSoundEvents.REDUCING_WORKING.get(),1,1);
    }

    private void shrinkStackInSlot(int pIndex) {
        ItemStack itemstack = this.inputSlots.getItem(pIndex);
        itemstack.shrink(1);
        this.inputSlots.setItem(pIndex, itemstack);
    }

    /**
     * called when the Anvil Input Slot changes, calculates the new result and puts it in the output slot
     */
    public void createResult() {
        judgeAndReplaceResultItem();
    }

    public void replaceAndWithCopyTagButNotCorrosion(ItemStack input,ItemStack toReplace)
    {
        CompoundTag compoundTag = input.getOrCreateTag().copy();
        if(compoundTag.contains("theDegreeOfCorrosion")) compoundTag.putInt("theDegreeOfCorrosion",0);
        toReplace.setTag(compoundTag);
    }
    public void judgeAndReplaceResultItem()
    {
        if(inputSlots.getItem(0).is(ModItemRegistryHandler.COPPER_SWORD.get()))
        {
            if(inputSlots.getItem(0).getOrCreateTag().contains("CustomModelData"))
            {
                switch (inputSlots.getItem(0).getOrCreateTag().getInt("CustomModelData"))
                {
                    case 1,2,3 -> {
                        ItemStack stack = new ItemStack(ModItemRegistryHandler.COPPER_SWORD.get());
                        stack.setTag(inputSlots.getItem(0).getOrCreateTag().copy());
                        stack.getTag().putInt("CustomModelData",0);
                        stack.getTag().putInt("theDegreeOfCorrosion",0);
                        resultSlots.setItem(0,stack);
                    }
                }
            }
            else
                inputSlots.getItem(0).getTag().putInt("CustomModelData",0);
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_AXE_CORROSION.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_AXE.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_AXE_CORROSION_ONE.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_AXE.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_AXE_CORROSION_TWO.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_AXE.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_PICKAXE_CORROSION.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_PICKAXE.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_PICKAXE_CORROSION_ONE.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_PICKAXE.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_PICKAXE_CORROSION_TWO.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_PICKAXE.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_SHOVEL_CORROSION.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_SHOVEL.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_SHOVEL_CORROSION_ONE.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_SHOVEL.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_SHOVEL_CORROSION_TWO.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_SHOVEL.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_HOE_CORROSION.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_HOE.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_HOE_CORROSION_ONE.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_HOE.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_HOE_CORROSION_TWO.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_HOE.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_PAXEL_CORROSION.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_PAXEL.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_PAXEL_CORROSION_ONE.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_PAXEL.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_PAXEL_CORROSION_TWO.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_PAXEL.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_HELMET_CORROSION.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_HELMET.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_CHESTPLATE_CORROSION.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_CHESTPLATE.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_LEGGINGS_CORROSION.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_LEGGINGS.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((ModItemRegistryHandler.COPPER_BOOTS_CORROSION.get())))
        {
            resultSlots.setItem(0,new ItemStack(ModItemRegistryHandler.COPPER_BOOTS.get()));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.EXPOSED_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.COPPER_BLOCK));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WEATHERED_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.COPPER_BLOCK));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.OXIDIZED_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.COPPER_BLOCK));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.EXPOSED_CUT_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.CUT_COPPER));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WEATHERED_CUT_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.CUT_COPPER));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.OXIDIZED_CUT_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.CUT_COPPER));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.EXPOSED_CUT_COPPER_STAIRS)))
        {
            resultSlots.setItem(0,new ItemStack(Items.CUT_COPPER_STAIRS));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WEATHERED_CUT_COPPER_STAIRS)))
        {
            resultSlots.setItem(0,new ItemStack(Items.CUT_COPPER_STAIRS));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.OXIDIZED_CUT_COPPER_STAIRS)))
        {
            resultSlots.setItem(0,new ItemStack(Items.CUT_COPPER_STAIRS));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.EXPOSED_CUT_COPPER_SLAB)))
        {
            resultSlots.setItem(0,new ItemStack(Items.CUT_COPPER_SLAB));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WEATHERED_CUT_COPPER_SLAB)))
        {
            resultSlots.setItem(0,new ItemStack(Items.CUT_COPPER_SLAB));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.OXIDIZED_CUT_COPPER_SLAB)))
        {
            resultSlots.setItem(0,new ItemStack(Items.CUT_COPPER_SLAB));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_EXPOSED_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_COPPER_BLOCK));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_WEATHERED_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_COPPER_BLOCK));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_OXIDIZED_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_COPPER_BLOCK));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_EXPOSED_CUT_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_CUT_COPPER));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_WEATHERED_CUT_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_CUT_COPPER));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_OXIDIZED_CUT_COPPER)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_CUT_COPPER));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_EXPOSED_CUT_COPPER_STAIRS)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_CUT_COPPER_STAIRS));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_WEATHERED_CUT_COPPER_STAIRS)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_CUT_COPPER_STAIRS));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_OXIDIZED_CUT_COPPER_STAIRS)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_CUT_COPPER_STAIRS));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_EXPOSED_CUT_COPPER_SLAB)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_CUT_COPPER_SLAB));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_WEATHERED_CUT_COPPER_SLAB)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_CUT_COPPER_SLAB));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
        else if(inputSlots.getItem(0).is((Items.WAXED_OXIDIZED_CUT_COPPER_SLAB)))
        {
            resultSlots.setItem(0,new ItemStack(Items.WAXED_CUT_COPPER_SLAB));
            replaceAndWithCopyTagButNotCorrosion(inputSlots.getItem(0),resultSlots.getItem(0));
        }
    }

    protected boolean shouldQuickMoveToAdditionalSlot(ItemStack pStack) {
        return pStack.is(ModItemRegistryHandler.DERUSTING_SHOVEL.get());
    }

    /**
     * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is
     * null for the initial slot that was double-clicked.
     */
    public boolean canTakeItemForPickAll(ItemStack pStack, Slot pSlot) {
        return true;
    }

    @Override
    protected boolean isValidBlock(BlockState pState) {
        return stillValid(player);
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(this.access, pPlayer, ModBlocks.REDUCING_TABLE.get());
    }

    public static ReductionMenu fromNetwork(int id, Inventory inventory) {
        return new ReductionMenu(id, inventory, ContainerLevelAccess.NULL);
    }
}
