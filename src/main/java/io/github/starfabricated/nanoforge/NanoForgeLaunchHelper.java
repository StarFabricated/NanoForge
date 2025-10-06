package io.github.starfabricated.nanoforge;

import java.io.File;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class NanoForgeLaunchHelper {
    public static final String MODS_PATH = System.getProperty("com.fs.starfarer.settings.paths.mods");
    public static final File modsDir = new File(MODS_PATH) ;

    private NanoForgeLaunchHelper(){}


    public static Boolean hasModMixins(JarFile jarFile) {
        return jarFile.stream()
                .filter(entry -> !entry.isDirectory())
                .map(ZipEntry::getName)
                .anyMatch(name -> name.endsWith(".mixins.json"));
    }

    public static List<String> getModMixins(JarFile jarFile) {
        return jarFile.stream()
                .filter(entry -> !entry.isDirectory() && entry.getName().endsWith(".mixins.json"))
                .map(ZipEntry::getName)
                .toList();
    }

    public static boolean isJarFile(File file) {
        return file.isFile() && file.getName().toLowerCase().endsWith(".jar");
    }
}
