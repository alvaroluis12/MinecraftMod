package com.alvaro.rpgmod.entity.client.monster.globlin;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.entity.custom.globlin.GloblinLordEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GloblinLordModel extends GeoModel<GloblinLordEntity> {

    @Override
    public ResourceLocation getModelResource(GloblinLordEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "geo/globlin_lord.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GloblinLordEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "textures/entity/globlin_lord.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GloblinLordEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "animations/globlin_lord.animation.json");
    }

    @Override
    public void setCustomAnimations(GloblinLordEntity animatable, long instanceId,
            AnimationState<GloblinLordEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
    
}