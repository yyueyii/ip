# Mario User Guide
Mario is here to make task management a breeze! This easy-to-use chatbot helps you  keep track of all your tasks - add, remove, mark, or unmark them anytime!

## Quick start
1. Download the ```.jar``` file.
2. Open terminal and ```cd``` into the directory with the jar file.
3. Run the application with ```java -jar mario.jar```.
4. Refer to the Features below for details on how to use the application. 

## Features
### Add a todo task: ```todo```
Adds a todo task to the task list. 

Format: ```todo NAME```

Example: 
- ```todo Homework```

### Add a deadline task: ```deadline```
Adds a deadline task to the task list. The deadline input by the user could be in the format yyyy-MM-dd HHmm, which will be formated in the list, or a non-formated deadline, such as "next week".

Format: ```deadline NAME /by DEADLINE```

Examples: 
- ```deadline CS2103T Task /by next week```
- ```deadline CS2103T Task /by 2024-09-20 2359```

### Add an event task: ```event```
Adds an event task to the task list. 

Format: ```event NAME /from START_TIME /to END_TIME```

Example:
- ```event Team meeting /from 5pm /to 7pm```

### Add a fixed duration task: ```fixed```
Adds a fixed duration task to the task list. 

Format: ```fixed NAME /needs REQUIRED_DURATION```

Example:
- ```fixed Revision /needs 2 hours```

### List all tasks in the task list: ```list```
Lists the tasks in the task list. 

Format: ```list```

### Mark a task as done: ```mark```
Marks a task on the task list as completed by its task number. 

Format: ```mark TASK_NUMBER```

Example:
- ```mark 3```

### Unmark a task as done: ```unmark```
Unmarks a task on the task list as not completed by its task number. 

Format: ```unmark TASK_NUMBER```

Example:
- ```unmark 3```

### Remove a task from the task list: ```remove```
Deletes a task from the task list by its task number. 

Format: ```remove TASK_NUMBER```

Example:
- ```remove 3```

### Find a task in the task list: ```find```
Lists tasks with matching names. 

Format: ```find NAME```

Example:
- ```find Homework```

  
