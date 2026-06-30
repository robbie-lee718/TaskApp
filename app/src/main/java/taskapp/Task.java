package taskapp;

import java.time.LocalDate;

public class Task {
    public enum Priority {NONE, LOW, MEDIUM, HIGH}

    private int id;
    private String name;
    private String description;
    private boolean completed;
    private LocalDate dueLocalDate;
    private Priority priority;

    public Task(int id, String name, String description, LocalDate dueLocalDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueLocalDate = dueLocalDate;
        this.completed = false;
        this.priority = Priority.NONE;
    }

    public Task(int id, String name, String description, LocalDate dueLocalDate, Priority priority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dueLocalDate = dueLocalDate;
        this.completed = false;
        this.priority = priority;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() {return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    public LocalDate getDueLocalDate() { return dueLocalDate; }
    public void setDueLocalDate(LocalDate dueLocalDate) {this.dueLocalDate = dueLocalDate;}

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    
    @Override
    public String toString() {
        return String.format("[%s] ID: %d | %s - %s (Due: %s, Priority: %s)", 
            completed ? "X" : " ", id, name, description, dueLocalDate, priority);
    }
}
