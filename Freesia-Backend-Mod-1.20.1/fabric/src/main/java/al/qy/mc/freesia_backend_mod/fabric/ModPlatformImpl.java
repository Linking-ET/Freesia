package al.qy.mc.freesia_backend_mod.fabric;

import al.qy.mc.freesia_backend_mod.ModConstants;
import al.qy.mc.freesia_backend_mod.fabric.network.ModFabricNetwork;
import al.qy.mc.freesia_backend_mod.network.packet.*;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.server.level.ServerPlayer;

public class ModPlatformImpl {
    public static void registerNetwork() {
        ModFabricNetwork.register();
    }

    public static void send(ServerPlayer player, S2CTrackerSyncPacket packet) {
        var buf = PacketByteBufs.create();
        packet.write(buf);
        ModFabricNetwork.send(player, ModConstants.TRACKING_SYNC, buf);
    }

    public static void send(ServerPlayer player, S2CNotifyTrackerUpdatePacket packet) {
        var buf = PacketByteBufs.create();
        packet.write(buf);
        ModFabricNetwork.send(player, ModConstants.TRACKING_SYNC, buf);
    }
}
