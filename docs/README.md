# Lubot User Guide

## Introduction
Lubot is a **task management chatbot** that helps users organize tasks efficiently. It features a user-friendly **JavaFX-based GUI** and supports **saving and loading** tasks seamlessly.

---

## Features

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

### Example
#### Adding Deadlines

- **Command Format:** `deadline <description> /by <YYYY-MM-DD>`
- **Example:** `deadline Submit report /by 2024-03-10`
- **Expected Output:**
```sh
Added a deadline
  [D][ ] Submit report (by: Mar 10 2024)
```

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

#### Method 2: Running from Source
1. Clone this repository:
```sh
git clone https://github.com/Codekrodile/ip
cd lubot
```
2. Run with Gradle:
```sh
./gradlew run
```

## Acknowledgements
- This project was developed with the assistance of ChatGPT, which was used to debug and write documentation.
