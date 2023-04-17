package com.alvaro.rpgmod.capabilities.stats;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;

public class PlayerStatsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerStats> PLAYER_STATS = CapabilityManager.get(new CapabilityToken<PlayerStats>() {});
    private PlayerStats stats = null;
    private final LazyOptional<PlayerStats> optional = LazyOptional.of(this::createPlayerStats);

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerStats().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerStats().loadNBTData(nbt);
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_STATS){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    private PlayerStats createPlayerStats() {
        if (this.stats == null){
            this.stats = new PlayerStats();
        }
        return this.stats;
    }
    
}
