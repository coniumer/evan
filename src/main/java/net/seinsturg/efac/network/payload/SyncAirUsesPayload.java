
package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.seinsturg.efac.EFAC;

public record SyncAirUsesPayload(int airUses) implements CustomPacketPayload {
    public static final Type<SyncAirUsesPayload> TYPE = new Type<>(EFAC.res("sync_air_uses_payload"));

    public static final StreamCodec<ByteBuf, SyncAirUsesPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            SyncAirUsesPayload::airUses,
            SyncAirUsesPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
