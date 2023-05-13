package com.alvaro.rpgmod.util;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class KeyBinding {
    public static final String KEY_CATEGORY_RPG = "key.category.rpgmod";

    public static final String KEY_TEST = "key.rpgmod.test";
    public static final String KEY_STATS_GUI = "key.rpgmod.stats_gui";
    public static final String KEY_QUESTS_GUI = "key.rpgmod.quests_gui";
    public static final String KEY_SKILL_1 = "key.rpgmod.skill1";
    public static final String KEY_SKILL_2 = "key.rpgmod.skill2";
    public static final String KEY_SKILL_3 = "key.rpgmod.skill3";
    public static final String KEY_SKILL_4 = "key.rpgmod.skill4";

    public static final KeyMapping TEST_KEY = new KeyMapping(KEY_TEST, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_RPG);
    public static final KeyMapping STATS_GUI_KEY = new KeyMapping(KEY_STATS_GUI, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_I, KEY_CATEGORY_RPG);
    public static final KeyMapping QUESTS_GUI_KEY = new KeyMapping(KEY_QUESTS_GUI, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_CAPS_LOCK, KEY_CATEGORY_RPG);
    public static final KeyMapping SKILL_1_KEY = new KeyMapping(KEY_SKILL_1, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_N, KEY_CATEGORY_RPG);
    public static final KeyMapping SKILL_2_KEY = new KeyMapping(KEY_SKILL_2, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORY_RPG);
    public static final KeyMapping SKILL_3_KEY = new KeyMapping(KEY_SKILL_3, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_H, KEY_CATEGORY_RPG);
    public static final KeyMapping SKILL_4_KEY = new KeyMapping(KEY_SKILL_4, KeyConflictContext.IN_GAME, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_J, KEY_CATEGORY_RPG);
    
}
