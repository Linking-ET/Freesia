package al.qy.mc.freesia_backend_mod.fabric;

import al.qy.mc.freesia_backend_mod.FreesiaBackendMod;
import al.qy.mc.freesia_backend_mod.network.handler.TrackerSyncHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class FreesiaBackendModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        FreesiaBackendMod.init();

        ServerTickEvents.END_SERVER_TICK.register(TrackerSyncHandler::serverTick);
    }
}
