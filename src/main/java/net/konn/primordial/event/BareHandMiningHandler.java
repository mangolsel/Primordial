package net.konn.primordial.event;

import net.konn.primordial.util.PrimordialTags;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class BareHandMiningHandler {

    private static final int DAMAGE_INTERVAL_TICKS = 5;
    private static final float DAMAGE_AMOUNT = 1.0F;
    private static final double MAX_DISTANCE_SQUARED = 36.0D;

    private final Map<UUID, MiningState> activeMining = new HashMap<>();

    @SubscribeEvent
    public void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        switch (event.getAction()) {
            case START -> startMining(player, event.getPos());

            case STOP, ABORT ->
                    activeMining.remove(player.getUUID());

            case CLIENT_HOLD -> {
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) {
            return;
        }

        MiningState mining = activeMining.get(player.getUUID());
        if (mining == null) {
            return;
        }

        if (!canContinueMining(player, mining)) {
            activeMining.remove(player.getUUID());
            return;
        }

        BlockState blockState = player.level().getBlockState(mining.blockPos);
        ItemStack heldItem = player.getMainHandItem();

        if (!injuresHands(blockState, heldItem)) {
            mining.unsafeTicks = 0;
            return;
        }

        mining.unsafeTicks++;

        if (mining.unsafeTicks >= DAMAGE_INTERVAL_TICKS) {
            player.hurt(
                    player.damageSources().generic(),
                    DAMAGE_AMOUNT
            );

            mining.unsafeTicks = 0;
        }
    }

    @SubscribeEvent
    public void onPlayerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        activeMining.remove(event.getEntity().getUUID());
    }

    private void startMining(ServerPlayer player, BlockPos blockPos) {
        BlockState blockState = player.level().getBlockState(blockPos);

        if (!isDangerousBlock(blockState)) {
            activeMining.remove(player.getUUID());
            return;
        }

        activeMining.put(
                player.getUUID(),
                new MiningState(
                        blockPos.immutable(),
                        player.level().dimension()
                )
        );
    }

    private boolean canContinueMining(
            ServerPlayer player,
            MiningState mining
    ) {
        if (!player.isAlive()
                || player.isCreative()
                || player.isSpectator()) {
            return false;
        }

        if (!player.level().dimension().equals(mining.dimension)) {
            return false;
        }

        if (!player.level().hasChunkAt(mining.blockPos)) {
            return false;
        }

        double distanceSquared = player.position().distanceToSqr(
                Vec3.atCenterOf(mining.blockPos)
        );

        if (distanceSquared > MAX_DISTANCE_SQUARED) {
            return false;
        }

        BlockState state = player.level().getBlockState(mining.blockPos);

        return !state.isAir() && isDangerousBlock(state);
    }

    private boolean isDangerousBlock(BlockState state) {
        return state.is(PrimordialTags.Blocks.INJURES_BARE_HANDS_WOOD)
                || state.is(PrimordialTags.Blocks.INJURES_BARE_HANDS_STONE);
    }

    private boolean injuresHands(
            BlockState state,
            ItemStack heldItem
    ) {
        boolean unsafeWood = state.is(
                PrimordialTags.Blocks.INJURES_BARE_HANDS_WOOD
        ) && !heldItem.is(
                PrimordialTags.Items.PROTECTS_HANDS_FROM_WOOD
        );

        boolean unsafeStone = state.is(
                PrimordialTags.Blocks.INJURES_BARE_HANDS_STONE
        ) && !heldItem.is(
                PrimordialTags.Items.PROTECTS_HANDS_FROM_STONE
        );

        return unsafeWood || unsafeStone;
    }

    private static final class MiningState {
        private final BlockPos blockPos;
        private final ResourceKey<Level> dimension;
        private int unsafeTicks;

        private MiningState(
                BlockPos blockPos,
                ResourceKey<Level> dimension
        ) {
            this.blockPos = blockPos;
            this.dimension = dimension;
        }
    }
}
