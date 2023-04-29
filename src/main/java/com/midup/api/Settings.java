package com.midup.api;

import com.midup.MidupCore;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Settings {

    protected static YamlConfig config;
    public static Configuration configuration;

    public Settings() {
        config = null;

        try {
            (config = new YamlConfig("config.yml", MidupCore.getInstance())).saveDefaultConfig();
            config.loadConfig();
            configuration = config.getConfig();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void createConfig(File file, Configuration config) {
        try {
            if (!MidupCore.getInstance().getDataFolder().exists()) {
                MidupCore.getInstance().getDataFolder().mkdirs();
            }
            file = new File(MidupCore.getInstance().getDataFolder(), "config.yml");
            if (!file.exists()) {
                file.createNewFile();
            }
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(MidupCore.getInstance().getDataFolder(), "config.yml"));
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveConfig() {
        try {
            config.saveConfig();
        } catch (IOException var1) {
            var1.printStackTrace();
        }
    }
}