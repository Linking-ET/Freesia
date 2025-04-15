package al.qy.mc.freesia_backend_mod.fabric.network;

import al.qy.mc.freesia_backend_mod.network.packet.WrappedPacket;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

public class ModFabricNetwork {
    public static void register() {
        PayloadTypeRegistry.playC2S().register(WrappedPacket.TYPE, WrappedPacket.CODEC);
        PayloadTypeRegistry.playS2C().register(WrappedPacket.TYPE, WrappedPacket.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(WrappedPacket.TYPE, (packet, context) -> packet.work(context.player()));
    }

    public static void send(ServerPlayer player, WrappedPacket packet) {
        ServerPlayNetworking.send(player, packet);
    }
}
