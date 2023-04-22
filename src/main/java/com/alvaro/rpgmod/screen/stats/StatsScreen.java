package com.alvaro.rpgmod.screen.stats;

import com.alvaro.rpgmod.RPGMod;
import com.alvaro.rpgmod.capabilities.stats.PlayerStats;
import com.alvaro.rpgmod.client.ClientStatsData;
import com.alvaro.rpgmod.networking.ModMessages;
import com.alvaro.rpgmod.networking.packet.addAttributeC2SPacket;
import com.alvaro.rpgmod.screen.button.AddButton;
import com.alvaro.rpgmod.util.KeyBinding;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

import org.jetbrains.annotations.NotNull;

public class StatsScreen extends AbstractContainerScreen<StatsMenu>{
    private final Player player;
    private final String[] classesList = new String[]{"Mage", "Druid", "Summoner", "Ranger", "Paladin", "Berserker"};
    
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(RPGMod.MODID,"textures/gui/stats_gui.png");

    public StatsScreen(StatsMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.player = pPlayerInventory.player;
    }

    @Override
    protected void init() {
        super.init();
        int i= this.leftPos;
        int j = this.topPos;

        this.addRenderableWidget(new AddButton(
                i+(166/2)-20,
                j+20,
                20,
                20,
                Component.literal("+"),
                (button) -> {
                    this.addAttribute(PlayerStats.STR_INDEX);
                },
                (button) -> {
                    return Component.translatable("rpgmod.str_add");
                }));

        this.addRenderableWidget(new AddButton(
                i+(166/2)-20,
                j+50,
                20,
                20,
                Component.literal("+"),
                (button) -> {
                    this.addAttribute(PlayerStats.DEX_INDEX);
                },
                (button) -> {
                    return Component.translatable("rpgmod.dex_add");
                }));

        this.addRenderableWidget(new AddButton(
                i+(166/2)-20,
                j+80,
                20,
                20,
                Component.literal("+"),
                (button) -> {
                    this.addAttribute(PlayerStats.CON_INDEX);
                },
                (button) -> {
                    return Component.translatable("rpgmod.con_add");
                }));

        this.addRenderableWidget(new AddButton(
                i+166-20,
                j+20,
                20,
                20,
                Component.literal("+"),
                (button) -> {
                    this.addAttribute(PlayerStats.INT_INDEX);
                },
                (button) -> {
                    return Component.translatable("rpgmod.int_add");
                }));

        this.addRenderableWidget(new AddButton(
                i+166-20,
                j+50,
                20,
                20,
                Component.literal("+"),
                (button) -> {
                    this.addAttribute(PlayerStats.WIS_INDEX);
                },
                (button) -> {
                    return Component.translatable("rpgmod.wis_add");
                }));
	
    }

    private void addAttribute(int attribute) {
        ModMessages.sendToServer(new addAttributeC2SPacket(attribute));
    }

    @Override
    public boolean keyPressed(int pKeyCode, int pScanCode, int pModifiers) {
        if (pKeyCode == KeyBinding.GUI_KEY.getKey().getValue() && this.shouldCloseOnEsc()) {
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
        this.font.draw(pPoseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 4210752);
        this.font.draw(pPoseStack, Component.literal("Points: " + ClientStatsData.getPoints()), (float)this.titleLabelX+83, (float)this.titleLabelY, 4210752);
        this.font.draw(pPoseStack, Component.literal("Str: "+ ClientStatsData.getStrength()), (float)this.titleLabelX, (float)this.titleLabelY+20, 4210752);
        this.font.draw(pPoseStack, Component.literal("Dex: "+ ClientStatsData.getDexterity()), (float)this.titleLabelX, (float)this.titleLabelY+50, 4210752);
        this.font.draw(pPoseStack, Component.literal("Con: "+ ClientStatsData.getConstitution()), (float)this.titleLabelX, (float)this.titleLabelY+80, 4210752);
        this.font.draw(pPoseStack, Component.literal("Int: "+ ClientStatsData.getIntelligence()), (float)this.titleLabelX+83, (float)this.titleLabelY+20, 4210752);
        this.font.draw(pPoseStack, Component.literal("Wis: "+ ClientStatsData.getWisdom()), (float)this.titleLabelX+83, (float)this.titleLabelY+50, 4210752);
        this.font.draw(pPoseStack, Component.literal("HP: "+ Math.round(player.getHealth()) + "/" + player.getAttributeBaseValue(Attributes.MAX_HEALTH)), (float)this.titleLabelX, (float)this.titleLabelY+110, 4210752);
        this.font.draw(pPoseStack, Component.literal("MP: "+ ClientStatsData.getPlayerMana() + "/" + ClientStatsData.getPlayerMaxMana()), (float)this.titleLabelX, (float)this.titleLabelY+140, 4210752);
        this.font.draw(pPoseStack, Component.literal("Class: "+ classesList[ClientStatsData.getPlayerClass()]), (float)this.titleLabelX+83, (float)this.titleLabelY+110, 4210752);
        this.font.draw(pPoseStack, Component.literal("XP: "+ ClientStatsData.getXp() + "/" + ClientStatsData.getXpNecessary()), (float)this.titleLabelX+83, (float)this.titleLabelY+140, 4210752);
        
    
    }
}
