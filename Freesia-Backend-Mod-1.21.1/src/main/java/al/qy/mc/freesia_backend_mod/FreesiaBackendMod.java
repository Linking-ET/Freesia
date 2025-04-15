package al.qy.mc.freesia_backend_mod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FreesiaBackendMod {
    public static final String MODID = "freesia";

    public static final Logger LOGGER = LoggerFactory.getLogger(FreesiaBackendMod.class);

    public static void init() {
        ModPlatform.registerNetwork();
    }
}
