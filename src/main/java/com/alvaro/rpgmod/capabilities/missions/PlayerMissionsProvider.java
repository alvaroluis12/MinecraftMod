package com.alvaro.rpgmod.capabilities.missions;

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

public class PlayerMissionsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerMissions> PLAYER_MISSIONS = CapabilityManager.get(new CapabilityToken<PlayerMissions>() {});
    private PlayerMissions missions = null;
    private final LazyOptional<PlayerMissions> optional = LazyOptional.of(this::createPlayerMissions);

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createPlayerMissions().saveNBTData(nbt);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createPlayerMissions().loadNBTData(nbt);
    }

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == PLAYER_MISSIONS){
            return optional.cast();
        }

        return LazyOptional.empty();
    }

    private PlayerMissions createPlayerMissions() {
        if (this.missions == null){
            this.missions = new PlayerMissions();
        }
        return this.missions;
    }
    
}
