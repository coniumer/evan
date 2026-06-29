package net.seinsturg.efac.sound;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.util.DeferredSoundType;
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

    public static final Supplier<SoundEvent> YES_BREAK = registerSoundEvent("yes_break");
    public static final Supplier<SoundEvent> YES_STEP = registerSoundEvent("yes_step");
    public static final Supplier<SoundEvent> YES_PLACE = registerSoundEvent("yes_place");
    public static final Supplier<SoundEvent> YES_HIT = registerSoundEvent("yes_hit");
    public static final Supplier<SoundEvent> YES_FALL = registerSoundEvent("yes_fall");

    public static final DeferredSoundType YES_BLOCK_SOUNDS = new DeferredSoundType(0.7f, 1.15f,
            EvansSounds.YES_BREAK, EvansSounds.YES_STEP, EvansSounds.YES_PLACE, EvansSounds.YES_HIT, EvansSounds.YES_FALL);

    public static final Supplier<SoundEvent> HARVESTER_HARVEST = registerSoundEvent("harvester_harvest");
    public static final Supplier<SoundEvent> HARVESTER_OPEN = registerSoundEvent("harvester_open");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation location = EFAC.res(name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(location));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
