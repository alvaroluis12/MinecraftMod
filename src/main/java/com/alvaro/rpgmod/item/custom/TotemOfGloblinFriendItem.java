package com.alvaro.rpgmod.item.custom;

import java.util.List;

import javax.annotation.Nullable;

import com.alvaro.rpgmod.capabilities.items.ItemEffectsProvider;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class TotemOfGloblinFriendItem extends Item{
    private boolean changed = false;
    

    public TotemOfGloblinFriendItem(Properties pProperties) {
        super(pProperties);
    }
    
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(!pLevel.isClientSide() && pUsedHand == InteractionHand.MAIN_HAND){
            if(givePlayerGloblinFriendship(pPlayer)){
                //this.damageItem(getDefaultInstance(), 1, pPlayer, null);
                
                
                this.getDefaultInstance().hurtAndBreak(1, pPlayer, (consumer) -> {
                    consumer.getInventory().setItem(consumer.getInventory().findSlotMatchingItem(getDefaultInstance()), ItemStack.EMPTY);
                    consumer.broadcastBreakEvent(pUsedHand);
                });
                
                
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
    
    private boolean givePlayerGloblinFriendship(Player pPlayer){
        pPlayer.getCapability(ItemEffectsProvider.ITEM_EFFECTS).ifPresent(player -> {
            if (!player.hasGloblinFriendship()){
                player.setGloblinFriend(true);
                this.changed = true;
            }else{
            this.changed = false;
            }
        });
        return this.changed;
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents,
            TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()){
            pTooltipComponents.add(Component.translatable("item.rpgmod.totem_of_globlin_friend.description").withStyle(ChatFormatting.GREEN));
        }
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
    
}
