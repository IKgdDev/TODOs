package ru.netology.javacore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Deque;
import java.util.LinkedList;

public class TodoServer {
    protected Todos todos;
    protected Deque<Request> deque;
    protected int port;

    public TodoServer(Todos todos, int port) {
        this.todos = todos;
        this.port = port;
        deque = new LinkedList<>();
    }

    public void start() throws IOException {

        Gson gson = new GsonBuilder().create();

        System.out.println("Сервер стартовал на порту " + port + " ...");

        try (ServerSocket serverSocket = new ServerSocket(port)) {
        while (true) {
            try (
                    Socket socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream());
            ) {
                Request request = gson.fromJson(in.readLine(), Request.class);
                requestProcessing(request);
                out.println(todos.getAllTasks());
            }
        }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    private void requestProcessing(Request request) {
        switch (request.getType()) {
            case "ADD":
                deque.offerLast(request);
                todos.addTask(request.getTask());
                break;
            case "REMOVE":
                deque.offerLast(request);
                todos.removeTask(request.getTask());
                break;
            case "RESTORE":
                if (!deque.isEmpty()) {
                    Request lastRequest = deque.pollLast();
                    if (lastRequest.getType().equals("ADD")) {
                        todos.removeTask(lastRequest.getTask());
                    } else {
                        todos.addTask(lastRequest.getTask());
                    }
                }
                break;
        }
    }
}