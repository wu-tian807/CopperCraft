package wutian.coppercraft.items.contents;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import wutian.coppercraft.items.abstractItem.ModTiers;

public class IronMixedCopperSword extends SwordItem {
    public IronMixedCopperSword() {
        super(ModTiers.IRON_MIXED_COPPER,7,-2.6f,
                new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).
                        stacksTo(1));

    }
}
