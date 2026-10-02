package com.tebrath.stalkermod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.resources.ResourceLocation;

@Environment(EnvType.CLIENT)
public class StalkerModClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		EntityModelLayerRegistry.registerModelLayer(WhistlerModel.LAYER_LOCATION, WhistlerModel::createBodyLayer);
		EntityModelLayerRegistry.registerModelLayer(DaddyInRedModel.LAYER_LOCATION, DaddyInRedModel::createBodyLayer);

		EntityRendererRegistry.register(StalkerMod.WHISTLER, ctx -> new StalkerRenderer<>(ctx,
				new WhistlerModel(ctx.bakeLayer(WhistlerModel.LAYER_LOCATION)),
				new ResourceLocation(StalkerMod.MODID, "textures/entity/whistler.png")));

		EntityRendererRegistry.register(StalkerMod.DADDY_IN_RED, ctx -> new StalkerRenderer<>(ctx,
				new DaddyInRedModel(ctx.bakeLayer(DaddyInRedModel.LAYER_LOCATION)),
				new ResourceLocation(StalkerMod.MODID, "textures/entity/daddy_in_red.png")));
	}
}
