package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.EFAC;

public record LightningCharmPayload(Vec3 pos) implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<LightningCharmPayload> TYPE = new CustomPacketPayload.Type<>(EFAC.res("lightning_charm_payload"));

    public static final StreamCodec<ByteBuf, LightningCharmPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(Vec3.CODEC),
            LightningCharmPayload::pos,
            LightningCharmPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
