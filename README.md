# Task Management App

A simple Java desktop application for managing tasks, built with Swing for the user interface.

## Features

Add Task: Create a new task with a title and description.
Modify Task: Edit the details of an existing task.
Delete Task: Remove a task from the list.
View Tasks: Display all tasks in a scrollable list.

## Technologies Used

Java SE: Core programming language.
Swing: For building the graphical user interface.
MVC Architecture: Separates concerns for better maintainability.

```bash
src/
├── connection/
│   └── DatabaseConnection.java
│   └── DatabaseEnvVar.java
├── models/
│   └── Task.java         # Task model class
├── persistance/
│   └── impl/
|   |    └── TaskDAOImpl
│   └── TaskDAO.java
├── services/
│   └── TaskService.java  # Business logic for task management
├── tests/
│   └── DatabaseTest.java
│   └── TaskServiceTest.java
└── views/
|   └── View.java          #  Application GUI
|
└── App.java               # Main Application

```

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`DATABASE_URL` : Exemple ("jdbc:mysql://localhost:3306/")

`DATABASE_NAME`

`DATABASE_USER_NAME`

`DATABASE_USER_PASSWORD`

## Run Locally

Clone the project

```bash
  git clone https://github.com/yourusername/task-management-app.git

```

Go to the project directory

```bash
  cd src

```

Complie the App Class

```bash
  javac App.java

```

Start the App

```bash
  java App

```

## 🔗 Links

[![linkedin](https://img.shields.io/badge/linkedin-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mohamed-aziz-jlassi/)
