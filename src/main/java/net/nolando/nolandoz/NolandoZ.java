package net.nolando.nolandoz;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.server.ServerStartedEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.nolando.nolandoz.modinit.NZProcessors;

@Mod(NolandoZ.MODID)
public class NolandoZ {
    public static final String MODID = "nolandoz";
    public static final Logger LOGGER = LogManager.getLogger();

    public NolandoZ() {

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Register processors and listeners
        NZProcessors.register(modBus);
        modBus.addListener(this::setup);
        forgeBus.addListener(this::onDatapackReload);
        forgeBus.addListener(this::onServerStarted);
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LOGGER.info("[{}] Common setup complete.", MODID);
        });
    }

    private void onDatapackReload(final AddReloadListenerEvent event) {
        // If you have custom reload listeners, register here
        // e.g., event.addListener(MyManager::reload);
    }

    private void onServerStarted(final ServerStartedEvent event) {
        LOGGER.info("[{}] Server started, mod ready!", MODID);
    }
}