package com.alvaro.rpgmod.entity.client;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.entity.custom.WindigoEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class WindigoModel extends GeoModel<WindigoEntity> {

    @Override
    public ResourceLocation getModelResource(WindigoEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "geo/windigo.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WindigoEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "textures/entity/windigo.png");
    }

    @Override
    public ResourceLocation getAnimationResource(WindigoEntity animatable) {
        return new ResourceLocation(RPGMod.MODID, "animations/windigo.animation.json");
    }

    @Override
    public void setCustomAnimations(WindigoEntity animatable, long instanceId,
            AnimationState<WindigoEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
    
}