package al.qy.mc.freesia_backend_mod.forge.handler;

import al.qy.mc.freesia_backend_mod.FreesiaBackendMod;
import al.qy.mc.freesia_backend_mod.network.handler.TrackerSyncHandler;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = FreesiaBackendMod.MODID)
public class ModEventHandler {
    @SubscribeEvent
    public static void onPlayerTrack(PlayerEvent.StartTracking event) {
        TrackerSyncHandler.onPlayerTrackEntity(event.getEntity(), event.getTarget());
    }

    @SubscribeEvent
    public static void onEntityJoin(EntityJoinLevelEvent event) {
        TrackerSyncHandler.onEntityJoin(event.getEntity());
    }

    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        TrackerSyncHandler.serverTick(event.getServer());
    }
}
