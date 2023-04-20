package com.alvaro.rpgmod.util;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBinding {
    public static final String KEY_CATEGORY_RPG = "key.category.rpgmod";

    public static final String KEY_TEST = "key.rpgmod.test";
    public static final String KEY_GUI = "key.rpgmod.gui";

    public static final KeyMapping TEST_KEY = new KeyMapping(KEY_TEST, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_RPG);
    public static final KeyMapping GUI_KEY = new KeyMapping(KEY_GUI, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, KEY_CATEGORY_RPG);
    
}
