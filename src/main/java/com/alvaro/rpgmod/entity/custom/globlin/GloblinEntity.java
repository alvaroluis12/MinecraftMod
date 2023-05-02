package com.alvaro.rpgmod.entity.custom.globlin;

import com.alvaro.rpgmod.item.ModItems;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animation.AnimatableManager.ControllerRegistrar;
import software.bernie.geckolib.core.animation.Animation;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.object.PlayState;

public class GloblinEntity extends AbstractGloblin{
    public GloblinEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(ModItems.GLOBLIN_DAGGER.get()));
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2D, false));
    }

    /*@Override
    public void registerControllers(ControllerRegistrar controllers) {
        super.registerControllers(controllers);
        controllers.add(new AnimationController<>(this, "attackController", this::attackPredicate));
    }

    private <T extends GeoAnimatable> PlayState attackPredicate(AnimationState<T> state) {
        if (this.swinging){
            state.getController().forceAnimationReset();
            state.getController().setAnimation(RawAnimation.begin().then("animation.globlin.attack", Animation.LoopType.PLAY_ONCE));
            this.updateSwingTime();
        }
        return PlayState.CONTINUE;
    }*/

    @Override
    public int getMaxSpawnClusterSize() {
        return 10;
    }
    
}
