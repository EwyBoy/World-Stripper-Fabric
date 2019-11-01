package com.ewyboy.worldstripper.config;

import com.ewyboy.worldstripper.other.Reference;
import io.github.prospector.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

import java.util.function.Function;

@SuppressWarnings("unused")
@Environment(EnvType.CLIENT)
public class ConfigModMenu implements ModMenuApi {

    @Override
    public String getModId() {
        return Reference.ModInfo.MOD_ID;
    }

    @Override
    public Function<Screen, ? extends Screen> getConfigScreenFactory() {
        return Config :: createConfigGui;
    }

}
