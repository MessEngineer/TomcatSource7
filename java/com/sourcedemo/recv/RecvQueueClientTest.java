package com.sourcedemo.recv;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.locks.LockSupport;

public class RecvQueueClientTest {

    public static void main(String[] args) throws IOException {
        try {
            for (int i = 0; i < 7; i++) {
                Socket socket = new Socket("127.0.0.1",8112);
                System.out.println("Client:" + socket + ", isConnected:" + socket.isConnected());
                OutputStream outputStream = socket.getOutputStream();
                outputStream.write('a');
            }
        } catch (IOException e) {
            e.printStackTrace();
            LockSupport.park();

        }
        LockSupport.park();



    }
}
