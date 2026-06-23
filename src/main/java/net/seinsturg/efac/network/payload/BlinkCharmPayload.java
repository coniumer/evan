package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.EFAC;

public record BlinkCharmPayload(Vec3 movement) implements CustomPacketPayload {
    public static final Type<BlinkCharmPayload> TYPE = new Type<>(EFAC.res("blink_charm_payload"));

    public static final StreamCodec<ByteBuf, BlinkCharmPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(Vec3.CODEC),
            BlinkCharmPayload::movement,
            BlinkCharmPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
