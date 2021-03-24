package net.serveron.hane.getmodlist;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PluginMessageEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.nio.charset.StandardCharsets;

public class ReceiveMessage implements Listener {
    private final GetModList plugin;
    public ReceiveMessage(GetModList plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPluginMessage(PluginMessageEvent e){
        System.out.println("Tag: "+e.getTag());
        System.out.println("Data: "+new String(e.getData(), StandardCharsets.UTF_8));
        if(e.getReceiver() instanceof ProxiedPlayer){
            System.out.println("ModList: "+((ProxiedPlayer) e.getReceiver()).getModList());

        }
    }
}
