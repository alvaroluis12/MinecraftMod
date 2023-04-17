package com.alvaro.rpgmod.client;

import com.alvaro.rpgmod.RPGMod;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ManaHudOverlay {
    public static final ResourceLocation FILLED_MANA = new ResourceLocation(RPGMod.MODID, "textures/mana/filled_mana.png");
    public static final ResourceLocation EMPTY_MANA = new ResourceLocation(RPGMod.MODID, "textures/mana/empty_mana.png");
    public static int MAX_MANA;


    public static final IGuiOverlay HUD_MANA = ((gui, poseStack, partialTick, width, height) -> {
        MAX_MANA = ClientStatsData.getPlayerMaxMana();
        int x = width / 2;
        int y = height;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, EMPTY_MANA);
        for(int i = 0; i < MAX_MANA; i++) {
            GuiComponent.blit(poseStack,x - 94 + ((i%10) * 9),y - 54-(i-(i%10)),0,0,12,12,
                    12,12);
        }

        RenderSystem.setShaderTexture(0, FILLED_MANA);
        for(int i = 0; i < MAX_MANA; i++) {
            if(ClientStatsData.getPlayerMana() > i) {
                GuiComponent.blit(poseStack,x - 94 + ((i%10) * 9),y - 54-(i-(i%10)),0,0,12,12,
                        12,12);
            } else {
                break;
            }
        }
    });
}
