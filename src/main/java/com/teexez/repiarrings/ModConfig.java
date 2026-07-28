package com.teexez.repiarrings;

import blue.endless.jankson.Jankson;
import blue.endless.jankson.JsonElement;
import blue.endless.jankson.JsonObject;
import blue.endless.jankson.api.DeserializationException;
import blue.endless.jankson.api.SyntaxError;
import net.fabricmc.loader.api.FabricLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class ModConfig {
    private static final String MOD_ID = "teexezringsrepiar";
    private static final String CONFIG_FILENAME = "/teexezringsrepiar_config_settings.json5";
    private static ModConfigSettings SETTINGS;

    public static void setup() {
        Path configPath = getModConfigPath();
        try {
            Files.createDirectories(configPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        File configFile = new File(configPath.toString() + CONFIG_FILENAME);
        if (configFile.exists()) {
            RingsOfRepair.LOGGER.info("{} config settings json5 found", MOD_ID);
            validateConfigFile();
        } else {
            RingsOfRepair.LOGGER.info("{} config settings json5 not found, creating default", MOD_ID);
            createDefaultConfig();
        }

        SETTINGS = getConfigFile();
    }

    public static ModConfigSettings getConfig() {
        return SETTINGS;
    }

    private static void validateConfigFile() {
        Jankson jankson = Jankson.builder().build();
        Path configPath = getModConfigPath();
        File configFile = new File(configPath.toString() + CONFIG_FILENAME);
        if (!configFile.exists()) return;

        JsonObject configFileData;
        try {
            configFileData = jankson.load(configFile);
        } catch (IOException | SyntaxError e) {
            throw new RuntimeException(e);
        }

        JsonElement jsonElement = jankson.toJson(new ModConfigSettings());
        if (!(jsonElement instanceof JsonObject configDefault)) return;

        for (Map.Entry<String, JsonElement> entry : configDefault.entrySet()) {
            if (!configFileData.containsKey(entry.getKey())) {
                configFileData.putDefault(entry.getKey(), entry.getValue(), null);
                RingsOfRepair.LOGGER.info("config key added: " + entry.getKey());
            }
        }

        for (Map.Entry<String, JsonElement> entry : configFileData.entrySet()) {
            if (!configDefault.containsKey(entry.getKey())) {
                configFileData.remove(entry.getKey(), entry.getValue());
                RingsOfRepair.LOGGER.info("config key removed: " + entry.getKey());
            }
        }

        try {
            SETTINGS = (ModConfigSettings) jankson.fromJsonCarefully(configFileData, ModConfigSettings.class);
        } catch (DeserializationException e) {
            throw new RuntimeException(e);
        }

        refreshConfigFile(configFile, SETTINGS);
    }

    private static void refreshConfigFile(File configFile, ModConfigSettings settings) {
        Jankson jankson = Jankson.builder().build();
        String result = jankson.toJson(settings).toJson(true, true);
        try (FileOutputStream out = new FileOutputStream(configFile, false)) {
            out.write(result.getBytes());
            out.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void createDefaultConfig() {
        Path configPath = getModConfigPath();
        File configFile = new File(configPath.toString() + CONFIG_FILENAME);
        try {
            Files.createDirectories(configPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        RingsOfRepair.LOGGER.info("{} default config created at: " + configFile, MOD_ID);

        Jankson jankson = Jankson.builder().build();
        String result = jankson.toJson(new ModConfigSettings()).toJson(true, true);

        if (!configFile.exists()) {
            try {
                configFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (FileOutputStream out = new FileOutputStream(configFile, false)) {
            out.write(result.getBytes());
            out.flush();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static ModConfigSettings getConfigFile() {
        Path configPath = getModConfigPath();
        Jankson jankson = Jankson.builder().build();
        File configFile = new File(configPath.toString() + CONFIG_FILENAME);

        try {
            JsonObject configJson = jankson.load(configFile);
            SETTINGS = (ModConfigSettings) jankson.fromJson(configJson, ModConfigSettings.class);
        } catch (IOException | SyntaxError ex) {
            throw new RuntimeException(ex);
        }

        return SETTINGS;
    }

    private static Path getModConfigPath() {
        return FabricLoader.getInstance().getConfigDir().resolve(MOD_ID);
    }
}