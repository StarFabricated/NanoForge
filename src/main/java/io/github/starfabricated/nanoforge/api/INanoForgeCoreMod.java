package io.github.starfabricated.nanoforge.api;

public interface INanoForgeCoreMod extends IGameTransformer {
    void onGameLauncherStart();

    void beforeApplicationLoad();

    void afterApplicationLoad();
}