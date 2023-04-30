package com.alvaro.rpgmod.capabilities.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class ItemEffects {
    private boolean globlinFriend = false;

    public void setGloblinFriend(boolean friend){
        this.globlinFriend = friend;
    }
    public boolean hasGloblinFriendship(){
        return globlinFriend;
    }

    public void copyFrom(ItemEffects source) {
        this.globlinFriend = source.globlinFriend;

    }
    public void saveNBTData(CompoundTag nbt){
        nbt.putBoolean("globlin_friend", globlinFriend);

    }
    public void loadNBTData(CompoundTag nbt){
        globlinFriend = nbt.getBoolean("globlin_friend");
    }
}
