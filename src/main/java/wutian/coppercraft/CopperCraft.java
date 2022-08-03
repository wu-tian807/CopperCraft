package wutian.coppercraft;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
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
}
