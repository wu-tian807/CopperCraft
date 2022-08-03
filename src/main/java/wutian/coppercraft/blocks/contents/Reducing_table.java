package wutian.coppercraft.blocks.contents;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import wutian.coppercraft.menus.ReductionMenu;

public class Reducing_table extends CraftingTableBlock {
    private static final net.minecraft.network.chat.Component CONTAINER_TITLE = Component.translatable("container.reduction");
    public Reducing_table(Properties pProperties) {
        super(pProperties);
    }

//    @Override
//    public RenderShape getRenderShape(BlockState pState) {
//        return RenderShape.MODEL;
//    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide) {
            pPlayer.openMenu(pState.getMenuProvider(pLevel, pPos));
            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public MenuProvider getMenuProvider(BlockState pState, Level pLevel, BlockPos pPos) {
        return new SimpleMenuProvider((p_52229_, p_52230_, p_52231_) -> {
            return new ReductionMenu(p_52229_, p_52230_, ContainerLevelAccess.create(pLevel, pPos));
        }, CONTAINER_TITLE);
    }
}
