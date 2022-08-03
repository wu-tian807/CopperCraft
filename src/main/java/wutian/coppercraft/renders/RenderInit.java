package wutian.coppercraft.renders;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import wutian.coppercraft.CopperCraft;
import wutian.coppercraft.entities.ModEntityTypes;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus= Mod.EventBusSubscriber.Bus.MOD,value = Dist.CLIENT,modid = CopperCraft.MODID)
public class RenderInit {

    @SubscribeEvent

    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {

        //添加我们的投掷物的渲染事件

        event.registerEntityRenderer(ModEntityTypes.MATCHLOCK_GUN_AMMO.get(), RenderMatchlockAmmo::new);

    }

}
