package com.midup.listener.ping;

import com.midup.platform.Platform;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class PingListener implements Listener {

    @EventHandler
    public void onPing(ProxyPingEvent event) {
        if (Platform.getMaintenance().isEnabled()) {
            event.getResponse().setDescription(Platform.getMaintenance().getMotd());
            return;
        } else {
            event.getResponse().setDescription(Platform.getMotd());
        }

    }
}
