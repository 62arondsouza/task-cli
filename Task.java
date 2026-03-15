import java.time.LocalDateTime;

public class Task {

    private static int lastId;
    private int id;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(String description) {
        this.id = ++lastId;
        this.description = description;
        this.status = Status.TODO;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public String toJson() {
        return String.format(
            "{\"id\":\"%d\", \"description\":\"%s\", \"status\":\"%s\", \"createdAt\":\"%s\", \"updatedAt\":\"%s\"}",
            id, description, status, createdAt, updatedAt
        );
    }

    public static Task fromJson(String json) {
        json = json.replace("{", "")
                .replace("}", "")
                .replace("\"", "");
        
        String[] jsonAttributes = json.split(", ");

        String id = jsonAttributes[0].split(":")[1].strip();
        String description = jsonAttributes[1].split(":")[1].strip();
        String statusStr = jsonAttributes[2].split(":")[1].strip();
        String createdAtStr = jsonAttributes[3].split("[a-z]:")[1].strip();
        String updatedAtStr = jsonAttributes[4].split("[a-z]:")[1].strip();

        Status status = Status.valueOf(statusStr.toUpperCase());

        Task task = new Task(description);
        task.id = Integer.parseInt(id);
        task.status = status;
        task.createdAt = LocalDateTime.parse(createdAtStr);
        task.updatedAt = LocalDateTime.parse(updatedAtStr);
        if(task.id > lastId) {
            lastId = task.id;
        }
        return task;
    }

    public void updateDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDateTime.now();
    }

    public void markInProgress() {
        this.status = Status.IN_PROGRESS;
        this.updatedAt = LocalDateTime.now();
    }

    public void markDone() {
        this.status = Status.DONE;
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format(
            "id: %d, description: %s, status: %s, createdAt: %s, updatedAt: %s",
            id, description, status, createdAt, updatedAt
        );
    }


}