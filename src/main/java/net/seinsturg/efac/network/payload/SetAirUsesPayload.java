
package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.seinsturg.efac.EFAC;

public record SetAirUsesPayload(int airUses) implements CustomPacketPayload {
    public static final Type<SetAirUsesPayload> TYPE = new Type<>(EFAC.res("set_air_uses_payload"));

    public static final StreamCodec<ByteBuf, SetAirUsesPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            SetAirUsesPayload::airUses,
            SetAirUsesPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
