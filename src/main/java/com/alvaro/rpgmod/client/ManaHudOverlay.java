package com.alvaro.rpgmod.client;

import com.alvaro.rpgmod.RPGMod;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FastColor;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;


public class ManaHudOverlay {
    public static final ResourceLocation FILLED_MANA = new ResourceLocation(RPGMod.MODID, "textures/mana/filled_mana.png");
    public static final ResourceLocation EMPTY_MANA = new ResourceLocation(RPGMod.MODID, "textures/mana/empty_mana.png");
    public static final ResourceLocation BAR = new ResourceLocation("textures/gui/bars.png");
    public static int MAX_MANA;


    public static final IGuiOverlay HUD_MANA = (gui, poseStack, partialTick, width, height) -> {
        MAX_MANA = 50;
        int x = 0;
        int y = 0;
        int topY = y + height;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);


        RenderSystem.setShaderTexture(0, BAR);

        gui.setupOverlayRenderState(true, false);
        //GuiComponent.fill(poseStack, x-94, y-54, x, y-34, -16777216);
        GuiComponent.fill(poseStack,
                   x+12,
                   topY -21,
                   x+116,
                   topY -2,
                          FastColor.ARGB32.color(255, 86, 94, 98));

        GuiComponent.fill(poseStack,
                   x+14,
                   topY -19,
                   x+14+(100*ClientStatsData.getPlayerMana()/Math.max(ClientStatsData.getPlayerMaxMana(), 20)),
                   topY -4,
                          FastColor.ARGB32.color(255, 46, 199, 221));
        
        GuiComponent.drawCenteredString(poseStack,
                                        Minecraft.getInstance().font,
                                  ClientStatsData.getPlayerMana()+"/"+ClientStatsData.getPlayerMaxMana(),
                                    x+57,
                                    topY -15,
                                        FastColor.ARGB32.color(255, 255, 255, 255));
    };
}
