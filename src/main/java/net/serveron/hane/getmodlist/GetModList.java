package net.serveron.hane.getmodlist;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public final class GetModList extends Plugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ProxyServer.getInstance().registerChannel("fml:handshake");
        ProxyServer.getInstance().registerChannel("FML|HS");
        ProxyServer.getInstance().getPluginManager().registerListener(this, new PacketListener(this));
        ProxyServer.getInstance().getPluginManager().registerListener(this, new ReceiveMessage(this));

        //getServer().getMessenger().registerIncomingPluginChannel(plugin, "FML|HS", samurai);
        //getServer().getMessenger().registerOutgoingPluginChannel(plugin, "FML|HS");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
