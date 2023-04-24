package com.alvaro.rpgmod.entity.client;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.entity.custom.TrollEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TrollRenderer extends GeoEntityRenderer<TrollEntity> {

    public TrollRenderer(Context renderManager) {
        super(renderManager, new TrollModel());
    }
    
    @Override
    public ResourceLocation getTextureLocation(TrollEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "textures/entity/troll.png");
    }

}