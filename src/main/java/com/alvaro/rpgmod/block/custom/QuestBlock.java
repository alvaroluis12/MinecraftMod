package com.alvaro.rpgmod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class QuestBlock extends Block {

    public QuestBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand,
            BlockHitResult pHit) {
    
        // Server: Main Hand & Off Hand
        // Client: Main Hand & Off Hand

        pPlayer.sendSystemMessage(Component.literal("Right Clicked this!"));

        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }
    
}
