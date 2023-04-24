package com.alvaro.rpgmod.capabilities.skills.Berserker;

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

public class BerserSkillsProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<BerserkerSkills> BERSERKER_SKILLS = CapabilityManager.get(new CapabilityToken<BerserkerSkills>() {});
    private BerserkerSkills skills = null;
    private final LazyOptional<BerserkerSkills> optional = LazyOptional.of(this::createBerserkerSkills);

    @Override
    public <T> @NotNull LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == BERSERKER_SKILLS){
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createBerserkerSkills().loadNBTData(nbt);
        
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createBerserkerSkills().saveNBTData(nbt);
        return nbt;
    }

    private BerserkerSkills createBerserkerSkills(){
        if (this.skills == null){
            this.skills = new BerserkerSkills();
        }
        return this.skills;
    }
    
}
