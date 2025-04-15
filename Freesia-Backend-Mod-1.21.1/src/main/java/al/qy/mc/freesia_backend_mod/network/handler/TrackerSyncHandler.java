package al.qy.mc.freesia_backend_mod.network.handler;

import al.qy.mc.freesia_backend_mod.ModPlatform;
import al.qy.mc.freesia_backend_mod.network.ModNetwork;
import al.qy.mc.freesia_backend_mod.network.packet.C2STrackerSyncPacket;
import al.qy.mc.freesia_backend_mod.network.packet.S2CNotifyTrackerUpdatePacket;
import al.qy.mc.freesia_backend_mod.network.packet.S2CTrackerSyncPacket;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

public class TrackerSyncHandler {
    public static void onPlayerTrackEntity(Player player, Entity tracked) {
        if (player instanceof ServerPlayer serverPlayer && tracked instanceof ServerPlayer watched) {
            onTracked(serverPlayer, watched);
        }
    }

    public static void onEntityJoin(Entity entity) {
        if (entity instanceof ServerPlayer player) {
            onTracked(player, player);
        }
    }

    public static void onTrackerSync(ServerPlayer player, C2STrackerSyncPacket packet) {
        var playerList = player.server.getPlayerList();
        var toScan = playerList.getPlayer(packet.requestedPlayer());

        var result = new HashSet<UUID>();
        if (toScan != null) {
            for (var p : playerList.getPlayers()) {
                if (!p.isInvisibleTo(toScan)) {
                    result.add(p.getUUID());
                }
            }
        }

        TICKERS.add(new ScheduledRunnable(() -> ModPlatform.send(player, new S2CTrackerSyncPacket(packet.callbackId(), result)), 2));
    }

    private static void onTracked(ServerPlayer player, ServerPlayer tracked) {
        var watcher = player.getUUID();
        var watched = tracked.getUUID();
        TICKERS.add(new ScheduledRunnable(() -> ModNetwork.randomPlayerAndRun(player.server, p -> ModPlatform.send(p, new S2CNotifyTrackerUpdatePacket(watched, watcher))), 2));
    }

    private static final List<ScheduledRunnable> TICKERS = new ArrayList<>();

    public static void serverTick(MinecraftServer server) {
        TICKERS.removeIf(ScheduledRunnable::run);
    }

    public static class ScheduledRunnable {

        public int delayRemain = 0;

        private final Runnable runnable;

        public ScheduledRunnable(Runnable runnable, int delay) {
            this.runnable = runnable;
            this.delayRemain = delay;
        }

        public boolean run() {
            if (delayRemain <= 0) {
                runnable.run();
                return true;
            }

            delayRemain -= 1;
            return false;
        }
    }
}
