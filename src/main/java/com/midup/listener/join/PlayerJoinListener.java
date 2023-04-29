package com.midup.listener.join;

import com.midup.platform.Platform;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onJoin(LoginEvent event) {
        PendingConnection pendingConnection = event.getConnection();

        if (!Platform.getMaintenance().contains(pendingConnection.getName())) {
            pendingConnection.disconnect(Platform.getMaintenance().getKickMessage());
        }
    }
}
