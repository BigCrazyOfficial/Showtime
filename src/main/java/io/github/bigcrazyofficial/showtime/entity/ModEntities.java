package io.github.bigcrazyofficial.showtime.entity;

import io.github.bigcrazyofficial.showtime.Showtime;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModEntities {
    private static final RegistryKey<EntityType<?>> SHOWTIME_BALL_KEY =
            RegistryKey.of(RegistryKeys.ENTITY_TYPE, Identifier.of(Showtime.MOD_ID, "showtime_ball"));

    public static final EntityType<ShowtimeBallEntity> SHOWTIME_BALL = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(Showtime.MOD_ID, "showtime_ball"),
            EntityType.Builder.<ShowtimeBallEntity>create(ShowtimeBallEntity::new, SpawnGroup.MISC)
                    .dimensions(0.5f, 0.5f).build(SHOWTIME_BALL_KEY));

    public static void initialize() {
        Showtime.LOGGER.info("yaaay");
    }
}
