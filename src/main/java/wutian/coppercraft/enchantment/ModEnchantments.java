package wutian.coppercraft.enchantment;

import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wutian.coppercraft.CopperCraft;

public class ModEnchantments {
    private static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, CopperCraft.MODID);

    public static final RegistryObject<Enchantment> LIGHTNING_CALLER = ENCHANTMENTS.register("lightning_caller",LightningEnchantment::new);

    public static final RegistryObject<Enchantment> HASTING = ENCHANTMENTS.register("hasting", HastingEnchantment::new);

    public static void register(IEventBus eventBus)
    {
        ENCHANTMENTS.register(eventBus);
    }
}
