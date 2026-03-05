package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.seinsturg.efac.EFAC;

public record SyncMaxChargePayload(int maxCharges) implements CustomPacketPayload {
    public static final Type<SyncMaxChargePayload> TYPE = new Type<>(EFAC.res("sync_max_charge_payload"));

    public static final StreamCodec<ByteBuf, SyncMaxChargePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            SyncMaxChargePayload::maxCharges,
            SyncMaxChargePayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
