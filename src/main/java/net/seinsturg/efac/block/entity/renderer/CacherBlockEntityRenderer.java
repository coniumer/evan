package net.seinsturg.efac.block.entity.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.seinsturg.efac.block.entity.CacherBlockEntity;

public class CacherBlockEntityRenderer implements BlockEntityRenderer<CacherBlockEntity> {
    public CacherBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(CacherBlockEntity blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight, int packedOverlay) {

    }
}
