package com.alvaro.rpgmod.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public class WindigoEntity extends Monster implements GeoEntity{

    private final AnimatableInstanceCache animatableCache = GeckoLibUtil.createInstanceCache(this);

    public WindigoEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH, 400D)
            .add(Attributes.ATTACK_DAMAGE, 20.0f)
            .add(Attributes.ATTACK_SPEED, 1f)
            .add(Attributes.MOVEMENT_SPEED, 0.2D).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.00D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return animatableCache;
    }

    @Override
    public void registerControllers(ControllerRegistrar registrarControllers) {
        registrarControllers.add(new AnimationController<>(this, "controller", this::predicate));
        registrarControllers.add(new AnimationController<>(this, "attackController", this::attackPredicate));
    }

    private <T extends GeoAnimatable> PlayState attackPredicate(AnimationState<T> state) {
        if (this.swinging){
            //state.getController().forceAnimationReset();
            //this.updateSwingTime();
            return state.setAndContinue(RawAnimation.begin().then("animation.troll.attack", Animation.LoopType.PLAY_ONCE));
        }
        else{
            return PlayState.STOP;
        }
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> state){
        if(state.isMoving()){
            return state.setAndContinue(RawAnimation.begin().thenLoop("animation.windigo.walk"));
        }
        else{
            return state.setAndContinue(RawAnimation.begin().thenLoop("animation.windigo.idle"));
        }
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return 1;
    }
    
}
