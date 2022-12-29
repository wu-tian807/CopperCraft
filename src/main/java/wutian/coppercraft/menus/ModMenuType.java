package wutian.coppercraft.menus;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wutian.coppercraft.CopperCraft;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
public class ModMenuType {

    private static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, CopperCraft.MODID);
    public static final RegistryObject<MenuType<ReductionMenu>> REDUCTION_TABLE = MENU_TYPES.register("reduction_table",() -> new MenuType<>(ReductionMenu::fromNetwork));

    public static void register(IEventBus eventBus)
    {
        MENU_TYPES.register(eventBus);
        //MenuScreens.register(REDUCTION_TABLE.get(), ReductionScreen::new);
    }

    public static void renderScreens() {
        MenuScreens.register(REDUCTION_TABLE.get(), ReductionScreen::new);
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void clientStepUp(FMLClientSetupEvent event)
    {
        renderScreens();
    }
}
