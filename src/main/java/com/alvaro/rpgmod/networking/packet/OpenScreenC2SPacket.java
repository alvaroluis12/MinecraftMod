package com.alvaro.rpgmod.networking.packet;

import java.util.function.Supplier;

import com.alvaro.rpgmod.client.ClientStatsData;
import com.alvaro.rpgmod.screen.classes.ClassSelectMenu;
import com.alvaro.rpgmod.screen.quests.QuestsMenu;
import com.alvaro.rpgmod.screen.stats.StatsMenu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkHooks;

public class OpenScreenC2SPacket {
    /*
     * 0 = Stats gui
     * 1 = Classes Select gui
     * 2 = Quests gui
     */
    private final int screen;
    public OpenScreenC2SPacket(int screen) {
        this.screen = screen;
    }

    public OpenScreenC2SPacket(FriendlyByteBuf buf){
        this.screen = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(this.screen);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            switch(this.screen){
                case 0:
                    NetworkHooks.openScreen(player, new SimpleMenuProvider(
                        (containerId, playerInventory, nul) -> new StatsMenu(containerId, playerInventory),
                        Component.literal("Level: " + ClientStatsData.getLevel())
                    ));
                    break;
                case 1:
                    NetworkHooks.openScreen(player, new SimpleMenuProvider(
                        (containerId, playerInventory, nul) -> new ClassSelectMenu(containerId, playerInventory),
                        Component.literal("Select a class")
                    ));
                    break;
                case 2:
                    NetworkHooks.openScreen(player, new SimpleMenuProvider(
                        (containerId, playerInventory, nul) -> new QuestsMenu(containerId, playerInventory),
                        Component.literal("Quests")
                    ));
                    break;

            }

            
        });
    }
    
}
