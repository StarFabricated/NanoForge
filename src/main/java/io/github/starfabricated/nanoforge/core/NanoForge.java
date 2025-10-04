package io.github.starfabricated.nanoforge.core;


import net.neoforged.bus.api.BusBuilder;
import net.neoforged.bus.api.IEventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class NanoForge {
    public static final IEventBus EVENT_BUS = BusBuilder.builder().startShutdown().build();
    public static final Logger LOGGER = LogManager.getLogger(NanoForge.class.getSimpleName());

    public static void Init(){
        EVENT_BUS.start();
        LOGGER.info("NanoForge Injected");
    }
}
