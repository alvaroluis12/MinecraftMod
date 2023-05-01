package com.alvaro.rpgmod.entity.client.monster.globlin;

import javax.annotation.Nullable;

import com.alvaro.rpgmod.entity.custom.globlin.GloblinLordEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.DynamicGeoEntityRenderer;
import software.bernie.geckolib.renderer.layer.BlockAndItemGeoLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;

public class GloblinLordRenderer extends DynamicGeoEntityRenderer<GloblinLordEntity> {

    private static final String RIGHT_HAND = "righthand";
    private static final String LEFT_HAND = "lefthand";

    protected ItemStack mainHandItem;
	protected ItemStack offhandItem;

    public GloblinLordRenderer(Context renderManager) {
        super(renderManager, new GloblinLordModel());
        // Add some held item rendering
		addRenderLayer(new BlockAndItemGeoLayer<>(this) {
			@Nullable
			@Override
			protected ItemStack getStackForBone(GeoBone bone, GloblinLordEntity animatable) {
				// Retrieve the items in the entity's hands for the relevant bone
				return switch (bone.getName()) {
					case LEFT_HAND -> animatable.isLeftHanded() ?
					GloblinLordRenderer.this.mainHandItem : GloblinLordRenderer.this.offhandItem;
					case RIGHT_HAND -> animatable.isLeftHanded() ?
					GloblinLordRenderer.this.offhandItem : GloblinLordRenderer.this.mainHandItem;
					default -> null;
				};
			}

			@Override
			protected ItemDisplayContext getTransformTypeForStack(GeoBone bone, ItemStack stack, GloblinLordEntity animatable) {
				// Apply the camera transform for the given hand
				return switch (bone.getName()) {
					case LEFT_HAND, RIGHT_HAND -> ItemDisplayContext.THIRD_PERSON_RIGHT_HAND;
					default -> ItemDisplayContext.NONE;
				};
			}

			// Do some quick render modifications depending on what the item is
			@Override
			protected void renderStackForBone(PoseStack poseStack, GeoBone bone, ItemStack stack, GloblinLordEntity animatable,
											  MultiBufferSource bufferSource, float partialTick, int packedLight, int packedOverlay) {
				if (stack == GloblinLordRenderer.this.mainHandItem) {
					poseStack.mulPose(Axis.XP.rotationDegrees(-90f));

					if (stack.getItem() instanceof ShieldItem)
						poseStack.translate(0, 0.125, -0.25);
				}
				else if (stack == GloblinLordRenderer.this.offhandItem) {
					poseStack.mulPose(Axis.XP.rotationDegrees(-90f));

					if (stack.getItem() instanceof ShieldItem) {
						poseStack.translate(0, 0.125, 0.25);
						poseStack.mulPose(Axis.YP.rotationDegrees(180));
					}
				}

				super.renderStackForBone(poseStack, bone, stack, animatable, bufferSource, partialTick, packedLight, packedOverlay);
			}
        });
    }

    @Override
    public void preRender(PoseStack poseStack, GloblinLordEntity animatable, BakedGeoModel model,
            MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick,
            int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        super.preRender(poseStack, animatable, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay,
                red, green, blue, alpha);
        
        this.mainHandItem = animatable.getMainHandItem();
    }

}
