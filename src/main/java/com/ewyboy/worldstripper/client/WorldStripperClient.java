package com.ewyboy.worldstripper.client;

import com.ewyboy.worldstripper.other.Reference;
import com.ewyboy.worldstripper.WorldStripper;
import com.ewyboy.worldstripper.client.keybindings.KeyBindingHandler;
import net.fabricmc.api.ClientModInitializer;

public class WorldStripperClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        WorldStripper.getLogger().info("Init KeyBindings for " + Reference.ModInfo.MOD_NAME);
        KeyBindingHandler.init();
    }
}
