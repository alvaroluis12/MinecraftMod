package com.alvaro.rpgmod.entity.client;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.entity.custom.AbstractGloblin;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GloblinModel extends GeoModel<AbstractGloblin> {

    @Override
    public ResourceLocation getModelResource(AbstractGloblin animatable) {
        return new ResourceLocation(RPGMod.MODID, "geo/globlin.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(AbstractGloblin animatable) {
        return new ResourceLocation(RPGMod.MODID, "textures/entity/globlin.png");
    }

    @Override
    public ResourceLocation getAnimationResource(AbstractGloblin animatable) {
        return new ResourceLocation(RPGMod.MODID, "animations/globlin.animation.json");
    }

    @Override
    public void setCustomAnimations(AbstractGloblin animatable, long instanceId,
            AnimationState<AbstractGloblin> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
    
}
