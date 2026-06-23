package net.seinsturg.efac.event;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.entity.client.ClumbProjectileModel;

@EventBusSubscriber(modid = EFAC.MOD_ID)
public class EvansEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ClumbProjectileModel.LAYER_LOCATION, ClumbProjectileModel::createBodyLayer);
    }
}
