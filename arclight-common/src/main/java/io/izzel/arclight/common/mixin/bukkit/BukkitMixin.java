package io.izzel.arclight.common.mixin.bukkit;

import io.izzel.arclight.common.bridge.bukkit.ServerBridge;
import io.izzel.arclight.common.mod.mixins.annotation.TransformAccess;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = Bukkit.class, remap = false)
public class BukkitMixin {

    // Paper start - expose game version
    /**
     * Gets the version of game this server implements
     *
     * @return version of game
     * @see io.papermc.paper.ServerBuildInfo#minecraftVersionId()
     * @see io.papermc.paper.ServerBuildInfo#minecraftVersionName()
     */
    @TransformAccess(Opcodes.ACC_PUBLIC | Opcodes.ACC_STATIC)
    @NotNull
    private static String getMinecraftVersion() {
        return ((ServerBridge) Bukkit.getServer()).getMinecraftVersion();
    }
    // Paper end
}
