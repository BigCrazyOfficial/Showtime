package io.github.bigcrazyofficial.showtime.util;

import io.github.bigcrazyofficial.showtime.Showtime;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class Sounds {

    private static SoundEvent register(String id) {
        Identifier identifier = Identifier.of(Showtime.MOD_ID, id);
        return Registry.register(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }
    //we have to do it this way because apparently only references to sounds can be used in createExplosion()
    private static RegistryEntry.Reference<SoundEvent> registerAsReference(String id) {
        Identifier identifier = Identifier.of(Showtime.MOD_ID, id);
        return Registry.registerReference(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier));
    }

    public static final RegistryEntry<SoundEvent> BALL_DETONATE = registerAsReference("ball_detonate");
    public static final SoundEvent BALL_THROW = register("ball_throw");
    public static final SoundEvent STAFF_RECHARGE = register("staff_recharge");
    public static void initialize() {
        Showtime.LOGGER.info("yaaay");
    }
}
