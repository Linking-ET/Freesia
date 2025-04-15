package al.qy.mc.freesia_backend_mod.network;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Consumer;

public class ModNetwork {
    public static void randomPlayerAndRun(MinecraftServer server, Consumer<ServerPlayer> consumer) {
        var list = server.getPlayerList();
        var p = list.getPlayers().stream().findAny();
        p.ifPresent(consumer);
    }
}
