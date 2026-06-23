package net.seinsturg.efac.network.payload;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.phys.Vec3;
import net.seinsturg.efac.EFAC;

public record SyncLastMovementPayload(Vec3 lastMovement) implements CustomPacketPayload {
    public static final Type<SyncLastMovementPayload> TYPE = new Type<>(EFAC.res("sync_last_movement_payload"));

    public static final StreamCodec<ByteBuf, SyncLastMovementPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.fromCodec(Vec3.CODEC),
            SyncLastMovementPayload::lastMovement,
            SyncLastMovementPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
