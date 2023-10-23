# Android ToDo Application Demo using Room DB and MVP

## Introduction

This repository contains a simple ToDo application for Android, which is designed using the Model-View-Presenter (MVP) architecture pattern. The application leverages the power of Room Persistence Library to offer robust database operations and efficient data management.

## Features

- **CRUD Operations**: Users can easily add, read, update, and delete tasks.
- **Offline Persistence**: The application uses Room DB, ensuring that the user's ToDo items are saved offline and can be accessed without an internet connection.
- **MVP Architecture**: By adhering to the MVP pattern, the app ensures a clear separation of concerns, enhancing code maintainability and testability.
- **Intuitive UI**: The app boasts a user-friendly interface, making task management an effortless endeavor.

## Key Components

1. **Model**: Represents the data source. In this demo, it's majorly based on the Room Database.
2. **View**: Displays the data to the user and sends user commands to the Presenter.
3. **Presenter**: Contains the logic to fetch data from the Model, and to update the View.

## Requirements

- **Android Studio**: To compile and run the project.
- **Android Device/Emulator**: Running API level 21 or higher.
