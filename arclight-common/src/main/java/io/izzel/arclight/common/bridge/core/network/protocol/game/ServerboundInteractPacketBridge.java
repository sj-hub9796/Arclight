package io.izzel.arclight.common.bridge.core.network.protocol.game;

public interface ServerboundInteractPacketBridge {

    // Paper start - PlayerUseUnknownEntityEvent
    int getEntityId();
    boolean isAttack();
    // Paper end - PlayerUseUnknownEntityEvent
}
