package net.seinsturg.efac.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.seinsturg.efac.EFAC;

public class EvansDamage {
    public static final ResourceKey<DamageType> CLUMB =
            ResourceKey.create(Registries.DAMAGE_TYPE, EFAC.res("clumb"));
    public static final ResourceKey<DamageType> CLUMB_BYPASS =
            ResourceKey.create(Registries.DAMAGE_TYPE, EFAC.res("clumb_bypass"));
}
