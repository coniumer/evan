package net.seinsturg.efac.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;

import java.util.function.Supplier;

public class EvansSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, EFAC.MOD_ID);

    public static final Supplier<SoundEvent> CLUMB_SUCCESS = registerSoundEvent("clumb_success");
    public static final Supplier<SoundEvent> CLUMB_FAIL = registerSoundEvent("clumb_fail");
    public static final Supplier<SoundEvent> CLUMB_DASH = registerSoundEvent("clumb_dash");
    public static final Supplier<SoundEvent> CLUMB_CHARGE = registerSoundEvent("clumb_charge");
    public static final Supplier<SoundEvent> CLUMB_PULSE = registerSoundEvent("clumb_pulse");
    public static final Supplier<SoundEvent> TRY_PARRY = registerSoundEvent("try_parry");
    public static final Supplier<SoundEvent> PARRY = registerSoundEvent("parry");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation location = EFAC.res(name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(location));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
