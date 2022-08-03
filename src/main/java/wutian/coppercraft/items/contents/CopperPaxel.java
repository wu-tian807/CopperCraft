package wutian.coppercraft.items.contents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import wutian.coppercraft.enchantment.ModEnchantments;
import wutian.coppercraft.events.Corrosion;
import wutian.coppercraft.items.ModItemRegistryHandler;
import wutian.coppercraft.items.abstractItem.ModTiers;
import wutian.coppercraft.items.abstractItem.PaxelItem;

import java.util.Random;

public class CopperPaxel extends PaxelItem {

    public CopperPaxel() {
        super(8f, -2.6f, ModTiers.COPPER, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).stacksTo(1).durability(260));
    }

    @Override
    public void inventoryTick(@NotNull ItemStack itemStack, @NotNull Level world, @NotNull Entity usingEntity, int slot, boolean isSelecetd) {
        {
            //logger.debug(String.format("%d,and,%b",pSlotId,pIsSelected));
            //((Player)pEntity).getInventory().setItem(pSlotId, new ItemStack(Items.DIRT));
            //pStack.shrink(1);
            //*************************************
            Corrosion.inventoryOnCorrosion(itemStack,world,usingEntity);
            Corrosion.setCorrosionToANewItem(itemStack,usingEntity,new ItemStack(ModItemRegistryHandler.COPPER_PAXEL_CORROSION.get()),slot);
            super.inventoryTick(itemStack, world, usingEntity, slot, isSelecetd);
        }
    }

    @Override
    public boolean mineBlock(ItemStack pStack, Level pLevel, BlockState pState, BlockPos pPos, LivingEntity pEntityLiving) {
        if(!pLevel.isClientSide())
        {
            if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 1)
            {
                pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,0));
            }
            if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 2)
            {
                pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,2));
                if(new Random().nextInt(100) <= 10)
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,0));
            }
            if(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get()) == 3)
            {
                pEntityLiving.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,30,3));
                if(new Random().nextInt(100) <= 30)
                    pEntityLiving.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,100,1));
            }
        }
        //LogUtils.getLogger().debug(String.valueOf(pStack.getEnchantmentLevel(ModEnchantments.HASTING.get())));
        return super.mineBlock(pStack, pLevel, pState, pPos, pEntityLiving);
    }
}
