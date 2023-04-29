package com.midup;

import com.midup.api.Settings;
import com.midup.commands.MaintenanceCommand;
import com.midup.platform.Platform;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
public class MidupCore extends Plugin {

    public static MidupCore midup;
    private Configuration config;


    @Override
    public void onLoad() {
        new Settings();
        createConfig();
    }

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new MaintenanceCommand());
        Platform.getMaintenance().setEnabled(config.getBoolean("maintenance.enable"));
        Platform.getMaintenance().setMembers(config.getStringList("maintenance.members"));
        Platform.setMotd(config.getString("motd").replace("&", "§").replace("{newline}", "\n"));
        Platform.getMaintenance().setMotd(config.getString("maintenance.motd").replace("&", "§").replace("{newline}", "\n"));
        Platform.getMaintenance().setKickMessage(config.getString("maintenance.kick").replace("&", "§").replace("{newline}", "\n"));
    }

    @Override
    public void onDisable() {

    }

    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(this.getDataFolder(), "config.yml");
            if (!file.exists()) {
                file.createNewFile();
            }
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(this.getDataFolder(), "config.yml"));
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static MidupCore getInstance() {
        return midup;
    }
}
