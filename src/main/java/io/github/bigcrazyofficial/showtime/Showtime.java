package io.github.bigcrazyofficial.showtime;

import io.github.bigcrazyofficial.showtime.entity.ModEntities;
import io.github.bigcrazyofficial.showtime.item.ModItems;
import io.github.bigcrazyofficial.showtime.item.component.ComponentTypes;
import io.github.bigcrazyofficial.showtime.util.Sounds;
import io.github.bigcrazyofficial.showtime.util.UnjustifiablyObtuseParticleHelper;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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
        UnjustifiablyObtuseParticleHelper.initialize();

        Registry.register(Registries.ITEM_GROUP, ModItems.GROUP_KEY, ModItems.ITEM_GROUP);
        ItemGroupEvents.modifyEntriesEvent(ModItems.GROUP_KEY).register(itemGroup -> {
            itemGroup.add(ModItems.SHOWSTOPPER);
            itemGroup.add(ModItems.GILDED_SHOWSTOPPER);

        });

        Showtime.LOGGER.info("yaaay");

    }
}
