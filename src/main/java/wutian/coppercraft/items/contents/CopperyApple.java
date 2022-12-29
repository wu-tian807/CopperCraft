package wutian.coppercraft.items.contents;

import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import wutian.coppercraft.items.abstractItem.ModFoods;

public class CopperyApple extends Item {
    public CopperyApple() {
        super(new Item.Properties().stacksTo(64).food(ModFoods.COPPERY_APPLE).rarity(Rarity.UNCOMMON));


    }
}
