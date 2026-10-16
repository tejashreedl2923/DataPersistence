
# Dear, Diary - Notes App

## Overview

Dear, Diary is a simple and aesthetically designed Android Notes application developed using Kotlin and XML in Android Studio. The application allows users to create, save, search, view, and delete personal notes using a locally stored SQLite database.

The application features a burgundy-themed interface with a dark background, soft blush-pink note cards, rounded corners, and elegant typography. It demonstrates Android data persistence using SQLite and SharedPreferences.

## Aim

To develop an Android application that demonstrates data persistence using SQLite and SharedPreferences while providing a simple and user-friendly interface for managing personal notes.

## Objectives

- To understand data persistence in Android applications.
- To implement SQLite for storing and retrieving notes.
- To use SharedPreferences for storing small application settings.
- To create an interactive user interface using Kotlin and XML.
- To implement basic note management operations.
- To design an aesthetically pleasing Android application.

## Technologies Used

- **Programming Language:** Kotlin
- **User Interface:** XML
- **Development Environment:** Android Studio
- **Local Database:** SQLite
- **Data Persistence:** SQLite and SharedPreferences
- **UI Components:** TextView, EditText, Button, LinearLayout, ScrollView
- **Design:** Burgundy background with blush-pink note cards

## Features

### 1. Create Notes

Users can enter a title and content to create personal notes.

### 2. Save Notes

Notes are stored locally in an SQLite database and remain available after closing and reopening the application.

### 3. View Notes

Saved notes are displayed as light-colored cards against a dark burgundy background. Each card displays the note title, content, and date.

### 4. Search Notes

Users can search for notes by entering keywords from the title or content.

### 5. Delete Notes

Users can delete individual notes that are no longer required.

### 6. Data Persistence

SQLite stores note records locally, while SharedPreferences can store small application settings such as user preferences.

### 7. Burgundy Theme

The interface uses a deep burgundy background, blush-pink cards, rose accents, rounded corners, and elegant typography for a consistent visual appearance.

## Application Workflow

1. Launch the application.
2. View the Notes home screen.
3. Enter a title and content for a new note.
4. Tap the Save button.
5. The note is stored in the SQLite database.
6. View the saved note in the notes section.
7. Search for a note using the search field.
8. Delete a note when it is no longer needed.

## Database Design

The application uses an SQLite database named `NotesDB` with a table named `notes`.

| Column | Data Type | Description |
|---|---|---|
| id | INTEGER | Unique identifier for each note; primary key with auto-increment |
| title | TEXT | Title of the note |
| content | TEXT | Body of the note |
| date | TEXT | Date associated with the note |

## Data Persistence

### SQLite

SQLite is used to store and retrieve notes locally on the device. The `DatabaseHelper` class extends `SQLiteOpenHelper` and manages database creation and note-related operations.

### SharedPreferences

SharedPreferences provides a lightweight mechanism for storing small key-value settings locally. It can be used to retain application preferences between sessions.

## Project Structure

```text
DataPersistenceDemo/
├── app/
│   └── src/
│       └── main/
│           ├── java/
│           │   └── com/
│           │       └── example/
│           │           └── datapersistencedemo/
│           │               ├── MainActivity.kt
│           │               └── DatabaseHelper.kt
│           ├── res/
│           │   ├── drawable/
│           │   │   └── search_background.xml
│           │   ├── layout/
│           │   │   └── activity_main.xml
│           │   └── values/
│           └── AndroidManifest.xml
├── README.md
└── build.gradle.kts
```

## Installation and Execution

1. Install Android Studio on your computer.
2. Clone or download this repository.
3. Open the project in Android Studio.
4. Allow Gradle to sync and resolve dependencies.
5. Create an Android emulator or connect a physical Android device.
6. Run the application using the Run button.
7. Start creating and managing notes.

## Testing

| Test Case | Expected Result | Status |
|---|---|---|
| Launch the application | Notes home screen is displayed | Pass |
| Save a note with a title and content | Note is saved in SQLite | Pass |
| Save a note with missing fields | Validation message is displayed | Pass |
| View saved notes | Notes appear as cards | Pass |
| Search using a title keyword | Matching notes are displayed | Pass |
| Search using a content keyword | Matching notes are displayed | Pass |
| Delete an individual note | Selected note is removed | Pass |
| Restart the application | Previously saved notes remain available | Pass |

## Screenshots

Add screenshots of the application in the following locations.

### Home Screen
<img width="1366" height="720" alt="Screenshot 2026-10-16 131147" src="https://github.com/user-attachments/assets/7b0a3b27-b4c9-405d-9eaf-24190e966be8" />


<img width="718" height="1598" alt="image" src="https://github.com/user-attachments/assets/3939ae40-a6e3-4cb9-adec-03620451f738" />


### Create and Save a Note

<img width="718" height="1598" alt="image" src="https://github.com/user-attachments/assets/21472887-3d07-4a5d-b5fa-553cdc9c262b" />

### Saved Notes

<img width="718" height="1598" alt="image" src="https://github.com/user-attachments/assets/6b572c55-2654-48ef-9f07-fac3b604fe9e" />


### Search Notes

<img width="718" height="1598" alt="image" src="https://github.com/user-attachments/assets/800a8658-dbfc-4ce9-8aae-c20415b2bec4" />


### Delete Note



## Learning Outcomes

- Learned how data persistence works in Android.
- Understood the use of SQLite databases for local storage.
- Learned how to create and manage database tables.
- Implemented basic note creation, retrieval, search, and deletion.
- Understood the purpose of SharedPreferences for storing application settings.
- Improved Kotlin programming and XML layout design skills.
- Gained experience building a functional Android application.


## Conclusion

Dear, Diary demonstrates the implementation of data persistence in Android using Kotlin, XML, SQLite, and SharedPreferences. The application provides a simple interface for managing personal notes while illustrating how locally stored data can remain available across application sessions.

The project also provides practical experience with Android UI development, database operations, input validation, and local data management.
