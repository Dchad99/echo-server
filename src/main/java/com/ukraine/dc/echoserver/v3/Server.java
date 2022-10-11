package com.ukraine.dc.echoserver.v3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            while (true) {
                var socket = serverSocket.accept();
                new Thread(() -> startServer(socket)).start();
            }
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private static void startServer(Socket socket) {
        try (var inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String line;

            while (!(line = inputStream.readLine()).isEmpty()) {
                outputStream.write("Echo: " + line + "\n");
                outputStream.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
