package com.ukraine.dc.echoserver.v1;

import java.io.*;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(3000);
        try (var socket = serverSocket.accept();
             var inputStream = new BufferedInputStream(socket.getInputStream());
             var outputStream = new BufferedOutputStream(socket.getOutputStream())) {

            int count;
            byte[] buffer = new byte[100];
            while ((count = inputStream.read(buffer)) != -1) {
                outputStream.write(("Echo: " + new String(buffer, 0, count)).getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
