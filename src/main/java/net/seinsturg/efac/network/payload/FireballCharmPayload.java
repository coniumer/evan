package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.EFAC;

public record FireballCharmPayload(Vec3 pos, Vec3 lookDir) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<FireballCharmPayload> TYPE = new CustomPacketPayload.Type<>(EFAC.res("fireball_charm_payload"));

    public static final StreamCodec<ByteBuf, FireballCharmPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(Vec3.CODEC),
            FireballCharmPayload::pos,
            ByteBufCodecs.fromCodec(Vec3.CODEC),
            FireballCharmPayload::lookDir,
            FireballCharmPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
