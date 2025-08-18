package io.github.bigcrazyofficial.showtime.item.component;

import com.mojang.serialization.Codec;
import io.github.bigcrazyofficial.showtime.Showtime;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ComponentTypes {
    public static void initialize() {
        Showtime.LOGGER.info("yaaay");
    }
    public static final ComponentType<Integer> BALL_CHARGES = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Showtime.MOD_ID, "charges"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );
    public static final ComponentType<Integer> RECHARGE_TIMER = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(Showtime.MOD_ID, "recharge_timer"),
            ComponentType.<Integer>builder().codec(Codec.INT).build()
    );
}
