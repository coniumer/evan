package net.seinsturg.efac.data;

import com.mojang.serialization.Codec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.seinsturg.efac.EFAC;

import java.util.function.Supplier;

public class EvansData {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, EFAC.MOD_ID);

    public static final Supplier<AttachmentType<Integer>> CHARGES = ATTACHMENT_TYPES.register(
            "charges", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT).build()
    );
    public static final Supplier<AttachmentType<Integer>> MAX_CHARGES = ATTACHMENT_TYPES.register(
            "max_charges", () -> AttachmentType.builder(() -> 5).serialize(Codec.INT)
            .copyOnDeath().build()
    );
    public static final Supplier<AttachmentType<Integer>> PARRY_TIME = ATTACHMENT_TYPES.register(
            "parry_time", () -> AttachmentType.builder(() -> 0).serialize(Codec.INT)
            .copyOnDeath().build()
    );
    public static final Supplier<AttachmentType<Boolean>> DAMAGE_FLAG = ATTACHMENT_TYPES.register(
            "damage_flag", () -> AttachmentType.builder(() -> false).serialize(Codec.BOOL)
            .build()
    );

    public static void register(IEventBus eventBus) {
        ATTACHMENT_TYPES.register(eventBus);
    }
}
