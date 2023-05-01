package com.alvaro.rpgmod.entity.custom.globlin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class GloblinLordEntity extends AbstractGloblin{

    public GloblinLordEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH, 400D)
            .add(Attributes.ATTACK_DAMAGE, 20.0f)
            .add(Attributes.ATTACK_SPEED, 0.4f)
            .add(Attributes.MOVEMENT_SPEED, 0.25D)
            .add(Attributes.KNOCKBACK_RESISTANCE, 1D)
            .add(Attributes.FOLLOW_RANGE, 30f).build();
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
    }
    
    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", this::predicate));
        controllers.add(new AnimationController<>(this, "attackController", this::attackPredicate));
    }

    private <T extends GeoAnimatable> PlayState attackPredicate(AnimationState<T> state) {
        if (this.swinging){
            state.getController().forceAnimationReset();
            state.getController().setAnimation(RawAnimation.begin().then("animation.globlin_lord.attack", Animation.LoopType.PLAY_ONCE));
            this.updateSwingTime();
        }
        return PlayState.CONTINUE;
    }
    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState){
        
        if (tAnimationState.isMoving()){
            return tAnimationState.setAndContinue(RawAnimation.begin().thenLoop("animation.globlin_lord.walk"));
        }
        else{
            return PlayState.STOP;
        }
    }

}
