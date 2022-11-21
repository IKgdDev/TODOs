package ru.netology.javacore;

public class RemoveCommand implements Command{
    private Todos todos;

    public RemoveCommand(Todos todos) {
        this.todos = todos;
    }

    @Override
    public void execute(String task) {
        todos.removeTask(task);
    }
}
