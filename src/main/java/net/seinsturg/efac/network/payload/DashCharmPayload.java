package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.EFAC;

public record DashCharmPayload(int i) implements CustomPacketPayload {
    public static final Type<DashCharmPayload> TYPE = new Type<>(EFAC.res("dash_charm_payload"));

    public static final StreamCodec<ByteBuf, DashCharmPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            DashCharmPayload::i,
            DashCharmPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
