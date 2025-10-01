package io.github.starfabricated.nanoforge.core;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NanoForgeMixinBootstrap implements ITweaker {

    private String gameDir = System.getProperty("user.dir");


    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {

    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        List<String> mixins = new ArrayList<>();
        mixins.add("nanoforge.mixin.json");


        MixinBootstrap.init();
        mixins.forEach(Mixins::addConfiguration);
    }

    @Override
    public String getLaunchTarget() {
        return "com.fs.starfarer.StarfarerLauncher";
    }

    @Override
    public String[] getLaunchArguments() {
        return new String[0];
    }
}
