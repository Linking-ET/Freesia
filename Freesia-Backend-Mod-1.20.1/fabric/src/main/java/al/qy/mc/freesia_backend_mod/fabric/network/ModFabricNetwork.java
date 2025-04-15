package al.qy.mc.freesia_backend_mod.fabric.network;

import al.qy.mc.freesia_backend_mod.ModConstants;
import al.qy.mc.freesia_backend_mod.network.handler.TrackerSyncHandler;
import al.qy.mc.freesia_backend_mod.network.packet.C2STrackerSyncPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class ModFabricNetwork {
    public static void register() {
        ServerPlayNetworking.registerGlobalReceiver(ModConstants.TRACKING_SYNC, (server, player, packetListener, buf, sender) -> {
            var p = C2STrackerSyncPacket.from(buf);
            TrackerSyncHandler.onTrackerSync(player, p);
        });
    }

    public static void send(ServerPlayer player, ResourceLocation name, FriendlyByteBuf buf) {
        ServerPlayNetworking.send(player, name, buf);
    }
}
