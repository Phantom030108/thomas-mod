package net.thomas.mod.Item;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.thomas.mod.ThomasMod;

import java.util.function.Function;

public class ModItems {
    public static final Item RUBY = register("ruby", Item::new, new Item.Properties());
    public static final Item RUBY_SHARD = register("ruby_shard", Item::new, new Item.Properties());

    public static void initialize() {
        // Get the event for modifying entries in the ingredients group.
        // And register an event handler that adds our suspicious item to the ingredients group.
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register((creativeTab) -> creativeTab.accept(ModItems.RUBY));
        CreativeModeTabEvents.modifyOutputEvent(CreativeModeTabs.INGREDIENTS).register((creativeTab) -> creativeTab.accept(ModItems.RUBY_SHARD));
    }

    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        //Create Item Key
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ThomasMod.MOD_ID, name));

        //Create Item Instance
        T item = itemFactory.apply(settings.setId(itemKey));

        //Register Item
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }
}
