import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/dept";
    private static final String USER = "root"; // Change as needed
    private static final String PASSWORD = "lemontree@2025"; // Change as needed

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

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
            e.printStackTrace();
        }
    }
}