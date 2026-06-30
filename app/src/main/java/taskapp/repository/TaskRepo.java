package taskapp.repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import taskapp.config.LocalDateAdapter;
import taskapp.model.Task;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

public class TaskRepo {
    private final String filePath;
    private final Gson gson;
    private List<Task> tasks;

    public TaskRepo(String filePath) {
        this.filePath = filePath;

        try {
            Path path = Paths.get(filePath);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
        } catch (IOException e) {
            System.err.println("Could not create data directories: " + e.getMessage());
        }

        this.gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .setPrettyPrinting()
            .create();

        this.tasks = loadFromFile();
    }

    public void addTask(Task task) {
        tasks.add(task);
        saveToFile();
    }

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(tasks, writer);
        } catch (IOException e) {
            System.err.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public List<Task> loadFromFile() {
        File file = new File(filePath);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (FileReader reader = new FileReader(file)) {
            Type listType = new TypeToken<ArrayList<Task>>(){}.getType();
            List<ArrayList<Task>> loadedTasks = gson.fromJson(reader, listType);
            return loadedTasks != null ? (List) loadedTasks : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error reading tasks from file, starting fresh: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    
}
