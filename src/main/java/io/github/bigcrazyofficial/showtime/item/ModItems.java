package io.github.bigcrazyofficial.showtime.item;

import io.github.bigcrazyofficial.showtime.Showtime;
import io.github.bigcrazyofficial.showtime.item.component.ComponentTypes;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UseCooldownComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;

import java.util.function.Function;

public class ModItems {
    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Showtime.MOD_ID, name));
        Item item = itemFactory.apply(settings.registryKey(itemKey));
        Registry.register(Registries.ITEM, itemKey, item);
        return item;
    }
    public static final Item SHOWTIME_STAFF = register("showtime_staff", ShowtimeStaffItem::new,
            new Item.Settings()
                    .component(DataComponentTypes.USE_COOLDOWN, new UseCooldownComponent(0.25f))
                    .component(ComponentTypes.BALL_CHARGES, 0)
                    .component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE)
                    .sword(ToolMaterial.GOLD, 0.0f, -2.4f));


    public static void initialize() {
        Showtime.LOGGER.info("yaaay");
    }
}