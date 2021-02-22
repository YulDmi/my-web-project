package com.geekbrains.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class SocketHttpClient {

    public static void main(String[] args) throws IOException {
        sendRequest("localhost", 8189, "GET", "/socket/persons/4");
        sendRequest("localhost", 8189, "POST", "/socket/persons?id=10&name=Vasya2");
    }

    public static void sendRequest(String host, int port, String method, String path) throws IOException {
        String content_Type = "application/json;charset=";
        String charsetName = "UTF-8";
        try (Socket socket = new Socket(host, port)) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%s %s HTTP/1.1\r\n", method, path))
                    .append(String.format("Host: %s:%d\r\n", host, port))
                    .append(String.format("Accept: %s%s\r\n", content_Type, charsetName))
                    .append("Connection: close\r\n")
                    .append(String.format("Content-Type: %s%s\r\n", content_Type, charsetName))
                    .append("\r\n");
            socket.getOutputStream().write(sb.toString().getBytes(charsetName));
            socket.getOutputStream().flush();

            new Response(socket.getInputStream());
        }
    }



    private static class Response {

        public Response(InputStream in) throws IOException {
            String line;

            try (BufferedReader br = new BufferedReader((new InputStreamReader(in)))) {
                while (true) {
                    line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    System.out.println(line);
                }
            }
        }
    }
}
