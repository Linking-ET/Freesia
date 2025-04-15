package al.qy.mc.freesia_backend_mod.network.packet;

import net.minecraft.network.FriendlyByteBuf;

public interface IS2CPacket {
    void write(FriendlyByteBuf buf);
}
