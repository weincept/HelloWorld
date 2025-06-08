package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 8080; // Koyeb looks for a running service on this port
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();

        System.out.println("Server started at http://localhost:" + port);
    }

    static class MyHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello from Java!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
