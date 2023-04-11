package com.alvaro.rpgmod.util;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBinding {
    public static final String KEY_CATEGORY_RPG = "key.category.rpgmod.test";
    public static final String KEY_TEST = "key.rpgmod.test";

    public static final KeyMapping TEST_KEY = new KeyMapping(KEY_TEST, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_RPG);
    
}
