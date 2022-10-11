package com.ukraine.dc.echoserver.v3;

import java.io.*;
import java.net.Socket;

public class NewClient {
    public static void main(String[] args) {
        try (var socket = new Socket("localhost", 3000)) {
            startClient(socket);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void startClient(Socket socket) {
        try (var inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             var outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             var consoleReader = new BufferedReader(new InputStreamReader(System.in))) {

            String line;
            while (!(line = consoleReader.readLine()).isEmpty()) {
                outputStream.write(line + "\n");
                outputStream.flush();

                line = inputStream.readLine();
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
