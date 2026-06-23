package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.EFAC;

public record FinishedBlinkPayload(Vec3 lastMovement) implements CustomPacketPayload {
    public static final Type<FinishedBlinkPayload> TYPE = new Type<>(EFAC.res("finished_blink_payload"));

    public static final StreamCodec<ByteBuf, FinishedBlinkPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(Vec3.CODEC),
            FinishedBlinkPayload::lastMovement,
            FinishedBlinkPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
