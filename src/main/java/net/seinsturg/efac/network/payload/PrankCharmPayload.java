package net.seinsturg.efac.network.payload;

import com.mojang.serialization.Codec;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.EFAC;

public record PrankCharmPayload(Vec3 pos, Vec3 lookDir, int fuse, int mult) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<PrankCharmPayload> TYPE = new CustomPacketPayload.Type<>(EFAC.res("prank_charm_payload"));

    public static final StreamCodec<ByteBuf, PrankCharmPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(Vec3.CODEC),
            PrankCharmPayload::pos,
            ByteBufCodecs.fromCodec(Vec3.CODEC),
            PrankCharmPayload::lookDir,
            ByteBufCodecs.fromCodec(Codec.INT),
            PrankCharmPayload::fuse,
            ByteBufCodecs.fromCodec(Codec.INT),
            PrankCharmPayload::mult,
            PrankCharmPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
