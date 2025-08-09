package io.izzel.arclight.common.bridge.bukkit;

import net.minecraft.world.InteractionHand;
import org.jetbrains.annotations.Nullable;

public interface CraftEventFactoryBridge {

    void arclight$callPlayerUseUnknownEntityEvent(net.minecraft.world.entity.player.Player player, net.minecraft.network.protocol.game.ServerboundInteractPacket packet, InteractionHand hand, @Nullable net.minecraft.world.phys.Vec3 vector);
}