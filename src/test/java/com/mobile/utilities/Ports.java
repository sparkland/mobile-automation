package com.mobile.utilities;

import java.io.IOException;
import java.net.ServerSocket;

public class Ports {
    public static String getAvailablePort() throws IOException {
        ServerSocket socket = new ServerSocket(0);
        socket.setReuseAddress(true);
        String port = Integer.toString(socket.getLocalPort());
        socket.close();
        return port;
    }
}
