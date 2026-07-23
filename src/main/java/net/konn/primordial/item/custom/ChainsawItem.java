package net.konn.primordial.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;

public class ChainsawItem extends Item {
    public ChainsawItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();

        if(!level.isClientSide){
            if(level.getBlockState(context.getClickedPos()).is(BlockTags.LOGS)){
                level.destroyBlock(context.getClickedPos(),true,context.getPlayer());
                context.getItemInHand().hurtAndBreak(1,((ServerLevel) level),((ServerPlayer) context.getPlayer()),
                        item -> {
                            assert context.getPlayer() != null;
                            context.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND);
                        });
            }
        }

        return InteractionResult.CONSUME;
    }
}
