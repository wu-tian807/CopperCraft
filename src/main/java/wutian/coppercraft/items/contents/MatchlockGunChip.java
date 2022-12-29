package wutian.coppercraft.items.contents;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import wutian.coppercraft.entities.EntityMatchlockAmmo;

import java.util.List;

public class MatchlockGunChip extends Item {

    public MatchlockGunChip() {
        super(new Properties().stacksTo(16));
    }
    public EntityMatchlockAmmo createArrow(Level level, ItemStack itemStack, LivingEntity livingEntity) {

        EntityMatchlockAmmo arrowentity = new EntityMatchlockAmmo(level, livingEntity);

        return arrowentity;
    }

    public boolean isInfinite(ItemStack itemStack,ItemStack gun, net.minecraft.world.entity.player.Player player) {

        if(!gun.hasTag()) return false;
        if(!gun.getTag().contains("Infinite")) return false;
        return gun.getTag().getInt("Infinite") == 1;

    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.matchlock_gun_ammo"));
    }
}
