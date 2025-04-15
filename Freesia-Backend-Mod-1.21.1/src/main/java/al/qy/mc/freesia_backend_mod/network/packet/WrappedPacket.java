package al.qy.mc.freesia_backend_mod.network.packet;

import al.qy.mc.freesia_backend_mod.ModConstants;
import al.qy.mc.freesia_backend_mod.network.handler.TrackerSyncHandler;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public class WrappedPacket implements CustomPacketPayload {
    public static final CustomPacketPayload.Type<WrappedPacket> TYPE = new Type<>(ModConstants.TRACKING_SYNC);

    public static final StreamCodec<FriendlyByteBuf, WrappedPacket> CODEC = new StreamCodec<>() {
        @Override
        public @NotNull WrappedPacket decode(FriendlyByteBuf buf) {
            var id = buf.readVarInt();
            if (id == 1) {
                return new WrappedPacket(C2STrackerSyncPacket.from(buf));
            }

            return new WrappedPacket();
        }

        @Override
        public void encode(@NotNull FriendlyByteBuf buf, @NotNull WrappedPacket packet) {
            if (packet.s2CNotifyTrackerUpdatePacket != null) {
                packet.s2CNotifyTrackerUpdatePacket.write(buf);
            } else if (packet.s2CTrackerSyncPacket != null) {
                packet.s2CTrackerSyncPacket.write(buf);
            }
        }
    };

    private C2STrackerSyncPacket c2STrackerSyncPacket = null;
    private S2CTrackerSyncPacket s2CTrackerSyncPacket = null;
    private S2CNotifyTrackerUpdatePacket s2CNotifyTrackerUpdatePacket = null;

    public WrappedPacket(C2STrackerSyncPacket packet) {
        this.c2STrackerSyncPacket = packet;
    }

    public WrappedPacket(S2CTrackerSyncPacket packet) {
        this.s2CTrackerSyncPacket = packet;
    }

    public WrappedPacket(S2CNotifyTrackerUpdatePacket packet) {
        this.s2CNotifyTrackerUpdatePacket = packet;
    }

    public WrappedPacket() {
    }

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void work(ServerPlayer player) {
        if (c2STrackerSyncPacket != null) {
            TrackerSyncHandler.onTrackerSync(player, c2STrackerSyncPacket);
        }
    }
}
