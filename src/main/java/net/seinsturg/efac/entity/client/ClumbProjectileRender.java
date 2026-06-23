package net.seinsturg.efac.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.entity.custom.ClumbProjectileEntity;

public class ClumbProjectileRender extends EntityRenderer<ClumbProjectileEntity> {
    private ClumbProjectileModel model;

    public ClumbProjectileRender(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ClumbProjectileModel(context.bakeLayer(ClumbProjectileModel.LAYER_LOCATION));
    }

    @Override
    public void render(ClumbProjectileEntity pEntity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        poseStack.mulPose(Axis.YP.rotationDegrees(pEntity.getRenderingRotation() * 5f));

        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(
                buffer, this.model.renderType(this.getTextureLocation(pEntity)),false, false);
        this.model.renderToBuffer(poseStack, vertexconsumer, packedLight, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();

        super.render(pEntity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(ClumbProjectileEntity clumbProjectileEntity) {
        return EFAC.res("textures/entity/clumb_projectile.png");
    }
}
