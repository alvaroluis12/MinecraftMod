package com.alvaro.rpgmod.goals;

import java.util.EnumSet;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import com.alvaro.rpgmod.capabilities.items.ItemEffectsProvider;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;

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
                if (!effects.hasGloblinFriendship()){
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