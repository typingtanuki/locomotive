package com.github.typingtanuki.locomotive.comm;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class InstallerServerThread implements Runnable {
    private DatagramSocket socket;

    public InstallerServerThread(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                byte[] buf = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                System.out.println("woop woop");
                InstallerServer.shaked();
            }
        } catch (IOException e) {
            System.err.println("Server socket crashed");
            e.printStackTrace(System.err);
        }
    }
}
