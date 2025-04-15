package al.qy.mc.freesia_backend_mod.forge.network;

import al.qy.mc.freesia_backend_mod.ModConstants;
import al.qy.mc.freesia_backend_mod.network.handler.TrackerSyncHandler;
import al.qy.mc.freesia_backend_mod.network.packet.C2STrackerSyncPacket;
import al.qy.mc.freesia_backend_mod.network.packet.IS2CPacket;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundCustomPayloadPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.event.EventNetworkChannel;

import java.util.Objects;

public class ModForgeNetwork {
    public static EventNetworkChannel CHANNEL;

    public static void register() {
        CHANNEL = NetworkRegistry.newEventChannel(ModConstants.TRACKING_SYNC, () -> "1", (s) -> true, (s) -> true);
        CHANNEL.addListener(ModForgeNetwork::onCustomPayload);
    }

    public static void onCustomPayload(NetworkEvent.ServerCustomPayloadEvent event) {
        var context = event.getSource().get();
        context.enqueueWork(() -> {
            var packet = C2STrackerSyncPacket.from(event.getPayload());
            TrackerSyncHandler.onTrackerSync(Objects.requireNonNull(context.getSender()), packet);
        });
    }

    public static void send(ServerPlayer player, IS2CPacket packet) {
        var buf = new FriendlyByteBuf(Unpooled.buffer());
        packet.write(buf);
        var p = new ClientboundCustomPayloadPacket(ModConstants.TRACKING_SYNC, buf);
        player.connection.send(p);
    }
}
