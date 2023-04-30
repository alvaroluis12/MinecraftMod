package com.alvaro.rpgmod.entity.custom;

import java.util.EnumSet;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.alvaro.rpgmod.capabilities.items.ItemEffectsProvider;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;

public abstract class AbstractGloblin extends Monster implements GeoEntity{
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public AbstractGloblin(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMonsterAttributes()
            .add(Attributes.MAX_HEALTH, 20D)
            .add(Attributes.ATTACK_DAMAGE, 2.0f)
            .add(Attributes.ATTACK_SPEED, 0.4f)
            .add(Attributes.MOVEMENT_SPEED, 0.25D)
            .add(Attributes.FOLLOW_RANGE, 30f).build();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        //this.goalSelector.addGoal(2, new FollowTrollGoal(this));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.00D));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 12.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        
        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
        
        this.targetSelector.addGoal(2, new GloblinNearestPlayerGoalWithoutItem(this, 10, true, false, null));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }
    

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public void registerControllers(ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", this::predicate));
    }

    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState){
        
        if (tAnimationState.isMoving()){
            return tAnimationState.setAndContinue(RawAnimation.begin().thenLoop("animation.globlin.walk"));
        }
        else{
            return PlayState.STOP;
            //return tAnimationState.setAndContinue(RawAnimation.begin().thenLoop("animation.globlin.idle"));
        }
    }

    public class GloblinNearestPlayerGoalWithoutItem extends TargetGoal {
        protected final int randomInterval;
        @Nullable
        protected LivingEntity target;
        /** This filter is applied to the Entity search. Only matching entities will be targeted. */
        protected TargetingConditions targetConditions;
      
         public GloblinNearestPlayerGoalWithoutItem(Mob pMob, int pRandomInterval, boolean pMustSee, boolean pMustReach, @Nullable Predicate<LivingEntity> pTargetPredicate) {
            super(pMob, pMustSee, pMustReach);
            //this.target = pMob.getTarget() instanceof Player ? (Player) pMob.getTarget() : null;
            this.randomInterval = reducedTickDelay(pRandomInterval);
            this.setFlags(EnumSet.of(Goal.Flag.TARGET));
            this.targetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(pTargetPredicate);

         }

         public boolean canUse() {
            if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
               return false;
            } else {
               this.findTarget();
               return this.target != null;
            }
         }
      
         protected AABB getTargetSearchArea(double pTargetDistance) {
            return this.mob.getBoundingBox().inflate(pTargetDistance, 4.0D, pTargetDistance);
         }
      
        protected void findTarget() {
            Player possibleTarget = this.mob.level.getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            if (possibleTarget != null){
                possibleTarget.getCapability(ItemEffectsProvider.ITEM_EFFECTS).ifPresent(effects -> {
                    if (!effects.hasGloblinFriend()){
                        setTarget(possibleTarget);
                    }
                    else {
                        setTarget(null);
                    }
                });
            }
        }
         /**
          * Execute a one shot task or start executing a continuous task
          */
         public void start() {
            this.mob.setTarget(this.target);
            super.start();
         }
      
         public void setTarget(@Nullable LivingEntity pTarget) {
            this.target = pTarget;
         }

    }
}
