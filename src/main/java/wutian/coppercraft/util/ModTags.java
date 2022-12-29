package wutian.coppercraft.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import wutian.coppercraft.CopperCraft;

public class ModTags {
    public static class Blocks{
        public static final TagKey<Block> PAXEL_MINEABLE = tag("mineable/paxel") ;

        public static final TagKey<Block> GUN_CAN_DESTROY = tag("gun_can_destroy");

        private static TagKey<Block> tag(String name){
            return BlockTags.create(new ResourceLocation(CopperCraft.MODID,name));
        }
    }

    public static class Items{
        public static final TagKey<Item> CORROSION_ABLE =tag("corrosion_able");
        public static final TagKey<Item> CAN_ENCHANT_LIGHTNING = tag("can_enchant_lightning");

        public static final TagKey<Item> CAN_ENCHAT_HASTING = tag("can_enchant_hasting");

        public static final TagKey<Item> COPPER_EQUIPMENTS = tag("copper_equipments");

        public static final TagKey<Item> DAMAGE_WHOLE_SHOVEL = tag("damage_whole_shovel");

        public static final TagKey<Item> FORGE_COPPER = ItemTags.create(new ResourceLocation("forge","nuggets/copper"));



        private static TagKey<Item> tag(String name)
        {
            return ItemTags.create(new ResourceLocation(CopperCraft.MODID,name));
        }
    }
}
