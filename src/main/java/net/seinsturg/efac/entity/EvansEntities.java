package net.seinsturg.efac.entity;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.seinsturg.efac.EFAC;
import net.seinsturg.efac.entity.custom.BlinkColliderEntity;
import net.seinsturg.efac.entity.custom.ClumbProjectileEntity;

import java.util.function.Supplier;

public class EvansEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, EFAC.MOD_ID);

    public static final Supplier<EntityType<ClumbProjectileEntity>> CLUMB_PROJECTILE =
            ENTITY_TYPES.register("clumb_projectile", () -> EntityType.Builder.<ClumbProjectileEntity>of(ClumbProjectileEntity::new, MobCategory.MISC)
                    .sized(0.25f, 0.25f).build("clumb_projectile"));

    public static final Supplier<EntityType<BlinkColliderEntity>> BLINK_COLLIDER_ENTITY =
            ENTITY_TYPES.register("blink_collider_entity", () -> EntityType.Builder.<BlinkColliderEntity>of(BlinkColliderEntity::new, MobCategory.MISC)
                    .sized(0.1f, 0.1f).build("clumb_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
