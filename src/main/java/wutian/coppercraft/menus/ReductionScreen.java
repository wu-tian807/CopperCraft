package wutian.coppercraft.menus;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import wutian.coppercraft.CopperCraft;

@OnlyIn(Dist.CLIENT)
public class ReductionScreen extends ItemCombinerScreen<ReductionMenu> {
    private static final ResourceLocation textureLoc = getGuiTexture("container/reducing_table.png");


    public ReductionScreen(ReductionMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle, textureLoc);
        this.titleLabelX = 60;
        this.titleLabelY = 18;
    }

    protected void renderLabels(PoseStack pPoseStack, int pX, int pY) {
        RenderSystem.disableBlend();
        super.renderLabels(pPoseStack, pX, pY);
    }

    public static ResourceLocation getGuiTexture(String name) {
        return new ResourceLocation(CopperCraft.MODID, "textures/gui/" + name);
    }
}
