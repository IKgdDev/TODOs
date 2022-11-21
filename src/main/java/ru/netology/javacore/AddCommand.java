package ru.netology.javacore;

public class AddCommand implements Command {
    private Todos todos;

    public AddCommand(Todos todos) {
        this.todos = todos;
    }

    @Override
    public void execute(String task) {
        todos.addTask(task);
    }
}
