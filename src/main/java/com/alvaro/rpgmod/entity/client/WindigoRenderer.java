package com.alvaro.rpgmod.entity.client;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.entity.custom.WindigoEntity;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;

public class WindigoRenderer extends GeoEntityRenderer<WindigoEntity> {

    public WindigoRenderer(Context renderManager) {
        super(renderManager, new WindigoModel());
    }
    
    @Override
    public ResourceLocation getTextureLocation(WindigoEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "textures/entity/windigo.png");
    }

}