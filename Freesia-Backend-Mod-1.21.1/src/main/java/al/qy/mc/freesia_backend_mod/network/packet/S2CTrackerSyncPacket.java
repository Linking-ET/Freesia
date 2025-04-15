package al.qy.mc.freesia_backend_mod.network.packet;

import net.minecraft.network.FriendlyByteBuf;

import java.util.Set;
import java.util.UUID;

public record S2CTrackerSyncPacket(int callbackId, Set<UUID> result) implements IS2CPacket {
    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeVarInt(0);
        buf.writeVarInt(callbackId);
        buf.writeVarInt(result.size());
        for (var u : result) {
            buf.writeUUID(u);
        }
    }
}
