import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DatabaseManager handles database connections and initialization for the application.
 */
public class DatabaseManager {
    /** JDBC URL for the MySQL database */
    private static final String URL = "jdbc:mysql://localhost:3306/dept";
    /** Database username */
    private static final String USER = "root"; // Change as needed
    /** Database password */
    private static final String PASSWORD = "lemontree@2025"; // Change as needed

    /**
     * Gets a new connection to the database.
     * @return The database connection
     * @throws SQLException if a connection error occurs
     */
    public static Connection getConnection() throws SQLException {
        // Establish a connection to the database using the provided URL, username, and password
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Initializes the database by creating the STUDENT and COURSE tables if they do not exist.
     */
    public static void initializeDatabase() {
        try (Connection conn = getConnection(); Statement stmt = conn.createStatement()) {
            // Create STUDENT table
            String createStudentTable = """
                CREATE TABLE IF NOT EXISTS STUDENT (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    name VARCHAR(100),
                    score FLOAT,
                    grade CHAR(1),
                    active BOOLEAN
                )
            """;
            stmt.executeUpdate(createStudentTable);

            // Create COURSE table
            String createCourseTable = """
                CREATE TABLE IF NOT EXISTS COURSE (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    title VARCHAR(100),
                    credits FLOAT,
                    section CHAR(1),
                    available BOOLEAN
                )
            """;
            stmt.executeUpdate(createCourseTable);

            System.out.println("Tables checked/created successfully.");
        } catch (SQLException e) {
            // Print stack trace for debugging purposes
            e.printStackTrace();
        }
    }
}