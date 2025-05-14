import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import Collect.*;
//import model.Student;

/**
 * Console application for demonstrating collection usage with Student records.
 * (Primarily for testing and demonstration; main GUI is in MainFrame.java)
 */
public class App {
    /**
     * Main entry point for the console demo. Connects to DB, shows menu, and demonstrates collections.
     *
     * Arguments:
     *   args (String[]): Command-line arguments (not used)
     *
     * Return:
     *   void
     */
    public static void main(String[] args) {
        // Database connection details
        // url: JDBC URL for the MySQL database. Used for DB connection in main(). Type: String
        String url = "jdbc:mysql://localhost:3306/DEPARTMENT";
        // user: Username for the MySQL database. Used for DB connection in main(). Type: String
        String user = "root";
        // password: Password for the MySQL database. Used for DB connection in main(). Type: String
        String password = "";
        // sc: Scanner for reading user input from the console. Used in main() for menu selection. Type: Scanner
        Scanner sc = new Scanner(System.in);

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
        // ch: Stores the user's menu choice. Used in main() for switch-case. Type: int
        int ch;

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {

            do {
                // Execute query to fetch student records
                // resultSet: ResultSet containing all rows from STUDENT table. Used in collection demos. Type: ResultSet
                ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENT");
                System.out.println("Connected to the database successfully!");
                
                // Display menu options for collection demonstration
                System.out.println("1. ArrayList Collection");
                System.out.println("2. LinkedList Collection");
                System.out.println("3. Stack Collection");
                System.out.println("4. Queue Collection");
                System.out.println("5. HashSet Class");
                System.out.println("6. LinkedHashSet Collection");
                System.out.println("7. TreeSet Collection");
                
                System.out.println("Enter the choice : ");
                ch = sc.nextInt();

                // Perform action based on user choice
                switch (ch) {
                    case 1: 
                        // Demonstrates ArrayList collection with student records
                        Arraylist.addArrayList(resultSet);
                        break;
                    case 2: 
                        // Demonstrates LinkedList collection with student records
                        Linkedlist.addLinkedList(resultSet);
                        break;
                    case 3: 
                        // Demonstrates Stack collection with student records
                        StackCollec.addStackCollec(resultSet);
                        break;
                    case 4: 
                        // Demonstrates Queue collection with student records
                        QueueCollec.addQueue(resultSet);
                        break;
                    case 5: 
                        // Demonstrates HashSet collection with student records
                        Hashset.addHashSet(resultSet);
                        break;
                    case 6: 
                        // Demonstrates LinkedHashSet collection with student records
                        Linkedhashset.addLinkedHashSet(resultSet);
                        break;
                    case 7: 
                        // Demonstrates TreeSet collection with student records
                        Treeset.addTreeSet(resultSet);
                        break;
                
                    default:
                        // Handles invalid menu choices
                        break;
                }
            } while (ch != 0);

        } catch (SQLException e) {
            System.err.println("Database connection or query failed.");
            e.printStackTrace();
        }
    
        sc.close();
    }
}
