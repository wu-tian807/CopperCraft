package wutian.coppercraft.items.contents;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import wutian.coppercraft.enchantment.ModEnchantments;
import wutian.coppercraft.items.abstractItem.ModTiers;

import java.util.Random;

public class IronMixedCopperPickAxe extends PickaxeItem {
    public IronMixedCopperPickAxe() {
        super(ModTiers.IRON_MIXED_COPPER,5,-3.2f,
                new Properties().tab(CreativeModeTab.TAB_TOOLS).
                        stacksTo(1).durability(400));

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
