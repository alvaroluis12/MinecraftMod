package com.alvaro.rpgmod.screen.quests;

import org.jetbrains.annotations.NotNull;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.client.ClientMissionsData;
import com.alvaro.rpgmod.config.RPGModCommonConfigs;
import com.alvaro.rpgmod.util.KeyBinding;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class QuestsScreen extends AbstractContainerScreen<QuestsMenu>{
    private static int i = 0;
    
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RPGMod.MODID,"textures/gui/quests_gui.png");

    public QuestsScreen(QuestsMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        //int i= this.leftPos;
        //int j = this.topPos;
	
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == KeyBinding.QUESTS_GUI_KEY.getKey().getValue() && this.shouldCloseOnEsc()) {
           this.onClose();
           return true;
        }
        return super.keyPressed(pKeyCode, pScanCode, pModifiers);
    }


    @Override
    public void onClose() {
        super.onClose();
    }

    @Override
    public void removed() {
        
        super.removed();
    }

    @Override
    public void render(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pPoseStack);

        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pPoseStack, pMouseX, pMouseY);

    }


    @Override
    protected void renderBg(@NotNull PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    protected void renderLabels(@NotNull PoseStack pPoseStack, int pMouseX, int pMouseY) {
        this.font.draw(pPoseStack, Component.literal("Quests"), (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
        RPGModCommonConfigs.MISSIONS.get().forEach((mission) -> {
            if (ClientMissionsData.getMissions().contains(Integer.parseInt(mission.get(0)))){
                this.font.draw(pPoseStack, Component.literal(mission.get(0)), (float)this.titleLabelX+20, (float)this.titleLabelY+10*(i+1), 4210752);
                this.font.draw(pPoseStack, Component.literal(mission.get(1)+ " " + mission.get(2) + " 0/" + mission.get(3)), (float)this.titleLabelX+20, (float)this.titleLabelY+10*(i+2), 4210752);
                this.font.draw(pPoseStack, Component.literal("Reward: "+ mission.get(4) + "XP"), (float)this.titleLabelX+20, (float)this.titleLabelY+10*(i+3), 4210752);
                i+=4;
            }
        });
        i = 0;
    }
}
