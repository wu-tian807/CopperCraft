package wutian.coppercraft.items.abstractItem;

import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Vanishable;
import wutian.coppercraft.util.ModTags;

public class PaxelItem extends DiggerItem implements Vanishable {
    public PaxelItem(float attackDamage, float attackSpeed, Tier tier, Properties p) {
        super(attackDamage, attackSpeed, tier, ModTags.Blocks.PAXEL_MINEABLE, p);
    }
}
