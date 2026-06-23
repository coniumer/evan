package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.seinsturg.efac.EFAC;

public record ClumbFailPayload(int i) implements CustomPacketPayload {
    public static final Type<ClumbFailPayload> TYPE = new Type<>(EFAC.res("clumb_fail_payload"));

    public static final StreamCodec<ByteBuf, ClumbFailPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT,
            ClumbFailPayload::i,
            ClumbFailPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
