package al.qy.mc.freesia_backend_mod.forge;

import al.qy.mc.freesia_backend_mod.FreesiaBackendMod;
import al.qy.mc.freesia_backend_mod.client.FreesiaBackendModClient;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FreesiaBackendMod.MODID)
public class FreesiaBackendModForge {

    @SuppressWarnings("removal")
    public FreesiaBackendModForge() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setupClient);

        FreesiaBackendMod.init();
    }

    private void setupClient(FMLClientSetupEvent event) {
        FreesiaBackendModClient.initClient();
    }
}
