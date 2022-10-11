package com.ukraine.dc.echoserver.v1;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try (var socket = new Socket("localhost", 3000);
             var inputStream = new BufferedInputStream(socket.getInputStream());
             var outputStream = new BufferedOutputStream(socket.getOutputStream())) {

            int count;
            byte[] buffer = new byte[100];
            while ((count = System.in.read(buffer)) != -1) {
                outputStream.write(new String(buffer, 0, count).getBytes());
                outputStream.flush();

                count = inputStream.read(buffer);
                System.out.println(new String(buffer, 0, count));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
