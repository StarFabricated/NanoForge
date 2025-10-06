package io.github.starfabricated.nanoforge;


import net.neoforged.bus.api.BusBuilder;
import net.neoforged.bus.api.IEventBus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public final class NanoForge {
    public static final IEventBus EVENT_BUS = BusBuilder.builder().startShutdown().build();
    public static final Logger LOGGER = LogManager.getLogger(NanoForge.class.getSimpleName());
    private final NanoForge instance;


    public NanoForge(){
        instance = this;
    }

    public static void Init(){
        new NanoForge();
        EVENT_BUS.start();
        LOGGER.info("NanoForge Injected!");
    }

    public NanoForge getInstance(){
        return instance;
    }
}
