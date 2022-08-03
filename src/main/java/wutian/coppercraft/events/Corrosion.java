package wutian.coppercraft.events;

import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;
import wutian.coppercraft.items.ModItemRegistryHandler;
import wutian.coppercraft.items.abstractItem.ModArmorItem;
import wutian.coppercraft.util.ModTags;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.FORGE)
public class Corrosion {
    //actually used
    public static final int corrosionTick =45000;
    //test used
    //public static final int corrosionTick =9;


    @SubscribeEvent
    public static void setPlayerFovEffectScale(TickEvent.PlayerTickEvent event)
    {
        int count = ModArmorItem.getTheCountOfSuit(event.player);
        //LogUtils.getLogger().debug(String.valueOf(count));
        switch (count) {
            case 0 -> Minecraft.getInstance().options.fovEffectScale().set(1.0d);
            case 1, 2, 3, 4 -> Minecraft.getInstance().options.fovEffectScale().set(0d);
        }
    }
    //To reuse the method conveniently
    public static void inventoryOnCorrosion(ItemStack itemStack, Level world, Entity usingEntity)
    {

        if (!world.isClientSide) {
            if (itemStack.getOrCreateTag().contains("theDegreeOfCorrosion")) {
                Random random = new Random();
                int r = random.nextInt(Corrosion.corrosionTick);
                if (world.isRaining()) {
                    boolean hasTopBlock = hasTopBlock(usingEntity);
                    //logger.debug(String.valueOf(hasTopBlock));
                    if (r <= 15 && !hasTopBlock)
                        itemStack.getTag().putInt("theDegreeOfCorrosion", itemStack.getTag().getInt("theDegreeOfCorrosion") + 1);
                    if (r == 0 && hasTopBlock)
                        itemStack.getTag().putInt("theDegreeOfCorrosion", itemStack.getTag().getInt("theDegreeOfCorrosion") + 1);
                } else if (r == 0)
                    itemStack.getTag().putInt("theDegreeOfCorrosion", itemStack.getTag().getInt("theDegreeOfCorrosion") + 1);

            } else {
                itemStack.getOrCreateTag().putInt("theDegreeOfCorrosion", 0);
            }
        }
    }

    public static boolean onArmorOrOffHand(ItemStack pStack,Level pLevel,Entity pEntity)
    {
        final Boolean[] onArmorOrOffhand = {false};
        pEntity.getArmorSlots().forEach(new Consumer<ItemStack>() {
            @Override
            public void accept(ItemStack itemStack) {
                if(itemStack.equals(pStack))
                {
                    onArmorOrOffhand[0] =true;
                    //if(((ArmorItem)pStack.getItem()).getMaterial() == ModArmorMaterials.COPPER)
                    //{
                        if((pStack.is(ModItemRegistryHandler.COPPER_HELMET.get())))
                            Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_HELMET_CORROSION.get()),EquipmentSlot.HEAD);
                        else if((pStack.is(ModItemRegistryHandler.COPPER_CHESTPLATE.get())))
                            Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_CHESTPLATE_CORROSION.get()),EquipmentSlot.CHEST);
                        if((pStack.is(ModItemRegistryHandler.COPPER_LEGGINGS.get())))
                            Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_LEGGINGS_CORROSION.get()),EquipmentSlot.LEGS);
                        if((pStack.is(ModItemRegistryHandler.COPPER_BOOTS.get())))
                            Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_BOOTS_CORROSION.get()),EquipmentSlot.FEET);
                    //}
                }
            }
        });
        if(((Player)pEntity).getOffhandItem().equals(pStack))
        {
            onArmorOrOffhand[0] = true;
            if((pStack.is(ModItemRegistryHandler.COPPER_HELMET.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_HELMET_CORROSION.get()),EquipmentSlot.OFFHAND);
            else if((pStack.is(ModItemRegistryHandler.COPPER_CHESTPLATE.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_CHESTPLATE_CORROSION.get()),EquipmentSlot.OFFHAND);
            if((pStack.is(ModItemRegistryHandler.COPPER_LEGGINGS.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_LEGGINGS_CORROSION.get()),EquipmentSlot.OFFHAND);
            if((pStack.is(ModItemRegistryHandler.COPPER_BOOTS.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_BOOTS_CORROSION.get()),EquipmentSlot.OFFHAND);
            if((pStack.is(ModItemRegistryHandler.COPPER_PAXEL.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_PAXEL_CORROSION.get()),EquipmentSlot.OFFHAND);
            if((pStack.is(ModItemRegistryHandler.COPPER_PAXEL_CORROSION.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_PAXEL_CORROSION_ONE.get()),EquipmentSlot.OFFHAND);
            if((pStack.is(ModItemRegistryHandler.COPPER_PAXEL_CORROSION_ONE.get())))
                Corrosion.setCorrosionToANewItem(pStack,pEntity,new ItemStack(ModItemRegistryHandler.COPPER_PAXEL_CORROSION_TWO.get()),EquipmentSlot.OFFHAND);
        }
        //LogUtils.getLogger().debug(String.valueOf(onArmorOrOffhand[0]));
        return onArmorOrOffhand[0];
    }
    public static ItemStack setANewItemStackWithCopyTag(ItemStack itemStack,ItemStack toReplaceItem)
    {
        CompoundTag compoundTag = itemStack.getOrCreateTag().copy();
        toReplaceItem.setTag(compoundTag);
        return toReplaceItem;
    }
    public static void setCorrosionToANewItem(ItemStack itemStack, Entity entity, ItemStack toReplaceItem, int slot)
    {
        if(onArmorOrOffHand(itemStack,entity.getLevel(),entity)) return;
        if (itemStack.getOrCreateTag().getInt("theDegreeOfCorrosion") > 5) {

            itemStack.getTag().putInt("theDegreeOfCorrosion",0);
            CompoundTag compoundTag = itemStack.getTag().copy();
            Player player = (Player) entity;
            toReplaceItem.setTag(compoundTag);
            player.getInventory().setItem(slot, toReplaceItem);
        }
    }
    public static void setCorrosionToANewItem(ItemStack itemStack, Entity entity, ItemStack toReplaceItem, EquipmentSlot slot)
    {
        //if(onArmorOrOffHand(itemStack,entity.getLevel(),entity)) return;
        if (itemStack.getOrCreateTag().getInt("theDegreeOfCorrosion") > 5) {

            itemStack.getTag().putInt("theDegreeOfCorrosion",0);
            setANewItemStackWithCopyTag(itemStack,toReplaceItem);
            Player player = (Player) entity;
            player.setItemSlot(slot,toReplaceItem);
        }
    }
    public static boolean hasTopBlock(Entity entity)
    {
        boolean hasTopBlock = false;
        for (int i = 1; i <= 255 - entity.getBlockY(); i++) {
            hasTopBlock = !entity.getLevel().isEmptyBlock(new BlockPos(entity.getBlockX(), entity.getBlockY() + i, entity.getBlockZ()));
            if (hasTopBlock)
                break;
        }
        return hasTopBlock;
    }


    @SubscribeEvent
    public static void ItemOnCorroding(TickEvent.PlayerTickEvent event) {
        Logger logger = LogUtils.getLogger();
        //nearby -100 - +100 itemEntity Corroding
        List<Entity> entities = event.player.getLevel().getEntities(null, new AABB(event.player.getBlockX() - 100, event.player.getBlockY() - 100, event.player.getBlockZ() - 100, event.player.getBlockX() + 100, event.player.getBlockY() + 100, event.player.getBlockZ() + 100));
        entities.forEach((entity ->
        {
            if (entity instanceof ItemEntity && !entity.getLevel().isClientSide()) {
                ItemStack itemStack = ((ItemEntity) entity).getItem();
                if (itemStack.is(ModTags.Items.CORROSION_ABLE)) {
                    //logger.debug(String.valueOf(itemStack.getTag().get("theDegreeOfCorrosion")));
                    if (((ItemEntity) entity).getItem().getOrCreateTag().contains("theDegreeOfCorrosion")) {
                        Random random = new Random();
                        int r = random.nextInt(corrosionTick);
                        if (entity.getLevel().isRaining()) {
                            boolean hasTopBlock = false;
                            for (int i = 1; i <= 255 - entity.getBlockY(); i++) {
                                hasTopBlock = !entity.getLevel().isEmptyBlock(new BlockPos(entity.getBlockX(), entity.getBlockY() + i, entity.getBlockZ()));
                                if (hasTopBlock)
                                    break;
                            }
                            //logger.debug(String.valueOf(hasTopBlock));
                            if (r <= 15 && !hasTopBlock)
                                ((ItemEntity) entity).getItem().getTag().putInt("theDegreeOfCorrosion", ((ItemEntity) entity).getItem().getTag().getInt("theDegreeOfCorrosion") + 1);
                            if (r == 0 && hasTopBlock)
                                ((ItemEntity) entity).getItem().getTag().putInt("theDegreeOfCorrosion", ((ItemEntity) entity).getItem().getTag().getInt("theDegreeOfCorrosion") + 1);
                        } else {
                            if (r == 0)
                                ((ItemEntity) entity).getItem().getTag().putInt("theDegreeOfCorrosion", ((ItemEntity) entity).getItem().getTag().getInt("theDegreeOfCorrosion") + 1);
                        }
                    } else {
                        ((ItemEntity) entity).getItem().getOrCreateTag().putInt("theDegreeOfCorrosion", 0);
                    }


                    if (itemStack.getTag().getInt("theDegreeOfCorrosion") > 5) {
                        if (itemStack.getTag().contains("CustomModelData")) {
                            if (itemStack.getTag().getInt("CustomModelData") <= 3)
                                itemStack.getTag().putInt("CustomModelData", itemStack.getTag().getInt("CustomModelData") + 1);
                            itemStack.getTag().putInt("theDegreeOfCorrosion", 0);
                            if (itemStack.is(ModItemRegistryHandler.COPPER_SWORD.get())) {
                                switch (itemStack.getTag().getInt("CustomModelData")) {
                                    case 1 -> {
                                        itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier("Attack_Damage", 3, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                                        itemStack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier("Attack_Speed", -2.6, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                                    }
                                    case 2, 3 -> {
                                        itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier("Attack_Damage", -1, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                                        itemStack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier("Attack_Speed", -0.2, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                                    }
                                }
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_PAXEL.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_PAXEL_CORROSION.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_PAXEL_CORROSION.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_PAXEL_CORROSION_ONE.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_PAXEL_CORROSION_ONE.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_PAXEL_CORROSION_TWO.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_HELMET.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_HELMET_CORROSION.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_CHESTPLATE.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_CHESTPLATE_CORROSION.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_LEGGINGS.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_LEGGINGS_CORROSION.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_BOOTS.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_BOOTS_CORROSION.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_PICKAXE.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_PICKAXE_CORROSION.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_PICKAXE_CORROSION.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_PICKAXE_CORROSION_ONE.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_PICKAXE_CORROSION_ONE.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_PICKAXE_CORROSION_TWO.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_AXE.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_AXE_CORROSION.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_AXE_CORROSION.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_AXE_CORROSION_ONE.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_AXE_CORROSION_ONE.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_AXE_CORROSION_TWO.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_SHOVEL.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_SHOVEL_CORROSION.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_SHOVEL_CORROSION.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_SHOVEL_CORROSION_ONE.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_SHOVEL_CORROSION_ONE.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_SHOVEL_CORROSION_TWO.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_HOE.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_HOE_CORROSION.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_HOE_CORROSION.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_HOE_CORROSION.get())));
                            }
                            else if(itemStack.is((ModItemRegistryHandler.COPPER_HOE_CORROSION_ONE.get())))
                            {
                                ItemEntity itemEntity = (ItemEntity) entity;
                                itemEntity.setItem(setANewItemStackWithCopyTag(itemEntity.getItem(),new ItemStack(ModItemRegistryHandler.COPPER_HOE_CORROSION_TWO.get())));
                            }
                        } else
                            itemStack.getTag().putInt("CustomModelData", 0);
                    }
                }
            }

        }));
        //BlockEntity
        int r = new Random().nextInt(corrosionTick);
        if (!event.player.getLevel().isClientSide() && r == 0)
            corrosionOnTheContainer(event.player.getLevel(), event.player);
        //logger.debug(String.valueOf(r));

    }
    private static void corrosionOnTheContainer(Level level, Player player) {
        List<BlockEntity> blockEntities = new ArrayList<>(5);
        int playerX = player.getOnPos().getX();
        int playerY = player.getOnPos().getY();
        int playerZ = player.getOnPos().getZ();
        for (int x = playerX - 100; x <= playerX + 100; x++) {
            for (int y = playerY - 100; y <= playerY + 100; y++) {
                for (int z = playerZ - 100; z <= playerZ + 100; z++) {
                    BlockPos pos = new BlockPos(x, y, z);
                    BlockEntity blockEntity = level.getBlockEntity(pos);
                    if (blockEntity instanceof BaseContainerBlockEntity)
                        blockEntities.add(blockEntity);
                }
            }
        }
        Logger logger = LogUtils.getLogger();
        for (var item :
                blockEntities) {
            //logger.debug(item.getBlockPos().toString());
            BaseContainerBlockEntity b = ((BaseContainerBlockEntity) item);
            for (int slot = 0; slot < b.getContainerSize(); slot++) {
                ItemStack itemStack = b.getItem(slot);
                if (itemStack.is(ModTags.Items.CORROSION_ABLE)) {
                    if (!itemStack.getOrCreateTag().contains("CustomModelData"))
                        itemStack.getTag().putInt("CustomModelData", 0);
                    if (itemStack.getOrCreateTag().contains("theDegreeOfCorrosion")) {
                        itemStack.getTag().putInt("theDegreeOfCorrosion", itemStack.getTag().getInt("theDegreeOfCorrosion")+1);
                    } else
                        itemStack.getOrCreateTag().putInt("theDegreeOfCorrosion", 0);
                    if (itemStack.getOrCreateTag().getInt("theDegreeOfCorrosion") > 5) {
                        if (itemStack.is(ModItemRegistryHandler.COPPER_SWORD.get())) {
                            int customModelData = itemStack.getTag().getInt("CustomModelData");
                            itemStack.getTag().putInt("CustomModelData", customModelData + 1);
                            switch (customModelData) {
                                case 1 -> {
                                    itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier("Attack_Damage", 3, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                                    itemStack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier("Attack_Speed", -2.6, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                                }
                                case 2, 3 -> {
                                    itemStack.addAttributeModifier(Attributes.ATTACK_DAMAGE, new AttributeModifier("Attack_Damage", -1, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                                    itemStack.addAttributeModifier(Attributes.ATTACK_SPEED, new AttributeModifier("Attack_Speed", -0.2, AttributeModifier.Operation.ADDITION), EquipmentSlot.MAINHAND);
                                }
                            }
                            b.setItem(slot, itemStack);
                        } else if (itemStack.is((ModItemRegistryHandler.COPPER_PAXEL.get()))) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack(ModItemRegistryHandler.COPPER_PAXEL_CORROSION.get())));
                        } else if (itemStack.is((ModItemRegistryHandler.COPPER_PAXEL_CORROSION.get()))) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack(ModItemRegistryHandler.COPPER_PAXEL_CORROSION_ONE.get())));
                        } else if (itemStack.is((ModItemRegistryHandler.COPPER_PAXEL_CORROSION_ONE.get()))) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack(ModItemRegistryHandler.COPPER_PAXEL_CORROSION_TWO.get())));
                        } else if (itemStack.is(ModItemRegistryHandler.COPPER_HELMET.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_HELMET_CORROSION.get()))));
                        } else if (itemStack.is(ModItemRegistryHandler.COPPER_CHESTPLATE.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_CHESTPLATE_CORROSION.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_LEGGINGS.get())) {
                                    b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_LEGGINGS_CORROSION.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_BOOTS.get())) {
                                    b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_BOOTS_CORROSION.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_PICKAXE.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_PICKAXE_CORROSION.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_PICKAXE_CORROSION.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_PICKAXE_CORROSION_ONE.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_PICKAXE_CORROSION_ONE.get())) {
                                    b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_PICKAXE_CORROSION_TWO.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_AXE.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_AXE_CORROSION.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_AXE_CORROSION.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_AXE_CORROSION_ONE.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_AXE_CORROSION_ONE.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_AXE_CORROSION_TWO.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_SHOVEL.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_SHOVEL_CORROSION.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_SHOVEL_CORROSION.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_SHOVEL_CORROSION_ONE.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_SHOVEL_CORROSION_ONE.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_SHOVEL_CORROSION_TWO.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_HOE.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_HOE_CORROSION.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_HOE_CORROSION.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack,new ItemStack((ModItemRegistryHandler.COPPER_HOE_CORROSION_ONE.get()))));
                        }else if (itemStack.is(ModItemRegistryHandler.COPPER_HOE_CORROSION_ONE.get())) {
                            b.setItem(slot, setANewItemStackWithCopyTag(itemStack, new ItemStack((ModItemRegistryHandler.COPPER_HOE_CORROSION_TWO.get()))));
                        }
                    }
                }
            }
        }
    }
}
