package al.qy.mc.freesia_backend_mod.forge;

import al.qy.mc.freesia_backend_mod.forge.network.ModForgeNetwork;
import al.qy.mc.freesia_backend_mod.network.packet.*;
import net.minecraft.server.level.ServerPlayer;

public class ModPlatformImpl {

    public static void registerNetwork() {
        ModForgeNetwork.register();
    }

    public static void send(ServerPlayer player, S2CTrackerSyncPacket packet) {
        ModForgeNetwork.send(player, packet);
    }

    public static void send(ServerPlayer player, S2CNotifyTrackerUpdatePacket packet) {
        ModForgeNetwork.send(player, packet);
    }
}
