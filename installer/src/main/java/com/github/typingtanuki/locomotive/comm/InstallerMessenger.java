package com.github.typingtanuki.locomotive.comm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class InstallerMessenger implements AutoCloseable {
    protected static final String SEPARATOR = ">_<";
    private final DatagramSocket socket;
    private final int port;

    public InstallerMessenger(int port) throws SocketException {
        this.port = port;
        socket = new DatagramSocket();
    }

    public void send(int id,
                     boolean finish,
                     boolean success,
                     MessageType messageType,
                     String body) throws IOException {
        String fullMessage = id + SEPARATOR + finish + SEPARATOR + success + SEPARATOR + messageType.name() + SEPARATOR + body;
        byte[] packet = fullMessage.getBytes(StandardCharsets.UTF_8);
        socket.send(new DatagramPacket(packet, packet.length, InetAddress.getLocalHost(), port));
    }

    @Override
    public void close() {
        socket.close();
    }
}
