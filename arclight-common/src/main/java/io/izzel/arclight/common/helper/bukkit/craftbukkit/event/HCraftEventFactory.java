package io.izzel.arclight.common.helper.bukkit.craftbukkit.event;

import com.destroystokyo.paper.event.player.PlayerUseUnknownEntityEvent;
import io.izzel.arclight.common.bridge.core.entity.player.ServerPlayerEntityBridge;
import io.izzel.arclight.common.bridge.core.network.protocol.game.ServerboundInteractPacketBridge;
import net.minecraft.world.InteractionHand;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v.CraftEquipmentSlot;
import org.bukkit.craftbukkit.v.util.CraftVector;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;

public class HCraftEventFactory {

    // Paper start - PlayerUseUnknownEntityEvent
    public static void callPlayerUseUnknownEntityEvent(net.minecraft.world.entity.player.Player player, net.minecraft.network.protocol.game.ServerboundInteractPacket packet, InteractionHand hand, @Nullable net.minecraft.world.phys.Vec3 vector) {
        ServerboundInteractPacketBridge bridge = (ServerboundInteractPacketBridge) packet;
        Player bukkitPlayer = ((ServerPlayerEntityBridge) player).bridge$getBukkitEntity();
        PlayerUseUnknownEntityEvent event = new PlayerUseUnknownEntityEvent(
                bukkitPlayer,
                bridge.getEntityId(),
                bridge.isAttack(),
                CraftEquipmentSlot.getHand(hand),
                vector != null ? CraftVector.toBukkit(vector) : null
        );
        Bukkit.getPluginManager().callEvent(event);
    }
    // Paper end - PlayerUseUnknownEntityEvent
}
