package com.alvaro.rpgmod.goals;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Monster;

public class FollowLeaderGoal extends Goal {
    public static final int HORIZONTAL_SCAN_RANGE = 8;
    public static final int VERTICAL_SCAN_RANGE = 4;
    public static final int DONT_FOLLOW_IF_CLOSER_THAN = 3;
    private final Monster monster;
    private Monster leader = null;
    @Nullable
    private final Class<? extends Monster> leaderClass;
    private final double speedModifier;
    private int timeToRecalcPath;
 
    public FollowLeaderGoal(Monster pMonster, double pSpeedModifier, Class<? extends Monster> pLeader) {
       this.monster = pMonster;
       this.leaderClass = pLeader;
       this.speedModifier = pSpeedModifier;
    }
 
    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        if (this.monster.getClass() == this.leaderClass){
            return false;
        }

          List<? extends Monster> list = this.monster.level.getEntitiesOfClass(this.leaderClass, this.monster.getBoundingBox().inflate(16.0D, 8.0D, 16.0D));
          Monster possibleLeader = null;
          double d0 = Double.MAX_VALUE;
 
          for(Monster possibleLeader1 : list) {
                double d1 = this.monster.distanceToSqr(possibleLeader1);
                if (!(d1 > d0)) {
                   d0 = d1;
                   possibleLeader = possibleLeader1;
                }
          }
 
          if (possibleLeader == null) {
             return false;
          } else if (d0 < 9.0D) {
             return false;
          } else {
             this.leader = possibleLeader;
             return true;
          }
    }
 
    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        double d0 = this.monster.distanceToSqr(this.leader);
        return !(d0 < 9.0D) && !(d0 > 256.0D);
    }
 
    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
       this.timeToRecalcPath = 0;
    }
 
    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
       this.leader = null;
    }
 
    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
       if (--this.timeToRecalcPath <= 0) {
          this.timeToRecalcPath = this.adjustedTickDelay(10);
          this.monster.getNavigation().moveTo(this.leader, this.speedModifier);
       }
    }
 }