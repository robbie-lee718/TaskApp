package taskapp;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        TaskRepo repo = new TaskRepo("src/main/resources/data/tasks.json");

        repo.saveToFile();
    }
}
