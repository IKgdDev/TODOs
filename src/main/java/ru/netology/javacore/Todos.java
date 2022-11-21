package ru.netology.javacore;

import java.util.Set;
import java.util.TreeSet;

public class Todos {
    protected Set<String> tasks;

    public Todos() {
        this.tasks = new TreeSet<>();
    }

    public void addTask(String task) {
        if (tasks.size() < 7) {
            tasks.add(task);
        }
    }

    public void removeTask(String task) {
        tasks.remove(task);
    }

    public String getAllTasks() {
        StringBuilder result = new StringBuilder();
        for (String task : tasks) {
            result.append(task + " ");
        }
        return result.toString().strip();
    }

}
