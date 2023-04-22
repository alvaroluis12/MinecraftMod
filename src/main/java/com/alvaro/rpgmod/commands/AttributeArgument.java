package com.alvaro.rpgmod.commands;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;

public class AttributeArgument implements ArgumentType<AttributeType> {
    private static final Collection<String> EXAMPLES = Stream.of(AttributeType.LEVEL, AttributeType.POINTS).map(AttributeType::getName).collect(Collectors.toList());
    private static final AttributeType[] VALUES = AttributeType.values();
    private static final DynamicCommandExceptionType ERROR_INVALID = new DynamicCommandExceptionType((p_260119_) -> {
       return Component.translatable("rpgmod.argument.attribute.invalid", p_260119_);
    });
 
    public AttributeType parse(StringReader pReader) throws CommandSyntaxException {
       String s = pReader.readUnquotedString();
       AttributeType attributetype = AttributeType.byName(s, (AttributeType)null);
       if (attributetype == null) {
          throw ERROR_INVALID.createWithContext(pReader, s);
       } else {
          return attributetype;
       }
    }
 
    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> pContext, SuggestionsBuilder pBuilder) {
       return pContext.getSource() instanceof SharedSuggestionProvider ? SharedSuggestionProvider.suggest(Arrays.stream(VALUES).map(AttributeType::getName), pBuilder) : Suggestions.empty();
    }
 
    public Collection<String> getExamples() {
       return EXAMPLES;
    }
 
    public static AttributeArgument attribute() {
       return new AttributeArgument();
    }
 
    public static AttributeType getAttribute(CommandContext<CommandSourceStack> pContext, String pName) throws CommandSyntaxException {
       return pContext.getArgument(pName, AttributeType.class);
    }
 }