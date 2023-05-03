package com.alvaro.rpgmod.screen.quests;

import org.jetbrains.annotations.NotNull;

import com.alvaro.rpgmod.screen.ModMenuTypes;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;

public class QuestsMenu extends AbstractContainerMenu{

    public QuestsMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf) {
        this(ModMenuTypes.Quests_Menu.get(), pContainerId, inv);
    }

    public QuestsMenu(int pContainerId, Inventory inv){
        this(ModMenuTypes.Quests_Menu.get(), pContainerId, inv);
    }

    public QuestsMenu(MenuType<?> pMenuType, int pContainerId, Inventory inv) {
        super(pMenuType, pContainerId);
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player pPlayer, int pIndex) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(@NotNull Player pPlayer) {
        return true;
    }
    
}
