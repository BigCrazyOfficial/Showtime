package io.github.bigcrazyofficial.showtime.item;

import io.github.bigcrazyofficial.showtime.Showtime;
import io.github.bigcrazyofficial.showtime.item.component.ComponentTypes;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.UseCooldownComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
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
                    .component(ComponentTypes.BALL_CHARGES, 4)
                    .component(ComponentTypes.RECHARGE_TIMER, 0)
                    .component(DataComponentTypes.UNBREAKABLE, Unit.INSTANCE)
                    .rarity(Rarity.EPIC)
                    .sword(ToolMaterial.GOLD, 0.0f, -2.4f));



    public static final RegistryKey<ItemGroup> GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Showtime.MOD_ID, "item_group"));
    public static final ItemGroup ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.SHOWTIME_STAFF))
            .displayName(Text.translatable("itemGroup.showtime"))
            .build();


    public static void initialize() {
        Showtime.LOGGER.info("yaaay");
    }
}