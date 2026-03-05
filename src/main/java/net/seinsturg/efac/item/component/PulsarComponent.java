package net.seinsturg.efac.item.component;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;

public record PulsarComponent(boolean mode) {

    public static final Codec<PulsarComponent> PULSAR_COMPONENT_CODEC = RecordCodecBuilder.create(
            instance -> instance.group(Codec.BOOL.fieldOf("mode").forGetter(PulsarComponent::mode)
            ).apply(instance, PulsarComponent::new));
    public static final StreamCodec<ByteBuf, PulsarComponent> PULSAR_STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.BOOL, PulsarComponent::mode,
            PulsarComponent::new);
}
