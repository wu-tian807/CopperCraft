package wutian.coppercraft.items.abstractItem;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModTiers {
    public static final ForgeTier COPPER = new ForgeTier(2,180,5.5f,0f,14, BlockTags.NEEDS_IRON_TOOL,()-> Ingredient.of(Items.COPPER_INGOT));
    public static final ForgeTier COPPER_CORROSION = new ForgeTier(2,180,3.5f,0f,14, BlockTags.NEEDS_IRON_TOOL,()-> Ingredient.of(Items.COPPER_INGOT));
    public static final ForgeTier COPPER_CORROSION_ONE = new ForgeTier(1,180,3f,0f,14, BlockTags.NEEDS_IRON_TOOL,()-> Ingredient.of(Items.COPPER_INGOT));
    public static final ForgeTier COPPER_CORROSION_TWO = new ForgeTier(1,180,1f,0f,14, BlockTags.NEEDS_STONE_TOOL,()-> Ingredient.of(Items.COPPER_INGOT));

    public static final ForgeTier IRON_MIXED_COPPER = new ForgeTier(3,350,6.8f,0f,20, BlockTags.NEEDS_STONE_TOOL,()-> Ingredient.of(Items.COPPER_INGOT));

    public static final ForgeTier COPPER_MIXED_GOLD = new ForgeTier(2,150,15f,0f,28, Tags.Blocks.NEEDS_GOLD_TOOL,()-> Ingredient.of(Items.GOLD_INGOT));
}
