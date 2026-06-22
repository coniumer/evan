package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.seinsturg.efac.EFAC;

public record HealthCharmPayload(int amt, boolean regenFlag, int regenDuration, int regenAmp) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<HealthCharmPayload> TYPE = new CustomPacketPayload.Type<>(EFAC.res("health_charm_payload"));

    public static final StreamCodec<ByteBuf, HealthCharmPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            HealthCharmPayload::amt,
            ByteBufCodecs.BOOL,
            HealthCharmPayload::regenFlag,
            ByteBufCodecs.INT,
            HealthCharmPayload::regenDuration,
            ByteBufCodecs.INT,
            HealthCharmPayload::regenAmp,
            HealthCharmPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
