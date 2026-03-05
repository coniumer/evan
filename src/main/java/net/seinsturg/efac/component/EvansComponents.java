package net.seinsturg.efac.component;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.item.component.PulsarComponent;

import java.util.function.UnaryOperator;

public class EvansComponents {
    public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, EFAC.MOD_ID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<PulsarComponent>> PULSAR_COMPONENT = REGISTRAR.registerComponentType(
            "pulsar_mode",
            builder -> builder
                    .persistent(PulsarComponent.PULSAR_COMPONENT_CODEC)
                    .networkSynchronized(PulsarComponent.PULSAR_STREAM_CODEC)
    );

    public static void register(IEventBus eventBus) {
        REGISTRAR.register(eventBus);
    }
}
