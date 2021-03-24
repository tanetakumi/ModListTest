package net.serveron.hane.getmodlist;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.PlayerHandshakeEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

public class PacketListener implements Listener {
    private final GetModList plugin;
    public PacketListener(GetModList plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onConnected(ServerConnectedEvent e){
        System.out.println("ConnectedEvent");
        System.out.println("ModList: "+e.getPlayer().getModList());

        plugin.getProxy().getScheduler().schedule(plugin, new Runnable() {
            @Override
            public void run() {
                System.out.println("SendPacket");
/*
                try {
                    byte [] handshakeMessage = new byte[128];
                    Socket socket = new Socket();
                    DataOutputStream output = new DataOutputStream();
                    // C->S : Handshake State=1
                    // send packet length and packet
                    writeVarInt(output, handshakeMessage.length);
                    output.write(handshakeMessage);

                    // C->S : Request
                    output.writeByte(0x01); //size is only 1
                    output.writeByte(0x00); //packet id for ping
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }*/
                 //createHandshakeMessage(address, port);

/*

                e.getPlayer().sendData("fml:handshake", new byte[]{-2,0});
                e.getPlayer().sendData("fml:handshake", new byte[]{0, 2,0,0,0});
                e.getPlayer().sendData("FML|HS", new byte[]{-2});
                e.getPlayer().sendData("FML|HS", new byte[]{0, 2});

 */
                //e.getPlayer().sendData("fml:handshake", new byte[]{2, 0, 0, 0});

                System.out.println("ModList: "+e.getPlayer().getModList());
                System.out.println("SendPacketFinish");
            }
        }, 10, 600, TimeUnit.SECONDS);

        System.out.println("ConnectedEventFinish");

    }
    @EventHandler
    public void onHandshake(PlayerHandshakeEvent e){
        System.out.println("Handshake");
        System.out.println(e.getHandshake().getHost());
    }
    @EventHandler
    public void onLogin(LoginEvent e){
        System.out.println("Login");
        if(e.getConnection().getListener() instanceof ProxiedPlayer){
            System.out.println(((ProxiedPlayer) e.getConnection().getListener()).getModList());
        }

    }

    public static void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
        while (true) {
            if ((paramInt & 0xFFFFFF80) == 0) {
                out.writeByte(paramInt);
                return;
            }

            out.writeByte(paramInt & 0x7F | 0x80);
            paramInt >>>= 7;
        }
    }
}
