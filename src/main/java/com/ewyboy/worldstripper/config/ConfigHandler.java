package com.ewyboy.worldstripper.config;

import com.ewyboy.worldstripper.WorldStripper;
import com.ewyboy.worldstripper.other.Reference;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import net.fabricmc.loader.FabricLoader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ConfigHandler {

    private static final Executor EXECUTOR = Executors.newSingleThreadExecutor(r -> new Thread(r, "World-Stripper Config Handler"));
    private static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();
    private static Config config;
    private static Path configFile;

    public static Config getConfig() {
        return config != null ? config : init();
    }

    public static Config init() {
        configFile = FabricLoader.INSTANCE.getConfigDirectory().toPath().resolve(Reference.ModInfo.MOD_ID + ".json");

        if (!Files.exists(configFile)) {
            WorldStripper.getLogger().info("Creating World-Stripper config file ({})", configFile.getFileName());
            save().join();
        }
        load().thenApply(c -> config = c).join();

        return Objects.requireNonNull(config, "failed to init config");
    }

    public static CompletableFuture<Config> load() {
        return CompletableFuture.supplyAsync(() -> {
            try(BufferedReader reader = Files.newBufferedReader(configFile)) {
                return GSON.fromJson(reader, Config.class);
            }
            catch (IOException | JsonParseException e) {
                WorldStripper.getLogger().error("unable to read config file, restoring defaults!", e);
                save();
                return new Config();
            }
        }, EXECUTOR);
    }

    public static CompletableFuture<Void> save() {
        WorldStripper.getLogger().trace("saving orderly config file to {}", configFile);
        return CompletableFuture.runAsync(() -> {
            try(BufferedWriter writer = Files.newBufferedWriter(configFile)) {
                GSON.toJson(Optional.ofNullable(config).orElseGet(Config :: new), writer);
            }
            catch (IOException | JsonIOException e) {
                WorldStripper.getLogger().error("unable to write config file", e);
            }
        }, EXECUTOR);
    }
}
