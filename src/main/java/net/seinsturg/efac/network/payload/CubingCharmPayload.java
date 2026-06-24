package net.seinsturg.efac.network.payload;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.EFAC;

public record CubingCharmPayload(Vec3 pos, Vec3 lookDir, int count, int mult) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<CubingCharmPayload> TYPE = new CustomPacketPayload.Type<>(EFAC.res("cubing_charm_payload"));

    public static final StreamCodec<ByteBuf, CubingCharmPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(Vec3.CODEC),
            CubingCharmPayload::pos,
            ByteBufCodecs.fromCodec(Vec3.CODEC),
            CubingCharmPayload::lookDir,
            ByteBufCodecs.fromCodec(Codec.INT),
            CubingCharmPayload::count,
            ByteBufCodecs.fromCodec(Codec.INT),
            CubingCharmPayload::mult,
            CubingCharmPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
