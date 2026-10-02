package com.tebrath.stalkermod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class StalkerMod implements ModInitializer {
	public static final String MODID = "stalkermod";

	// Hitbox size (width, height). The MODEL is drawn at full Blockbench size; the hitbox is kept
	// smaller for Daddy in Red so pathfinding doesn't get stuck on trees/terrain.
	public static final EntityType<StalkerEntity> WHISTLER = Registry.register(
			BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(MODID, "the_whistler"),
			EntityType.Builder.of(StalkerEntity::new, MobCategory.MONSTER)
					.sized(1.4F, 5.6F).clientTrackingRange(16).build("the_whistler"));

	public static final EntityType<StalkerEntity> DADDY_IN_RED = Registry.register(
			BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(MODID, "daddy_in_red"),
			EntityType.Builder.of(StalkerEntity::new, MobCategory.MONSTER)
					.sized(3.0F, 10.0F).clientTrackingRange(16).build("daddy_in_red"));

	public static final Item WHISTLER_EGG = Registry.register(
			BuiltInRegistries.ITEM, new ResourceLocation(MODID, "the_whistler_spawn_egg"),
			new SpawnEggItem(WHISTLER, 0x111111, 0xFF7A00, new Item.Properties()));

	public static final Item DADDY_IN_RED_EGG = Registry.register(
			BuiltInRegistries.ITEM, new ResourceLocation(MODID, "daddy_in_red_spawn_egg"),
			new SpawnEggItem(DADDY_IN_RED, 0x111111, 0xB00000, new Item.Properties()));

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(WHISTLER, StalkerEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(DADDY_IN_RED, StalkerEntity.createAttributes());

		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.SPAWN_EGGS).register(entries -> {
			entries.accept(WHISTLER_EGG);
			entries.accept(DADDY_IN_RED_EGG);
		});
	}
}
