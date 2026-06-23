package net.seinsturg.efac.entity.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.entity.custom.BlinkColliderEntity;

public class BlinkColliderRenderer extends EntityRenderer<BlinkColliderEntity> {
    public BlinkColliderRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public ResourceLocation getTextureLocation(BlinkColliderEntity blinkColliderEntity) {
        return EFAC.res("");
    }
}
