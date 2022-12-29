package wutian.coppercraft;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import wutian.coppercraft.blocks.ModBlocks;
import wutian.coppercraft.enchantment.ModEnchantments;
import wutian.coppercraft.entities.ModEntityTypes;
import wutian.coppercraft.items.ModItemRegistryHandler;
import wutian.coppercraft.menus.ModMenuType;
import wutian.coppercraft.sounds.ModSoundEvents;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CopperCraft.MODID)
public class CopperCraft
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "coppercraft";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public CopperCraft()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the ItemRegistry
        ModSoundEvents.register(modEventBus);
        ModMenuType.register(modEventBus);
        ModEntityTypes.register(modEventBus);
        ModItemRegistryHandler.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEnchantments.register(modEventBus);
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerTab);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
    }

    private void registerTab(CreativeModeTabEvent.BuildContents event)
    {
        if(event.getTab() == CreativeModeTabs.COMBAT)
        {
            event.accept(ModItemRegistryHandler.COPPER_AXE.get());
            event.accept(ModItemRegistryHandler.COPPER_BOOTS.get());
            event.accept(ModItemRegistryHandler.COPPER_HELMET.get());
            event.accept(ModItemRegistryHandler.COPPER_CHESTPLATE.get());
            event.accept(ModItemRegistryHandler.COPPER_LEGGINGS.get());
            event.accept(ModItemRegistryHandler.COPPER_SWORD.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_AXE.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_SWORD.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_BOOTS.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_HELMET.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_CHESTPLATE.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_LEGGINGS.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_PAXEL.get());
            event.accept(ModItemRegistryHandler.COPPER_MIXED_GOLDEN_AXE.get());event.accept(ModItemRegistryHandler.COPPER_BOOTS.get());
            event.accept(ModItemRegistryHandler.COPPER_MIXED_GOLDEN_HELMET.get());
            event.accept(ModItemRegistryHandler.COPPER_MIXED_GOLDEN_CHESTPLATE.get());
            event.accept(ModItemRegistryHandler.COPPER_MIXED_GOLDEN_LEGGINGS.get());
            event.accept(ModItemRegistryHandler.COPPER_MIXED_GOLDEN_SWORD.get());
            event.accept(ModItemRegistryHandler.MATCHLOCK_GUN.get());
            event.accept(ModItemRegistryHandler.MATCHLOCK_GUN_AMMO.get());
            event.accept(ModItemRegistryHandler.MATCHLOCK_GUN_CHIP.get());
            event.accept(ModItemRegistryHandler.MATCHLOCK_GUN_CHIP_EMPTY.get());
        }
        else if(event.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS)
        {
            event.accept(ModBlocks.REDUCING_TABLE.get());
        }
        else if(event.getTab() == CreativeModeTabs.FOOD_AND_DRINKS)
        {
            event.accept(ModItemRegistryHandler.COPPERY_APPLE.get());
            event.accept(ModItemRegistryHandler.ENCHANTED_COPPERY_APPLE.get());
        }
        else if(event.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES)
        {
            event.accept(ModItemRegistryHandler.COPPER_SHOVEL.get());
            event.accept(ModItemRegistryHandler.COPPER_AXE.get());
            event.accept(ModItemRegistryHandler.COPPER_PICKAXE.get());
            event.accept(ModItemRegistryHandler.COPPER_HOE.get());
            event.accept(ModItemRegistryHandler.COPPER_PAXEL.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_SHOVEL.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_AXE.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_PICKAXE.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_HOE.get());
            event.accept(ModItemRegistryHandler.IRON_MIXED_COPPER_PAXEL.get());
            event.accept(ModItemRegistryHandler.COPPER_MIXED_GOLDEN_SHOVEL.get());
            event.accept(ModItemRegistryHandler.COPPER_MIXED_GOLDEN_AXE.get());
            event.accept(ModItemRegistryHandler.COPPER_MIXED_GOLDEN_PICKAXE.get());
            event.accept(ModItemRegistryHandler.COPPER_MIXED_GOLDEN_HOE.get());
        }
        else if(event.getTab() == CreativeModeTabs.INGREDIENTS)
        {
            event.accept(ModItemRegistryHandler.NETHERITE_IRON_INGOT.get());
            event.accept(ModItemRegistryHandler.COPPER_MIXED_GOLD_INGOT);
        }
    }
}

