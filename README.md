# Task Tracker CLI
A simple command-line tool to manage your to-do list: add, update, mark tasks, and track progress.

# Features
Add, update, delete tasks

Mark tasks as **todo**, **in-progress**, or **done**

List tasks by status

Persistent storage using tasks.json

# Setup

```
git clone https://github.com/yourusername/task-cli.git
cd task-cli
javac *.java
```

# Usage
```
# Add a task
java Main add "Buy groceries"
# Output: Task added successfully (ID: 1)

# Update a task
java Main update 1 "Buy groceries and cook dinner"

# Delete a task
java Main delete 1

# Mark task as in progress or done
java Main mark-in-progress 1
java Main mark-done 1

# List tasks
java Main list
java Main list done
java Main list todo
java Main list in-progress
```

# Task Structure

Each task has :  
- id: Unique Identifier  
- description: Task Description  
- status: todo, in-progress or done  
- createdAt: Creation Timestamp  
- updatedAt: Last Update Timestamp  

# Storage

Tasks are saved in tasks.json in the current directory. The file is created automatically.

# Project Requirements
https://roadmap.sh/projects/task-tracker