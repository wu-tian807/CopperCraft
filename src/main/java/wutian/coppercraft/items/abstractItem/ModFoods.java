package wutian.coppercraft.items.abstractItem;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties COPPERY_APPLE = new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(1.2f).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,300,0),1f).effect(new MobEffectInstance(MobEffects.DIG_SPEED,1000,1),0.8f).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,150,0),0.2f).build();
    public static final FoodProperties ENCHANTED_COPPERY_APPLE = new FoodProperties.Builder().alwaysEat().nutrition(4).saturationMod(1.2f).effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,1000,2),1f).effect(new MobEffectInstance(MobEffects.DIG_SPEED,2400,2),1f).effect(new MobEffectInstance(MobEffects.JUMP,300,2),1f).effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,1800,1),1f).build();
}
