package com.alvaro.rpgmod.entity.client.tiger;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.entity.custom.TigerEntity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TigerRenderer extends GeoEntityRenderer<TigerEntity> {

    public TigerRenderer(Context renderManager) {
        super(renderManager, new TigerModel());
    }

    @Override
    public ResourceLocation getTextureLocation(TigerEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "textures/entity/tiger.png");
    }

    @Override
    public void render(TigerEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
            MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()){
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
    
}
