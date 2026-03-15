import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository {
    private static final Path FILE_PATH = Paths.get("tasks.json");
    private List<Task> tasks;

    public TaskRepository() {
        this.tasks = loadTasks();
    }

    public List<Task> loadTasks() {
        List<Task> storedTasks = new ArrayList<>();

        if(!Files.exists(FILE_PATH)) {
            try {
                Files.createFile(FILE_PATH);
                Files.writeString(FILE_PATH, "[]");
            } catch(IOException e) {
                throw new RuntimeException("File cannot be created.");
            }
            return storedTasks;
        }

        try {
            String json = Files.readString(FILE_PATH);
            json = json.replace("[", "")
                    .replace("]", "");
            if(json.isBlank()) {
                return storedTasks;
            }

            String[] tasksJson = json.split("},");
            for(String taskJson : tasksJson) {
                if(!taskJson.endsWith("}")) {
                    taskJson = taskJson + '}';
                }
                storedTasks.add(Task.fromJson(taskJson));
            }
        } catch (IOException e) {
            throw new RuntimeException("File cannot be read.");
        }

        return storedTasks;
    }

    public void saveTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for(int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toJson());
            if(i < tasks.size() - 1) {
                sb.append(",\n");
            }
        }
        sb.append("\n]");
        String json = sb.toString();

        try {
            Files.writeString(FILE_PATH, json);
        } catch (IOException e) {
            throw new RuntimeException("File not found.");
        }
    }

    public Task findTask(int id) {
        return tasks.stream()
            .filter((task) -> task.getId() == id)
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Task not found with ID: " + id));
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int id) {
        Task task = findTask(id);
        tasks.remove(task);
    }

    public void updateTask(int id, String newDescription) {
        Task task = findTask(id);
        task.updateDescription(newDescription);
    }

    public List<Task> findAllTasks() {
        return tasks;
    }

    public List<Task> findTasksInProgress() {
        return tasks.stream()
                .filter((task) -> task.getStatus().equals(Status.IN_PROGRESS))
                .toList();
    }

    public List<Task> findTasksDone() {
        return tasks.stream()
                .filter((task) -> task.getStatus().equals(Status.DONE))
                .toList();
    }

    public List<Task> findTasksToDo() {
        return tasks.stream()
                .filter((task) -> task.getStatus().equals(Status.TODO))
                .toList();
    }
}
