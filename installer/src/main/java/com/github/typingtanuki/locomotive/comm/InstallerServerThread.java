package com.github.typingtanuki.locomotive.comm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

import static com.github.typingtanuki.locomotive.comm.InstallerMessenger.SEPARATOR;

public class InstallerServerThread implements Runnable {
    private static final CharsetDecoder DECODER = StandardCharsets.UTF_8.newDecoder();
    private static final CharsetEncoder ENCODER = StandardCharsets.UTF_8.newEncoder();

    private final DatagramSocket socket;


    public InstallerServerThread(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                byte[] buf = new byte[2048];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                ByteBuffer buffer = ByteBuffer.wrap(packet.getData());
                String s = DECODER.decode(buffer).toString().replaceAll("\\s"," ").trim();
                handleMessage(s);
            }
        } catch (IOException e) {
            System.err.println("Server socket crashed");
            e.printStackTrace(System.err);
        }
    }

    private void handleMessage(String message) {
        String[] split = message.split(SEPARATOR);
        if (split.length != 5) {
            System.err.println("Unknown message " + message);
            return;
        }
        int id = Integer.parseInt(split[0]);
        boolean finish = Boolean.parseBoolean(split[1]);
        boolean success = Boolean.parseBoolean(split[2]);
        MessageType commandId = MessageType.valueOf(split[3]);
        String commandBody = split[4];

        switch (commandId) {
            case shake:
                handleShake(message, id, finish, success, commandBody);
                return;
            default:
                System.err.println("Unknown command " + message);
        }
    }

    private void handleShake(String message, int id, boolean finish, boolean success, String commandBody) {
        if (id == -1 && finish && success && commandBody.isBlank()) {
            InstallerServer.shaked();
            return;
        }
        System.err.println("Unknown shake " + message);
    }
}
