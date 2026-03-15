package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.seinsturg.efac.EFAC;

public record ParryPayload(int duration) implements CustomPacketPayload {
    public static final Type<ParryPayload> TYPE = new Type<>(EFAC.res("parry_payload"));
    public static final StreamCodec<ByteBuf, ParryPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            ParryPayload::duration,
            ParryPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
