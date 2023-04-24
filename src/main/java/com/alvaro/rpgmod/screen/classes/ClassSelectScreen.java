package com.alvaro.rpgmod.screen.classes;

import org.jetbrains.annotations.NotNull;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.capabilities.stats.PlayerStats;
import com.alvaro.rpgmod.networking.ModMessages;
import com.alvaro.rpgmod.networking.packet.SelectClassC2SPacket;
import com.alvaro.rpgmod.screen.button.AddButton;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class ClassSelectScreen extends AbstractContainerScreen<ClassSelectMenu>{
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RPGMod.MODID,"textures/gui/stats_gui.png");

    public ClassSelectScreen(ClassSelectMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        int i= this.leftPos;
        int j = this.topPos;

        this.addRenderableWidget(new AddButton(
                i+10,
                j+20,
                70,
                20,
                Component.translatable("rpgmod.classes.mage"),
                (button) -> {
                    this.selectClass(PlayerStats.MAGE);
                },
                (button) -> {
                    return Component.translatable("rpgmod.select_mage");
                }));

        this.addRenderableWidget(new AddButton(
                i+10,
                j+50,
                70,
                20,
                Component.translatable("rpgmod.classes.druid"),
                (button) -> {
                    this.selectClass(PlayerStats.DRUID);
                },
                (button) -> {
                    return Component.translatable("rpgmod.select_druid");
                }));

        this.addRenderableWidget(new AddButton(
                i+10,
                j+80,
                70,
                20,
                Component.translatable("rpgmod.classes.summoner"),
                (button) -> {
                    this.selectClass(PlayerStats.SUMMONER);
                },
                (button) -> {
                    return Component.translatable("rpgmod.select_summoner");
                }));

        this.addRenderableWidget(new AddButton(
                i+100,
                j+20,
                70,
                20,
                Component.translatable("rpgmod.classes.ranger"),
                (button) -> {
                    this.selectClass(PlayerStats.RANGER);
                },
                (button) -> {
                    return Component.translatable("rpgmod.select_ranger");
                }));

        this.addRenderableWidget(new AddButton(
                i+100,
                j+50,
                70,
                20,
                Component.translatable("rpgmod.classes.paladin"),
                (button) -> {
                    this.selectClass(PlayerStats.PALADIN);
                },
                (button) -> {
                    return Component.translatable("rpgmod.select_paladin");
                }));
                
        this.addRenderableWidget(new AddButton(
                i+100,
                j+80,
                70,
                20,
                Component.translatable("rpgmod.classes.berserker"),
                (button) -> {
                    this.selectClass(PlayerStats.BERSERKER);
                },
                (button) -> {
                    return Component.translatable("rpgmod.select_berserker");
                }));
	
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        return false;
    }

    private void selectClass(int playerClass) {
        ModMessages.sendToServer(new SelectClassC2SPacket(playerClass));
        onClose();
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
        this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
    }
}
