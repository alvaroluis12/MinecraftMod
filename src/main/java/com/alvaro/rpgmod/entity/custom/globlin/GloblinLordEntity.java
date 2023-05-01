package com.alvaro.rpgmod.entity.custom.globlin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

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

}
