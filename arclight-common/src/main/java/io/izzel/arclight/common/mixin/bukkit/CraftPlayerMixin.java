package io.izzel.arclight.common.mixin.bukkit;

import com.google.common.base.Preconditions;
import io.izzel.arclight.common.bridge.core.network.common.DiscardedPayloadBridge;
import io.izzel.arclight.common.mod.server.ArclightServer;
import io.netty.buffer.Unpooled;
import net.minecraft.network.Utf8String;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.DiscardedPayload;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.core.util.StringEncoder;
import org.bukkit.craftbukkit.v.entity.CraftPlayer;
import org.bukkit.event.player.PlayerRegisterChannelEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.StandardMessenger;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Set;

@Mixin(value = CraftPlayer.class, remap = false)
public abstract class CraftPlayerMixin extends CraftEntityMixin {

    @Shadow @Final private Set<String> channels;

    /**
     * @author IzzelAliz
     * @reason
     */
    @Overwrite
    @SuppressWarnings("deprecation")
    public void addChannel(String channel) {
        Preconditions.checkState(this.channels.size() < 1024, "Cannot register channel '%s'. Too many channels registered!", channel);
        channel = StandardMessenger.validateAndCorrectChannel(channel);
        if (this.channels.add(channel)) {
            this.server.getPluginManager().callEvent(new PlayerRegisterChannelEvent((CraftPlayer) (Object) this, channel));
        }
    }

    /**
     * @author sj-hub9796
     * @reason
     */
    @Overwrite
    public void sendPluginMessage(Plugin source, String channel, byte[] message) {
        StandardMessenger.validatePluginMessage(this.server.getMessenger(), source, channel, message);
        if (((CraftPlayer) (Object) this).getHandle().connection != null) {
            if (this.channels.contains(channel)) {
                ResourceLocation id = ResourceLocation.parse(StandardMessenger.validateAndCorrectChannel(channel));
                this.sendCustomPayload(id, message);
            }
        }
    }

    /**
     * @author sj-hub9796
     * @reason
     */
    @Overwrite
    private void sendCustomPayload(ResourceLocation id, byte[] message) {
        DiscardedPayload payload = new DiscardedPayload(id);
        ((DiscardedPayloadBridge) (Object) payload).bridge$setData(Unpooled.wrappedBuffer(message));
        ClientboundCustomPayloadPacket packet = new ClientboundCustomPayloadPacket(payload);
        ((CraftPlayer) (Object) this).getHandle().connection.send(packet);
    }
}
