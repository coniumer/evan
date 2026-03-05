package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.seinsturg.efac.EFAC;

public record AddChargePayload(int charges) implements CustomPacketPayload {
    public static final Type<AddChargePayload> TYPE = new Type<>(EFAC.res("add_charge_payload"));

    public static final StreamCodec<ByteBuf, AddChargePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            AddChargePayload::charges,
            AddChargePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
