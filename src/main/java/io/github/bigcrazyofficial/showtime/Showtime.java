package io.github.bigcrazyofficial.showtime;

import io.github.bigcrazyofficial.showtime.entity.ModEntities;
import io.github.bigcrazyofficial.showtime.item.ModItems;
import io.github.bigcrazyofficial.showtime.item.component.ComponentTypes;
import io.github.bigcrazyofficial.showtime.util.Sounds;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Showtime implements ModInitializer {
    public static final String MOD_ID = "showtime";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    @Override
    public void onInitialize() {
        ModItems.initialize();
        ComponentTypes.initialize();
        ModEntities.initialize();
        Sounds.initialize();
        Showtime.LOGGER.info("yaaay");


    }
}
