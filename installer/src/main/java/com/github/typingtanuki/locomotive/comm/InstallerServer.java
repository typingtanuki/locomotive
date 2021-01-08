package com.github.typingtanuki.locomotive.comm;

import com.github.typingtanuki.locomotive.executor.CoreExecutor;

import java.io.IOException;
import java.net.DatagramSocket;

public final class InstallerServer {
    private static int port = -1;
    private static DatagramSocket socket;
    private static boolean shook = false;

    private InstallerServer() {
        super();
    }

    public static void start() throws IOException {
        socket = new DatagramSocket();
        socket.setReuseAddress(false);

        // Binding on port 0 will choose a random free port
        port = socket.getLocalPort();
        CoreExecutor.init();
        CoreExecutor.execute(new InstallerServerThread(socket));
        System.out.println("server started on port " + port);
    }

    public static void stop() throws IOException {
        if (port == -1) {
            return;
        }
        socket.close();
        socket = null;
        port = -1;
    }

    public static int getPort() {
        return port;
    }

    public static void waitHandshake() {
        System.out.println("Waiting for handshake");
        while (!shook) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void shaked() {
        shook = true;
    }
}
