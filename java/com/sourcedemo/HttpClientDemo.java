package com.sourcedemo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HttpClientDemo {

    public static void main(String[] args) throws Exception{
        HttpClientDemo httpClientDemo = new HttpClientDemo();
        //httpClientDemo.testContentLength();
        httpClientDemo.testChunk();
        //httpClientDemo.testErrorContentLength();
    }

    void testContentLength() throws IOException {
        StringBuffer get = new StringBuffer(200);
        get.append("GET /webExample/example HTTP/1.1\r\n");
        get.append("Host: 127.0.0.1:8080\r\n");
        get.append("Accept: text/html\r\n");
        get.append("Connection: Close\r\n");
        get.append("Content-Length: 11\r\n");
        //get.append("Transfer-Encoding: chunked\r\n");
        get.append("\r\n");
        get.append("requestBody");
        System.out.println(get);

        Socket socket = new Socket("127.0.0.1", 8080);
        socket.setSoTimeout(30000);

        PrintWriter outWriter = new PrintWriter(socket.getOutputStream());
        outWriter.println(get);
        outWriter.flush();

        BufferedReader inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 以下是服务器返回的数据
        System.out.println("**************** Server responsed ****************");
        String tmp = "";
        while ((tmp = inReader.readLine()) != null) {
            // 解析服务器返回的数据，做相应的处理
            System.out.println(tmp);
        }
        outWriter.close();
        inReader.close();
    }

    void testChunk() throws Exception {
        StringBuffer get = new StringBuffer(200);
        get.append("GET /webExample/example HTTP/1.1\r\n");
        get.append("Host: 127.0.0.1:8080\r\n");
        get.append("Accept: text/html\r\n");
        get.append("Connection: Close\r\n");
        get.append("Transfer-Encoding: chunked\r\n");
        get.append("\r\n");

        //chunk传输
        get.append("6\r\n");
        get.append("hello,\r\n");

        get.append("8\r\n");
        get.append("chunked!\r\n");

        get.append("0\r\n");
        get.append("\r\n");

        System.out.println(get);

        Socket socket = new Socket("127.0.0.1", 8080);
        socket.setSoTimeout(30000);

        PrintWriter outWriter = new PrintWriter(socket.getOutputStream());
        outWriter.println(get);
        outWriter.flush();

        BufferedReader inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 以下是服务器返回的数据
        System.out.println("**************** Server responsed ****************");
        String tmp = "";
        while ((tmp = inReader.readLine()) != null) {
            // 解析服务器返回的数据，做相应的处理
            System.out.println(tmp);
        }
        outWriter.close();
        inReader.close();
    }

    void testErrorContentLength() throws IOException {
        StringBuffer get = new StringBuffer(200);
        get.append("GET /webExample/example HTTP/1.1\r\n");
        get.append("Host: 127.0.0.1:8080\r\n");
        get.append("Accept: text/html\r\n");
        get.append("Connection: keep-alive\r\n");
        get.append("Content-Length: 10\r\n");
        get.append("\r\n");
        get.append("requestBody");
        System.out.println(get);

        Socket socket = new Socket("127.0.0.1", 8080);
        socket.setSoTimeout(30000);

        PrintWriter outWriter = new PrintWriter(socket.getOutputStream());
        outWriter.print(get);
        outWriter.flush();
        outWriter.println(get);
        outWriter.flush();

        BufferedReader inReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        // 以下是服务器返回的数据
        System.out.println("**************** Server responsed ****************");
        String tmp = "";
        while ((tmp = inReader.readLine()) != null) {
            // 解析服务器返回的数据，做相应的处理
            System.out.println(tmp);
        }
        outWriter.close();
        inReader.close();
    }

}
