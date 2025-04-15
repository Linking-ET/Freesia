package al.qy.mc.freesia_backend_mod.network.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.UUID;

public record C2STrackerSyncPacket(int callbackId, UUID requestedPlayer) {
    public static C2STrackerSyncPacket from(FriendlyByteBuf buf) {
        return new C2STrackerSyncPacket(buf.readVarInt(), buf.readUUID());
    }
}
