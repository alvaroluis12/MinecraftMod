package com.alvaro.rpgmod.commands;

import java.util.function.IntFunction;

import javax.annotation.Nullable;

import com.alvaro.rpgmod.capabilities.stats.PlayerStats;

import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;

public enum AttributeType implements StringRepresentable {
   STRENGTH(PlayerStats.STR_INDEX, "strength"),
   DEXTERITY(PlayerStats.DEX_INDEX, "dexterity"),
   CONSTITUTION(PlayerStats.CON_INDEX, "constitution"),
   INTELLIGENCE(PlayerStats.INT_INDEX, "intelligence"),
   WISDOM(PlayerStats.WIS_INDEX, "wisdom"),
   LEVEL(PlayerStats.LEVEL_INDEX, "level"),
   POINTS(PlayerStats.POINTS_INDEX, "points"),
   MANA(PlayerStats.MANA_INDEX, "mana"),
   MAX_MANA(PlayerStats.MAX_MANA_INDEX, "max_mana"),
   XP(PlayerStats.XP_INDEX, "xp");

   //public static final StringRepresentable.EnumCodec<AttributeType> CODEC = StringRepresentable.fromEnum(AttributeType::values);
   private static final IntFunction<AttributeType> BY_ID = ByIdMap.continuous(AttributeType::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
   private final int id;
   private final String name;


   private AttributeType(int pId, String pName) {
      this.id = pId;
      this.name = pName;
   }
   public int getId() {
      return this.id;
   }
   public String getName() {
      return this.name;
   }

   public String getSerializedName() {
      return this.name;
   }
   public static AttributeType byId(int pId) {
      return BY_ID.apply(pId);
   }
   public static AttributeType byName(String attributeName) {
      return byName(attributeName, LEVEL);
   }

   @Nullable
   public static AttributeType byName(String pTargetName, @Nullable AttributeType pFallback) {
      AttributeType attributetype = AttributeType.valueOf(pTargetName.toUpperCase());//CODEC.byName(pTargetName);
      return attributetype != null ? attributetype : pFallback;
   }

   public static int getNullableId(@Nullable AttributeType attributetype) {
      return attributetype != null ? attributetype.id : -1;
   }

   @Nullable
   public static AttributeType byNullableId(int pId) {
      return pId == -1 ? null : byId(pId);
   }
}
