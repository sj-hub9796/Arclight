package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.bridge.bukkit.ServerBridge;
import org.bukkit.Server;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = Server.class, remap = false)
public interface ServerMixin extends ServerBridge {

    // Paper start - expose game version
    /**
     * Gets the version of game this server implements
     *
     * @return version of game
     */
    @NotNull
    String getMinecraftVersion();
    // Paper end
}
