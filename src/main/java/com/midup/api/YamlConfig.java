package com.midup.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class YamlConfig {
    private File configFile;
    private String fileName;
    private Plugin plugin;
    private File folder;
    private static Configuration config;

    public YamlConfig(String fileName, Plugin plugin) {
        this.plugin = plugin;
        this.fileName = fileName;
        this.folder = plugin.getDataFolder();
        this.configFile = new File(this.folder, fileName);
    }

    public void saveDefaultConfig() {
        if (!this.folder.exists()) {
            this.folder.mkdirs();
        }

        try {
            if (!this.configFile.exists()) {
                Files.copy(this.plugin.getResourceAsStream(this.fileName), this.configFile.toPath(), new CopyOption[0]);
            }

            this.loadConfig();
        } catch (IOException var2) {
            var2.printStackTrace();
        }

    }

    public void loadConfig() throws IOException {
        config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(this.configFile);
    }

    public void saveConfig() throws IOException {
        ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, this.configFile);
    }

    public Configuration getConfig() {
        return config;
    }
}