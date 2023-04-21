package com.alvaro.rpgmod.screen.button;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class AddButton extends Button{

    public AddButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, OnPress pOnPress) {
        super(pX,
              pY,
              pWidth,
              pHeight,
              pMessage,
              pOnPress,
              DEFAULT_NARRATION);
    }

    public AddButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, OnPress pOnPress,
            CreateNarration pCreateNarration) {
        super(pX,
              pY,
              pWidth,
              pHeight,
              pMessage,
              pOnPress,
              pCreateNarration);
    }

}
