package com.alvaro.rpgmod.screen.classes;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import com.alvaro.rpgmod.screen.ModMenuTypes;

public class ClassSelectMenu extends AbstractContainerMenu{

    public ClassSelectMenu(int pContainerId, Inventory inv, FriendlyByteBuf buf) {
        this(ModMenuTypes.Class_Select_Menu.get(), pContainerId, inv);
    }

    public ClassSelectMenu(int pContainerId, Inventory inv){
        this(ModMenuTypes.Class_Select_Menu.get(), pContainerId, inv);
    }

    public ClassSelectMenu(MenuType<?> pMenuType, int pContainerId, Inventory inv) {
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
