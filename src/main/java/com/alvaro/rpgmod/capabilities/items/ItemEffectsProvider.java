package com.alvaro.rpgmod.capabilities.items;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class ItemEffectsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<ItemEffects> ITEM_EFFECTS = CapabilityManager.get(new CapabilityToken<ItemEffects>() {});
    private ItemEffects effects = null;
    private final LazyOptional<ItemEffects> optional = LazyOptional.of(this::createItemEffects);

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createItemEffects().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createItemEffects().loadNBTData(nbt);
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ITEM_EFFECTS){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    private ItemEffects createItemEffects() {
        if (this.effects == null){
            this.effects = new ItemEffects();
        }
        return this.effects;
    }
    
}
