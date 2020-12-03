package com.sourcedemo.recv;

import java.io.IOException;
import java.net.ServerSocket;

public class RecvQueueServerTest {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8112,10);
        serverSocket.accept();
        System.in.read();
    }
}
