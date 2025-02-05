package io.izzel.arclight.common.bridge.bukkit;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.players.PlayerList;

public interface CraftServerBridge extends ServerBridge {

    void bridge$setPlayerList(PlayerList playerList);

    void bridge$removeWorld(ServerLevel world);
}
