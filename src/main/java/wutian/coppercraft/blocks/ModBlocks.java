package wutian.coppercraft.blocks;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wutian.coppercraft.CopperCraft;
import wutian.coppercraft.blocks.contents.Reducing_table;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, CopperCraft.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,CopperCraft.MODID);

    public static final RegistryObject<Block> REDUCING_TABLE = registerBlockWithItem("reducing_table",()->new Reducing_table(BlockBehaviour.Properties.of(Material.METAL).requiresCorrectToolForDrops().strength(5f)));

    private static <T extends Block> RegistryObject<T> registerBlockWithItem(String id, Supplier<T> block)
    {
        RegistryObject<T> toReturn =  BLOCKS.register(id,block);
        ITEMS.register(id,() -> new BlockItem(toReturn.get(),new Item.Properties().stacksTo(64)));
        return toReturn;
    }

    public static void register(IEventBus eventBus)
    {
        BLOCKS.register(eventBus);
        ITEMS.register(eventBus);
    }

}
