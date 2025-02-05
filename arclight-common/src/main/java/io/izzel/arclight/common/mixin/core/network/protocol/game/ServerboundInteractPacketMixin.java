package io.izzel.arclight.common.mixin.core.network.protocol.game;

import io.izzel.arclight.common.bridge.core.network.protocol.game.ServerboundInteractPacketBridge;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ServerboundInteractPacket.class)
public class ServerboundInteractPacketMixin implements ServerboundInteractPacketBridge {

    @Shadow @Final @Mutable private int entityId;
    @Shadow @Final @Mutable private ServerboundInteractPacket.Action action;

    @Override
    public int getEntityId() {
        return this.entityId;
    }

    @Override
    public boolean isAttack() {
        return this.action.getType() == ServerboundInteractPacket.ActionType.ATTACK;
    }
}
