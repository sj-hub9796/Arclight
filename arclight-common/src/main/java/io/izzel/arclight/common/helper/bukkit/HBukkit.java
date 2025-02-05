package io.izzel.arclight.common.helper.bukkit;

import io.izzel.arclight.common.bridge.bukkit.ServerBridge;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public final class HBukkit {

    // Paper start - expose game version
    /**
     * Gets the version of game this server implements
     *
     * @return version of game
     * @see io.papermc.paper.ServerBuildInfo#minecraftVersionId()
     * @see io.papermc.paper.ServerBuildInfo#minecraftVersionName()
     */
    @NotNull
    public static String getMinecraftVersion() {
        return ((ServerBridge) Bukkit.getServer()).getMinecraftVersion();
    }
    // Paper end
}
