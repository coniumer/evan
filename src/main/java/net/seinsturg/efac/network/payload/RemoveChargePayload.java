package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.seinsturg.efac.EFAC;

public record RemoveChargePayload(int charges) implements CustomPacketPayload {
    public static final Type<RemoveChargePayload> TYPE = new Type<>(EFAC.res("remove_charge_payload"));

    public static final StreamCodec<ByteBuf, RemoveChargePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            RemoveChargePayload::charges,
            RemoveChargePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
