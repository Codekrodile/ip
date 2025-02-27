# Lubot - Your Personal Task Manager

Lubot is a **task management chatbot** that helps you organize your daily tasks efficiently. It provides an intuitive **graphical user interface (GUI)** built with **JavaFX**, and supports **saving and loading** tasks for seamless task tracking.

---

## Features

✅ **Task Management** – Add, delete, mark, and unmark tasks.  
✅ **Task Types** – Supports To-dos, Deadlines, Events, and Fixed-Duration Tasks.  
✅ **Find Tasks** – Search for tasks by keywords.  
✅ **GUI** – A modern JavaFX-based graphical interface.  
✅ **Data Persistence** – Tasks are automatically saved to disk.  
✅ **Graceful Error Handling** – Handles incorrect inputs and file errors smoothly.  
✅ **Exit Command** – Allows the user to type `exit` to close the application.  

---

## Getting Started

### Prerequisites
- **Java 11** or later
- **JavaFX** (Included in JAR build)
- **Gradle** (for building from source)

### Running the Application

#### Method 1: Running via JAR
```sh
java -jar build/libs/lubot.jar
```
*(Ensure JavaFX runtime components are included in the JAR.)*

#### Method 2: Running from Source
1. Clone this repository:
```sh
git clone https://github.com/your-username/lubot.git
cd lubot
```
2. Run with Gradle:
```sh
./gradlew run
```

---

## Usage

| Command | Example | Description |
|---------|---------|-------------|
| `todo <description>` | `todo Read book` | Adds a to-do task |
| `deadline <description> /by <YYYY-MM-DD>` | `deadline Submit report /by 2024-03-10` | Adds a deadline |
| `event <description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>` | `event Conference /from 2024-03-12 /to 2024-03-14` | Adds an event |
| `fixed <description> /duration <hours>` | `fixed Read paper /duration 2` | Adds a fixed-duration task |
| `list` | `list` | Shows all tasks |
| `mark <index>` | `mark 1` | Marks task as done |
| `unmark <index>` | `unmark 1` | Unmarks a task |
| `delete <index>` | `delete 2` | Deletes a task |
| `find <keyword>` | `find book` | Finds tasks containing the keyword |
| `help` | `help` | Displays available commands |
| `exit` | `exit` | Exits the application |

---

## Code Quality

This project follows:
- **Checkstyle** – Ensures adherence to Java coding standards.
- **JUnit Testing** – Includes unit tests for core functionalities.
- **Assertions** – Uses assertions to document assumptions in the code.

---

## Contributing

Contributions are welcome!  
To contribute:
1. Fork the repository.
2. Create a new branch (`feature-branch`).
3. Commit your changes with meaningful messages.
4. Push and open a pull request.

---

## Acknowledgments
- **JavaFX Documentation** for GUI implementation.  
- **SE-EDU Guides** for software engineering practices.

