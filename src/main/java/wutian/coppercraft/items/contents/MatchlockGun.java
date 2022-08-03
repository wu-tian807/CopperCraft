package wutian.coppercraft.items.contents;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import wutian.coppercraft.entities.EntityMatchlockAmmo;
import wutian.coppercraft.items.ModItemRegistryHandler;
import wutian.coppercraft.sounds.ModSoundEvents;

import java.util.List;
import java.util.Random;

public class MatchlockGun extends Item {

    public MatchlockGun() {
        super(new Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1));

    }

    public InteractionResultHolder<ItemStack> use(Level level, Player playerIn, InteractionHand mainhand) {

        ItemStack ammoItem = playerIn.getItemInHand(mainhand);

        boolean flag = !this.findAmmo(playerIn).isEmpty();
        //LogUtils.getLogger().debug(String.valueOf(flag));


        InteractionResultHolder<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(ammoItem, level, playerIn, mainhand, flag);

        if (ret != null) return ret;


        if (!playerIn.getAbilities().instabuild && !flag) {
            level.playSound((Player) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), SoundEvents.ITEM_BREAK, SoundSource.PLAYERS, 4.5F, 1F);
            return InteractionResultHolder.fail(ammoItem);

        } else {
            boolean flag1 = playerIn.getAbilities().instabuild || ((MatchlockGunChip) ModItemRegistryHandler.MATCHLOCK_GUN_CHIP.get()).isInfinite(ammoItem, ammoItem, playerIn);
            for (int i = 0; i < 1 + playerIn.getRandom().nextInt(5); i++) {
                level.addParticle(ParticleTypes.SMOKE, playerIn.position().x + playerIn.getLookAngle().x + playerIn.getRandom().nextFloat() * 0.25, playerIn.position().y + 1.6 + playerIn.getLookAngle().y, playerIn.position().z + +playerIn.getLookAngle().z + playerIn.getRandom().nextFloat() * 0.25, 0, 0.05 * new Random().nextFloat(1.5f), 0);
            }
            playerIn.setXRot(playerIn.getXRot() - 5f*playerIn.getRandom().nextFloat());

            if (!level.isClientSide) {
                MatchlockGunChip arrowitem = (MatchlockGunChip) (ammoItem.getItem() instanceof MatchlockGunChip ? ammoItem.getItem() : ModItemRegistryHandler.MATCHLOCK_GUN_CHIP.get().asItem());

                EntityMatchlockAmmo[] abstractarrowentities = new EntityMatchlockAmmo[8];
                for (int i = 0; i < abstractarrowentities.length; i++) {
                    abstractarrowentities[i] = arrowitem.createArrow(level, ammoItem, playerIn);
                }


                for (EntityMatchlockAmmo abstractarrowentity : abstractarrowentities) {
                    abstractarrowentity = customArrow(abstractarrowentity);

                    //abstractarrowentity.setItem(itemstack);

                    float r1 = playerIn.getRandom().nextIntBetweenInclusive(-3, 3) * 1f;
                    abstractarrowentity.shootFromRotation(playerIn, playerIn.getXRot() + r1, playerIn.getYRot(), 0.2F, 1.6F, 0.75F);
                    abstractarrowentity.setXRot(playerIn.getXRot() + r1);
                    abstractarrowentity.setYRot(playerIn.getYRot());




//                p_77615_1_.hurtAndBreak(1, playerIn, (p_220009_1_) -> {
//
//                    p_220009_1_.broadcastBreakEvent(Player.getUsedItemHand());
//
//                })

                    level.addFreshEntity(abstractarrowentity);

                }
            }
            level.playSound((Player) null, playerIn.getX(), playerIn.getY(), playerIn.getZ(), ModSoundEvents.SHOOT.get(), SoundSource.PLAYERS, 4.5F, 4.5F / (playerIn.getRandom().nextFloat() * 0.2F + 1.2F) + 0.5F);

            ItemStack ammo = findAmmo(playerIn);

            if (!flag1 && !playerIn.getAbilities().instabuild) {

                ammo.shrink(1);

                if (ammo.isEmpty()) {

                    playerIn.getInventory().removeItem(ammo);

                }

            }

            playerIn.getCooldowns().addCooldown(this, 10);


            playerIn.awardStat(Stats.ITEM_USED.get(this));

            return InteractionResultHolder.consume(ammoItem);

        }

    }


    @Override
    public float getDestroySpeed(ItemStack pStack, BlockState pState) {
        return 0f;
    }

    public EntityMatchlockAmmo customArrow(EntityMatchlockAmmo arrow) {

        return arrow;

    }


    //判断是否能找到我们的专属子弹

    protected ItemStack findAmmo(Player player) {

        if (this.isMatchlockAmmo(player.getItemInHand(InteractionHand.OFF_HAND))) {

            return player.getItemInHand(InteractionHand.OFF_HAND);

        } else if (this.isMatchlockAmmo(player.getItemInHand(InteractionHand.MAIN_HAND))) {

            return player.getItemInHand(InteractionHand.MAIN_HAND);

        }
//        else
//
//        {
//
//            for (int i = 0; i < player.getInventory().getContainerSize(); ++i)
//
//            {
//
//                ItemStack itemstack = player.getInventory().getItem(i);
//
//
//
//                if (this.isMatchlockAmmo(itemstack))
//
//                {
//
//                    return itemstack;
//
//                }
//
//            }
//
//
//
//
//
//        }
        return ItemStack.EMPTY;

    }



    protected boolean isMatchlockAmmo(ItemStack stack)

    {

        return stack.getItem() instanceof MatchlockGunChip;

    }



    @Override

    public boolean onEntitySwing(ItemStack stack, LivingEntity entity)

    {

        return true;

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(Screen.hasShiftDown())
        {
            pTooltipComponents.add(Component.translatable("tooltips.matchlock_gun"));
        }
        else
            pTooltipComponents.add(Component.translatable("tooltips.matchlock_gun.shiftTip"));
    }
}
