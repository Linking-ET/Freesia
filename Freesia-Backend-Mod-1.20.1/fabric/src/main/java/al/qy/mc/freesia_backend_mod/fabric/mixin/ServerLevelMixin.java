package al.qy.mc.freesia_backend_mod.fabric.mixin;

import al.qy.mc.freesia_backend_mod.network.handler.TrackerSyncHandler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {

    @Inject(method = "addPlayer", at = @At("RETURN"))
    private void afterAddPlayer(ServerPlayer player, CallbackInfo ci) {
        TrackerSyncHandler.onEntityJoin(player);
    }
}
