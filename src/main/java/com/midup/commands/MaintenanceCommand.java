package com.midup.commands;

import com.midup.MidupCore;
import com.midup.platform.Platform;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;

public class MaintenanceCommand extends Command {

    public MaintenanceCommand() {
        super("maintenance");
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (sender instanceof ProxiedPlayer) {
            ProxiedPlayer proxiedPlayer = (ProxiedPlayer) sender;

            if (!proxiedPlayer.hasPermission("server.superior")) {
                proxiedPlayer.sendMessage("§cVocê não tem permissão para executar este comando.");
                return;
            }
        }

        if (args.length == 0) {
            sender.sendMessage("§cSintaxe incorreta, utilize '/maintenance [on/off]'.");
            return;
        }

        if (args[0].equalsIgnoreCase("on")) {
            if (Platform.getMaintenance().isEnabled()) {
                sender.sendMessage("§cA manutenção já está ativada.");
                return;
            }

            Platform.getMaintenance().setEnabled(true);
            updateConfig();
            sender.sendMessage("§aA manutenção foi ativada.");
            return;
        }
        if (args[0].equalsIgnoreCase("off")) {
            if (!Platform.getMaintenance().isEnabled()) {
                sender.sendMessage("§cA manutenção já está desativada.");
                return;
            }

            Platform.getMaintenance().setEnabled(false);
            updateConfig();
            sender.sendMessage("§aA manutenção foi desativada.");
            return;
        }
    }


    public void updateConfig() {
        try {
            File file = new File(MidupCore.getInstance().getDataFolder(), "config.yml");
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(MidupCore.getInstance().getDataFolder(), "config.yml"));

            config.set("maintenance.enable", Platform.getMaintenance().isEnabled());

            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
