# JD-ConnectDB

A Java Swing application for managing Student and Course records using various Java collections, with MySQL database integration.

## Features
- Add, view, and delete Student and Course records
- Choose between different Java collections (ArrayList, LinkedList, Stack, Queue, HashSet, TreeSet)
- GUI built with Java Swing
- Data persistence with MySQL
- Collection display order matches the semantics of the selected collection

## Project Structure

- `src/` - Java source files
  - `MainFrame.java` - Main GUI window
  - `CollectionManager.java` - Manages collections and DB sync
  - `DatabaseManager.java` - Handles DB connection and table creation
  - `model/Student.java` - Student record definition
  - `model/Course.java` - Course record definition
  - `Collect/` - Example collection usage classes (console-based)
- `lib/` - External libraries (e.g., MySQL connector)
- `bin/` - Compiled class files

## Main Files & Classes

### MainFrame.java
- Main GUI window for the application
- Lets users select table and collection, add/delete/display records
- See inline JavaDoc for details

### CollectionManager.java
- Generic class to manage a collection of Student or Course objects
- Handles loading from DB, adding, deleting, and switching collection types
- TreeSet sorts by score (Student) or credits (Course)

### DatabaseManager.java
- Handles DB connection and table creation
- See JavaDoc for connection details

### model/Student.java & model/Course.java
- Record types for Student and Course entities
- See JavaDoc for field descriptions

## Usage

1. Ensure MySQL is running and the connection details in `DatabaseManager.java` are correct.
2. Compile the project:
   ```sh
   javac -cp lib/mysql-connector-j-8.3.0.jar -d bin src/**/*.java
   ```
3. Run the application:
   ```sh
   java -cp "bin;lib/mysql-connector-j-8.3.0.jar" MainFrame
   ```

## Notes
- The ID field is auto-generated and not user-editable.
- The display order in the GUI matches the semantics of the selected collection.
- For more details, see inline comments and JavaDoc in each source file.
