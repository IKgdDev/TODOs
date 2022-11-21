package ru.netology.javacore;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        String host = "localhost";
        int port = 8989;
        try (
                Socket clientSocket = new Socket(host, port);
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outFile = new PrintWriter("Reply.json");
                BufferedReader inFile = new BufferedReader(new FileReader("Request.json"))
        ) {

            out.println(inFile.readLine());

            outFile.println(in.readLine());
        }
    }
}
