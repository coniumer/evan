package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.seinsturg.efac.EFAC;

public record SyncChargePayload(int charges) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<SyncChargePayload> TYPE = new CustomPacketPayload.Type<>(EFAC.res("sync_charge_payload"));

    public static final StreamCodec<ByteBuf, SyncChargePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            SyncChargePayload::charges,
            SyncChargePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
