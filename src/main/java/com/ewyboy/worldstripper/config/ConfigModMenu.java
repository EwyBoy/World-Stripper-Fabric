package com.ewyboy.worldstripper.config;

import com.ewyboy.worldstripper.other.Reference;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

@SuppressWarnings("unused")
public class ConfigModMenu implements ModMenuApi {

    @Override
    public String getModId() {
        return Reference.ModInfo.MOD_ID;
    }

    /*@Override
    public Optional<Supplier<Screen>> getConfigScreen(Screen screen) {
        return Optional.of(AutoConfig.getConfigScreen(Config.class, screen));
    }*/

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return Config :: createConfigGui;
    }
}
