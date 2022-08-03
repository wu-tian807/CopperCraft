package wutian.coppercraft.enchantment;

import com.mojang.logging.LogUtils;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.event.AnvilUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wutian.coppercraft.util.ModTags;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.FORGE)
public class HastingEnchantment extends Enchantment {
    protected HastingEnchantment() {
        super(Rarity.RARE, EnchantmentCategory.DIGGER,new EquipmentSlot[]{EquipmentSlot.MAINHAND});
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
            if((right).getTag().get("StoredEnchantments").getAsString().contains("coppercraft:hasting"))
            {
                LogUtils.getLogger().debug(String.valueOf(!left.is(ModTags.Items.CAN_ENCHAT_HASTING)));
                event.setCanceled(!left.is(ModTags.Items.CAN_ENCHAT_HASTING));
            }
        }
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack) {
        return stack.is(ModTags.Items.CAN_ENCHAT_HASTING);
    }

    @Override
    public int getMaxLevel() {
        return  3;
    }
}
