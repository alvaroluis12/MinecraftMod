package com.alvaro.rpgmod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

import com.alvaro.rpgmod.client.ClientManaData;

import it.unimi.dsi.fastutil.ints.IntList;

public class ManaDataSyncS2C {
    private final int mana;
    private final int maxMana;

    public ManaDataSyncS2C(int mana, int maxMana) {
        this.mana = mana;
        this.maxMana = maxMana;
    }

    public ManaDataSyncS2C(FriendlyByteBuf buf){
        IntList list = buf.readIntIdList();
        this.mana = list.getInt(0);
        this.maxMana = list.getInt(1);
    }

    public void toBytes(FriendlyByteBuf buf){
        int[] attr = {this.mana, this.maxMana};
        buf.writeVarIntArray(attr);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT
            ClientManaData.setMana(mana);
            ClientManaData.setMaxMana(maxMana);
        });
        return true;
    }

}
