package com.alvaro.rpgmod.entity.custom.globlin;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.RangedBowAttackGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;

public class ArcherGloblinEntity extends AbstractGloblin implements RangedAttackMob {
    private final RangedBowAttackGoal<ArcherGloblinEntity> bowGoal = new RangedBowAttackGoal<ArcherGloblinEntity>(this, 1.0D, 20, 30.0F);
    private final MeleeAttackGoal meleeGoal = new MeleeAttackGoal(this, 1.2D, false);

    public ArcherGloblinEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.BOW));
    }

    public void reassessWeaponGoal() {
        if (this.level != null && !this.level.isClientSide) {
            this.goalSelector.removeGoal(this.meleeGoal);
            this.goalSelector.removeGoal(this.bowGoal);
            ItemStack itemstack = this.getItemInHand(ProjectileUtil.getWeaponHoldingHand(this,
                    item -> item instanceof net.minecraft.world.item.BowItem));
            if (itemstack.is(Items.BOW)) {
                this.bowGoal.setMinAttackInterval(20);
                this.goalSelector.addGoal(2, this.bowGoal);
            } else {
                this.goalSelector.addGoal(2, this.meleeGoal);
            }
        }
    }
    

    public void performRangedAttack(LivingEntity pTarget, float pDistanceFactor) {
        ItemStack itemstack = this.getProjectile(this.getItemInHand(
                ProjectileUtil.getWeaponHoldingHand(this, item -> item instanceof net.minecraft.world.item.BowItem)));
        AbstractArrow abstractarrow = this.getArrow(itemstack, pDistanceFactor);
        if (this.getMainHandItem().getItem() instanceof net.minecraft.world.item.BowItem)
            abstractarrow = ((net.minecraft.world.item.BowItem) this.getMainHandItem().getItem())
                    .customArrow(abstractarrow);
        double d0 = pTarget.getX() - this.getX();
        double d1 = pTarget.getY(0.3333333333333333D) - abstractarrow.getY();
        double d2 = pTarget.getZ() - this.getZ();
        double d3 = Math.sqrt(d0 * d0 + d2 * d2);
        abstractarrow.shoot(d0, d1 + d3 * (double) 0.2F, d2, 1.6F,
                (float) (14 - this.level.getDifficulty().getId() * 4));
        this.level.addFreshEntity(abstractarrow);
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
       return 1.5F;
    }

    protected AbstractArrow getArrow(ItemStack pArrowStack, float pVelocity) {
        return ProjectileUtil.getMobArrow(this, pArrowStack, pVelocity);
    }

    public boolean canFireProjectileWeapon(ProjectileWeaponItem pProjectileWeapon) {
        return pProjectileWeapon == Items.BOW;
    }

    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.reassessWeaponGoal();
    }

    public void setItemSlot(EquipmentSlot pSlot, ItemStack pStack) {
        super.setItemSlot(pSlot, pStack);
        if (!this.level.isClientSide) {
            this.reassessWeaponGoal();
        }

    }

}
