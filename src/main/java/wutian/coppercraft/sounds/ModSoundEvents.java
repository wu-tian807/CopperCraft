package wutian.coppercraft.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wutian.coppercraft.CopperCraft;

@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD)
public class ModSoundEvents {
    private static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CopperCraft.MODID);

    public static final RegistryObject<SoundEvent> REDUCING_WORKING = SOUND_EVENTS.register("reducing_working", () -> new SoundEvent(new ResourceLocation(CopperCraft.MODID,"reducing_working")));

    public static final RegistryObject<SoundEvent> SHOOT = SOUND_EVENTS.register("shoot", () -> new SoundEvent(new ResourceLocation(CopperCraft.MODID,"shoot")));

    public static void register(IEventBus eventBus)
    {
        SOUND_EVENTS.register(eventBus);
    }
}
