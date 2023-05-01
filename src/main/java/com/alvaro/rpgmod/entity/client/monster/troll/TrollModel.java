package com.alvaro.rpgmod.entity.client.monster.troll;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.entity.custom.TrollEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class TrollModel extends GeoModel<TrollEntity> {

    @Override
    public ResourceLocation getModelResource(TrollEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "geo/troll.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(TrollEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "textures/entity/troll.png");
    }

    @Override
    public ResourceLocation getAnimationResource(TrollEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "animations/troll.animation.json");
    }

    @Override
    public void setCustomAnimations(TrollEntity animatable, long instanceId,
            AnimationState<TrollEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
    
}
