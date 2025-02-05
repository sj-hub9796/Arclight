package io.izzel.arclight.common.bridge.bukkit;

public interface ServerBridge {

    // Paper start - expose game version
    String getMinecraftVersion();
    // Paper end
}
