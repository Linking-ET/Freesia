package al.qy.mc.freesia_backend_mod;

import al.qy.mc.freesia_backend_mod.network.packet.*;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.level.ServerPlayer;

public class ModPlatform {
    @ExpectPlatform
    public static void registerNetwork() {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void send(ServerPlayer player, S2CTrackerSyncPacket packet) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void send(ServerPlayer player, S2CNotifyTrackerUpdatePacket packet) {
        throw new AssertionError();
    }
}
