package io.github.starfabricated.nanoforge;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraft.launchwrapper.LogWrapper;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarFile;

import static io.github.starfabricated.nanoforge.NanoForgeLaunchHelper.*;
import static net.minecraft.launchwrapper.Launch.classLoader;


public class NanoForgeBootstrap implements ITweaker {
    private static final List<String> MIXINS = new ArrayList<>();
    public static final String MAIN_CLASS = "com.fs.starfarer.StarfarerLauncher";


    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        try {
            loadModMixins();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1337);
        }

        mixinBootstrap();
    }

    @Override
    public String getLaunchTarget() {
        return MAIN_CLASS;
    }

    @Override
    public String[] getLaunchArguments() {
        return new String[0];
    }

    private void mixinBootstrap(){
        MIXINS.add("nanoforge.mixins.json");
        MixinBootstrap.init();
        MIXINS.forEach(Mixins::addConfiguration);
    }

    private void loadModMixins() throws IOException {
        Files.walkFileTree(modsDir.toPath(), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toFile().getName().endsWith(".jar")) {
                    try {
                        JarFile modJar = new JarFile(file.toFile());
                        if (hasModMixins(modJar)) {
                            classLoader.addURL(file.toUri().toURL());
                            MIXINS.addAll(getModMixins(modJar));
                        } else {
                            LogWrapper.log("NanoForge/MixinLoader", Level.DEBUG, "Ignore %s , mixins.json not found", file.toFile().getName());
                        }
                    } catch (Throwable throwable) {
                        LogWrapper.log("NanoForge/MixinLoader", Level.ERROR, throwable, "File %s fail to load", file.toFile().getName());
                    }
                }
                return super.visitFile(file, attrs);
            }
        });
    }
}
