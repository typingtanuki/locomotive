package com.github.typingtanuki.locomotive.comm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class InstallerMessenger implements AutoCloseable {
    private final DatagramSocket socket;
    private final int port;

    public InstallerMessenger(int port) throws SocketException {
        this.port = port;
        socket = new DatagramSocket();
    }

    public void send(String data) throws IOException {
        byte[] packet = data.getBytes(StandardCharsets.UTF_8);
        socket.send(new DatagramPacket(packet, packet.length, InetAddress.getLocalHost(), port));
    }

    @Override
    public void close() {
        socket.close();
    }
}
