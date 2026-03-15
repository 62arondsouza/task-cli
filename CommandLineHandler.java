import java.util.List;

public class CommandLineHandler {
    private TaskService service = new TaskService();

    public void handle(String[] args) {
        if(args.length == 0) {
            printCommands();
            return;
        }
        switch (args[0]) {
            
            case "add": {
                if(args.length < 2) {
                    System.err.println("Usage: task-cli add <description>");
                    return;
                }
                Task task = service.addTask(args[1]);
                System.out.println("Task added sucessfully (ID: "+ task.getId() + ")");
                break;
            }

            case "update": {
                if(args.length < 2 || args.length < 3) {
                    System.err.println("Usage: task-cli update <id> <newDescription>");
                    return;
                }
                service.updateTask(args[1], args[2]);
                break;
            }
            
            case "delete": {
                if(args.length < 2) {
                    System.err.println("Usage: task-cli delete <id>");
                    return;
                }
                service.removeTask(args[1]);
                break;
            }

            case "mark-in-progress": {
                if(args.length < 2) {
                    System.err.println("Usage: task-cli mark-in-progress <id>");
                    return;
                }
                service.markInProgress(args[1]);
                break;
            }

            case "mark-done": {
                if(args.length < 2) {
                    System.err.println("Usage: task-cli mark-done <id>");
                    return;
                }
                service.markDone(args[1]);
                break;
            }

            case "list": {
                if(args.length < 2) {
                    List<Task> tasks = service.findAllTasks();
                    tasks.forEach(System.out::println);
                } else if(args.length < 3) {
                    if(args[1].toLowerCase().equals("done")) {

                        List<Task> tasksDone = service.findTasksDone();
                        tasksDone.forEach(System.out::println);
                    
                    } else if(args[1].toLowerCase().equals("todo")) {
                        
                        List<Task> tasksToDo = service.findTasksToDo();
                        tasksToDo.forEach(System.out::println);
                    
                    } else if(args[1].toLowerCase().equals("in-progress")) {
                        
                        List<Task> tasksInProgress = service.findTasksInProgress();
                        tasksInProgress.forEach(System.out::println);

                    } else {
                        System.err.println("Usage: task-cli list <status>");
                        System.err.println("statuses: todo, in-progress, done");
                    }
                }
                break;
            }

            default: {
                printCommands();
                break;
            }
        }
        service.saveTasks();
    }

    private void printCommands() {
        System.err.println("Usage: task-cli <operation> <args>");
        System.err.println("Operations are:");
        System.err.println("add <description>");
        System.err.println("update <id> <newDescription>");
        System.err.println("delete <id>");
        System.err.println("mark-in-progress <id>");
        System.err.println("mark-done <id>");
        System.err.println("list");
        System.err.println("list <status>");
        System.err.println("Statuses are:");
        System.err.println("done");
        System.err.println("todo");
        System.err.println("in-progress");
    }
}
