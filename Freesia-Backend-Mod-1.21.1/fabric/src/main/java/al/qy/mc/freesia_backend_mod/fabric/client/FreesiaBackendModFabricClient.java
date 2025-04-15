package al.qy.mc.freesia_backend_mod.fabric.client;

import al.qy.mc.freesia_backend_mod.client.FreesiaBackendModClient;
import net.fabricmc.api.ClientModInitializer;

public class FreesiaBackendModFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FreesiaBackendModClient.initClient();
    }
}
