package al.qy.mc.freesia_backend_mod.network.packet;

import net.minecraft.network.FriendlyByteBuf;

import java.util.UUID;

public record S2CNotifyTrackerUpdatePacket(UUID beWatchedPlayer, UUID watcherPlayer) implements IS2CPacket {
    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeVarInt(2);
        buf.writeUUID(beWatchedPlayer);
        buf.writeUUID(watcherPlayer);
    }
}
