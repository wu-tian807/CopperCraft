package wutian.coppercraft.enchantment;

import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.NotNull;
import wutian.coppercraft.events.Corrosion;
import wutian.coppercraft.util.ModTags;

import java.util.Random;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.FORGE)
public class LightningEnchantment extends Enchantment {
    protected LightningEnchantment() {
        super(Rarity.UNCOMMON, EnchantmentCategory.WEAPON,new EquipmentSlot[]{EquipmentSlot.MAINHAND});
    }

    @Override
    public int getMinCost(int pEnchantmentLevel) {
        return pEnchantmentLevel * 10;
    }

    @Override
    public int getMaxCost(int pEnchantmentLevel) {
        return this.getMinCost(pEnchantmentLevel) + 15;
    }

//    @Override
//    public boolean checkCompatibility(Enchantment pEnch) {
//        return super.checkCompatibility(pEnch) && pEnch != Enchantments.SHARPNESS;
//    }

    @SubscribeEvent
    public static void whenPlayerUseAnvil(AnvilUpdateEvent event)
    {
        ItemStack right = event.getRight();
        ItemStack left = event.getLeft();
        if(right.is(Items.ENCHANTED_BOOK))
        {
            if((right).getTag().get("StoredEnchantments").getAsString().contains("coppercraft:lightning_caller"))
            {
                    event.setCanceled(!left.is(ModTags.Items.CAN_ENCHANT_LIGHTNING));
            }
        }
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.is(ModTags.Items.CAN_ENCHANT_LIGHTNING);
    }

    @Override
    public int getMaxLevel() {
        return  2;
    }


    @Override
    public void doPostAttack(LivingEntity pAttacker, @NotNull Entity pTarget, int pLevel) {
        if(!pAttacker.getLevel().isClientSide)
        {
            int r = new Random().nextInt(100);
            ServerLevel serverLevel = ((ServerLevel)pAttacker.getLevel());
            ServerPlayer serverPlayer = ((ServerPlayer) pAttacker);
            Vec3 vec3 = pAttacker.getLookAngle();
            LogUtils.getLogger().debug("ab");
            //LogUtils.getLogger().debug(vec3.toString());
            BlockPos pos = pTarget.getOnPos().offset(new BlockPos(Math.ceil(vec3.x*1.8),Math.ceil(Math.abs(vec3.y)),Math.ceil(vec3.z*1.8)).above());
            //LogUtils.getLogger().debug(String.valueOf(!Corrosion.hasTopBlock(pTarget)));
            if(!Corrosion.hasTopBlock(pTarget))
            {
                //LogUtils.getLogger().debug(String.valueOf(!Corrosion.hasTopBlock(pTarget)));
                if(pLevel == 1 && r <=20 && serverLevel.isRaining())
                    EntityType.LIGHTNING_BOLT.spawn(serverLevel,null,serverPlayer,pos, MobSpawnType.TRIGGERED,true,true);
                else if(pLevel == 2 && r <=50 && serverLevel.isRaining())
                    EntityType.LIGHTNING_BOLT.spawn(serverLevel,null,serverPlayer,pos, MobSpawnType.TRIGGERED,true,true);
                else if(pLevel == 1 && r <=80 && serverLevel.isThundering())
                    EntityType.LIGHTNING_BOLT.spawn(serverLevel,null,serverPlayer,pos, MobSpawnType.TRIGGERED,true,true);
                else if(pLevel == 2  && serverLevel.isThundering())
                    EntityType.LIGHTNING_BOLT.spawn(serverLevel,null,serverPlayer,pos, MobSpawnType.TRIGGERED,true,true);
                else if(pLevel == 2  && serverLevel.isThundering())
                    EntityType.LIGHTNING_BOLT.spawn(serverLevel,null,serverPlayer,pos, MobSpawnType.TRIGGERED,true,true);
                else if(pLevel == 1 && r <=5)
                    EntityType.LIGHTNING_BOLT.spawn(serverLevel,null,serverPlayer,pos, MobSpawnType.TRIGGERED,true,true);
                else if(pLevel == 2  && r <= 10)
                    EntityType.LIGHTNING_BOLT.spawn(serverLevel,null,serverPlayer,pos, MobSpawnType.TRIGGERED,true,true);
            }
        }

        super.doPostAttack(pAttacker,pTarget, pLevel);
    }
}
