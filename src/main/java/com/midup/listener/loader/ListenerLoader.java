package com.midup.listener.loader;

import com.midup.MidupCore;
import com.midup.utils.ClassGetter;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;

public class ListenerLoader {

    public static void loadListeners(Plugin plugin, String pack) {
        for (Class<?> listenerClass : ClassGetter.getClassesForPackage(plugin, pack)) {
            if (Listener.class.isAssignableFrom(listenerClass)) {
                try {
                    Listener listener = (Listener)listenerClass.newInstance();
                    MidupCore.getInstance().getProxy().getPluginManager().registerListener(plugin, listener);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
