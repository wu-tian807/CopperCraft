package wutian.coppercraft.entities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import wutian.coppercraft.CopperCraft;

public class ModEntityTypes {
    private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CopperCraft.MODID);

    public static final RegistryObject<EntityType<EntityMatchlockAmmo>> MATCHLOCK_GUN_AMMO = ENTITY_TYPES.register("matchlock_gun_ammo",

            () -> EntityType.Builder.<EntityMatchlockAmmo>of(EntityMatchlockAmmo::new, MobCategory.MISC).sized(0.05f,0.05f).build(new ResourceLocation(CopperCraft.MODID,"matchlock_gun_ammo").toString()));

    public static void register(IEventBus eventBus)
    {
        ENTITY_TYPES.register(eventBus);
    }
}
