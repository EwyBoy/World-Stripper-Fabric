package com.ewyboy.worldstripper.client.keybindings;

import com.ewyboy.worldstripper.other.Reference;
import com.ewyboy.worldstripper.WorldStripper;
import com.ewyboy.worldstripper.network.PacketDressWorld;
import com.ewyboy.worldstripper.network.PacketStripWorld;
import com.raphydaphy.crochet.network.PacketHandler;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.fabricmc.fabric.api.event.client.ClientTickCallback;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyBindingHandler {

    private static FabricKeyBinding keyBindingStrip;
    private static FabricKeyBinding keyBindingDress;

    private static FabricKeyBinding getKeyBindingStrip() { return keyBindingStrip; }
    private static FabricKeyBinding getKeyBindingDress() { return keyBindingDress; }

    public static void init() {
        registerCategory();
        initKeyBindings();
        registerKeyBindings();
        keyPressedEvent();
    }

    private static void registerCategory() {
        KeyBindingRegistry.INSTANCE.addCategory(Reference.ModInfo.MOD_NAME);
    }

    private static void initKeyBindings() {
        keyBindingStrip = FabricKeyBinding.Builder.create(
                new Identifier(Reference.ModInfo.MOD_ID, "strip"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_DELETE,
                Reference.ModInfo.MOD_NAME
        ).build();
        keyBindingDress = FabricKeyBinding.Builder.create(
                new Identifier(Reference.ModInfo.MOD_ID, "dress"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_INSERT,
                Reference.ModInfo.MOD_NAME
        ).build();
    }

    private static void registerKeyBindings() {
        KeyBindingRegistry.INSTANCE.register(keyBindingStrip);
        KeyBindingRegistry.INSTANCE.register(keyBindingDress);
    }

    private static void keyPressedEvent() {
        ClientTickCallback.EVENT.register(e -> {
            if(KeyBindingHandler.getKeyBindingStrip().isPressed()) {
                WorldStripper.getLogger().info("Strip was pressed!");
                PacketHandler.sendToServer(new PacketStripWorld());
            }
            if(KeyBindingHandler.getKeyBindingDress().isPressed()) {
                WorldStripper.getLogger().info("Dress was pressed!");
                PacketHandler.sendToServer(new PacketDressWorld());
            }
        });
    }

}