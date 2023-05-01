package com.alvaro.rpgmod.commands;


import com.alvaro.rpgmod.capabilities.stats.PlayerStats;
import com.alvaro.rpgmod.capabilities.stats.PlayerStatsProvider;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameRules;

public class AttributesCommand {
    private static boolean changed = true;

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher){
        dispatcher.register(Commands.literal("modattribute").requires((player) -> {
            return player.hasPermission(2);
        }).then(Commands.literal("add").then(Commands.argument("attribute", AttributeArgument.attribute()).executes((command0) -> {
            return addAttribute(command0.getSource(), AttributeArgument.getAttribute(command0, "attribute"), 1, command0.getSource().getPlayerOrException());
        }).then(Commands.argument("value", IntegerArgumentType.integer(0)).executes((command1) -> {
            return addAttribute(command1.getSource(), AttributeArgument.getAttribute(command1, "attribute"), IntegerArgumentType.getInteger(command1, "value"), command1.getSource().getPlayerOrException());
        }).then(Commands.argument("target", EntityArgument.player()).executes((command2) -> {
            return addAttribute(command2.getSource(), AttributeArgument.getAttribute(command2, "attribute"), IntegerArgumentType.getInteger(command2, "value"), EntityArgument.getPlayer(command2, "target"));
        })))))
        .then(Commands.literal("set").then(Commands.argument("attribute", AttributeArgument.attribute()).executes((command0) -> {
            return setAttribute(command0.getSource(), AttributeArgument.getAttribute(command0, "attribute"), 1, command0.getSource().getPlayerOrException());
        }).then(Commands.argument("value", IntegerArgumentType.integer(0)).executes((command1) -> {
            return setAttribute(command1.getSource(), AttributeArgument.getAttribute(command1, "attribute"), IntegerArgumentType.getInteger(command1, "value"), command1.getSource().getPlayerOrException());
        }).then(Commands.argument("target", EntityArgument.player()).executes((command2) -> {
            return setAttribute(command2.getSource(), AttributeArgument.getAttribute(command2, "attribute"), IntegerArgumentType.getInteger(command2, "value"), EntityArgument.getPlayer(command2, "target"));
        })))))
        .then(Commands.literal("remove").then(Commands.argument("attribute", AttributeArgument.attribute()).executes((command0) -> {
            return removeAttribute(command0.getSource(), AttributeArgument.getAttribute(command0, "attribute"), 1, command0.getSource().getPlayerOrException());
        }).then(Commands.argument("value", IntegerArgumentType.integer(0)).executes((command1) -> {
            return removeAttribute(command1.getSource(), AttributeArgument.getAttribute(command1, "attribute"), IntegerArgumentType.getInteger(command1, "value"), command1.getSource().getPlayerOrException());
        }).then(Commands.argument("target", EntityArgument.player()).executes((command2) -> {
            return removeAttribute(command2.getSource(), AttributeArgument.getAttribute(command2, "attribute"), IntegerArgumentType.getInteger(command2, "value"), EntityArgument.getPlayer(command2, "target"));
        })))))
        );
    }
    
    private static void logAttributeChange(CommandSourceStack pSource, ServerPlayer pPlayer, AttributeType pAttributeType) {
       Component component = Component.translatable("rpgmod.attribute." + pAttributeType.getName());
       if (pSource.getEntity() == pPlayer) {
          pSource.sendSuccess(Component.translatable("rpgmod.commands.attribute.success.self", component), true);
       } else {
          if (pSource.getLevel().getGameRules().getBoolean(GameRules.RULE_SENDCOMMANDFEEDBACK)) {
             pPlayer.sendSystemMessage(Component.translatable("rpgmod.attribute.changed", component));
          }
 
          pSource.sendSuccess(Component.translatable("rpgmod.commands.attribute.success.other", pPlayer.getDisplayName(), component), true);
       }
 
    }

    private static int addAttribute(CommandSourceStack source, AttributeType attribute, int value, ServerPlayer target){
        int i = 0;
        target.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
            //boolean changed = false;
            switch(attribute.getId()){
                case PlayerStats.STR_INDEX:
                    stats.addStrength(value);
                    break;
                case PlayerStats.CON_INDEX:
                    stats.addCon(value);
                    break;
                case PlayerStats.DEX_INDEX:
                    stats.addDex(value);
                    break;
                case PlayerStats.INT_INDEX:
                    stats.addIntelligence(value);
                    break;
                case PlayerStats.WIS_INDEX:
                    stats.addWisdom(value);
                    break;
                case PlayerStats.LEVEL_INDEX:
                    stats.addLevel(value);
                    break;
                case PlayerStats.MANA_INDEX:
                    stats.addMana(value);
                    break;
                case PlayerStats.MAX_MANA_INDEX:
                    stats.addMaxMana(value);
                    break;
                case PlayerStats.XP_INDEX:
                    stats.addXp(value);
                    break;
                default:
                    changed = false;
                    break;
            }
        });
        if (changed){
            ++i;
            logAttributeChange(source, target, attribute);
        }
        return i;
    }

    private static int setAttribute(CommandSourceStack source, AttributeType attribute, int value, ServerPlayer target){
        int i = 0;
        target.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
            //boolean changed = false;
            switch(attribute.getId()){
                case PlayerStats.STR_INDEX:
                    stats.setStrength(value);
                    break;
                case PlayerStats.CON_INDEX:
                    stats.setCon(value);
                    break;
                case PlayerStats.DEX_INDEX:
                    stats.setDex(value);
                    break;
                case PlayerStats.INT_INDEX:
                    stats.setIntelligence(value);
                    break;
                case PlayerStats.WIS_INDEX:
                    stats.setWisdom(value);
                    break;
                case PlayerStats.LEVEL_INDEX:
                    stats.setLevel(value);
                    break;
                case PlayerStats.MANA_INDEX:
                    stats.setMana(value);
                    break;
                case PlayerStats.MAX_MANA_INDEX:
                    stats.setMaxMana(value);
                    break;
                case PlayerStats.XP_INDEX:
                    stats.setXp(value);
                    break;
                default:
                    changed = false;
                    break;
            }
        });
        if (changed){
            ++i;
            logAttributeChange(source, target, attribute);
        }
        return i;
    }
    private static int removeAttribute(CommandSourceStack source, AttributeType attribute, int value, ServerPlayer target){
        int i = 0;
        target.getCapability(PlayerStatsProvider.PLAYER_STATS).ifPresent(stats -> {
            //boolean changed = false;
            switch(attribute.getId()){
                case PlayerStats.STR_INDEX:
                    stats.subStrength(value);
                    break;
                case PlayerStats.CON_INDEX:
                    stats.subConstitution(value);
                    break;
                case PlayerStats.DEX_INDEX:
                    stats.subDexterity(value);
                    break;
                case PlayerStats.INT_INDEX:
                    stats.subIntelligence(value);
                    break;
                case PlayerStats.WIS_INDEX:
                    stats.subWisdom(value);
                    break;
                case PlayerStats.LEVEL_INDEX:
                    stats.subLevel(value);
                    break;
                case PlayerStats.MANA_INDEX:
                    stats.subMana(value);
                    break;
                case PlayerStats.MAX_MANA_INDEX:
                    stats.subMaxMana(value);
                    break;
                case PlayerStats.XP_INDEX:
                    stats.subXp(value);
                    break;
                default:
                    changed = false;
                    break;
            }
        });
        if (changed){
            ++i;
            logAttributeChange(source, target, attribute);
        }
        return i;
    }
}
