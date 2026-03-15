import java.util.List;

public class TaskService {
    private final TaskRepository repo = new TaskRepository();

    public Task addTask(String description) {
        Task task = new Task(description);
        repo.addTask(task);
        return task;
    }

    public void removeTask(String idStr) {
        int id = Integer.parseInt(idStr);
        repo.removeTask(id);
    }

    public void updateTask(String idStr, String newDescription) {
        int id = Integer.parseInt(idStr);
        repo.updateTask(id, newDescription);
    }

    public void markInProgress(String idStr) {
        int id = Integer.parseInt(idStr);
        Task task = repo.findTask(id);
        task.markInProgress();
    }

    public void markDone(String idStr) {
        int id = Integer.parseInt(idStr);
        Task task = repo.findTask(id);
        task.markDone();
    }

    public List<Task> findAllTasks() {
        return repo.findAllTasks();
    }

    public List<Task> findTasksToDo() {
        return repo.findTasksToDo();
    }

    public List<Task> findTasksInProgress() {
        return repo.findTasksInProgress();
    }

    public List<Task> findTasksDone() {
        return repo.findTasksDone();    
    }

    public void saveTasks() {
        repo.saveTasks();
    }
}