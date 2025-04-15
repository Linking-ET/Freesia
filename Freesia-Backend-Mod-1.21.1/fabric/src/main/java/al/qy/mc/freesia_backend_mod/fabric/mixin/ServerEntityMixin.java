package al.qy.mc.freesia_backend_mod.fabric.mixin;

import al.qy.mc.freesia_backend_mod.network.handler.TrackerSyncHandler;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerEntity.class)
public abstract class ServerEntityMixin {
    @Shadow @Final private Entity entity;

    @Inject(method = "addPairing", at = @At("RETURN"))
    private void afterAddPairing(ServerPlayer player, CallbackInfo ci) {
        TrackerSyncHandler.onPlayerTrackEntity(player, this.entity);
    }
}
