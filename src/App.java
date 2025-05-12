import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import Collect.*;
    class Student {
    String usn;
    String name;
    int age;
    int sem;
    float cgpa;

        public Student(String usn, String name, int age, int sem, float cgpa) {
            this.usn = usn;
            this.name = name;
            this.age = age;
            this.sem = sem;
            this.cgpa = cgpa;
        }

        @Override
        public String toString() {
            return usn + " - " + name + " - Age: " + age + ", Sem: " + sem + ", CGPA: " + cgpa;
        }
    }


	public class App {
	    public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3306/DEPARTMENT";
	        String user = "root";
	        String password = "@helloSql";
			Scanner sc = new Scanner(System.in);

	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            System.err.println("MySQL JDBC Driver not found.");
	            e.printStackTrace();
	            
	        }
            int ch;

	        try (Connection connection = DriverManager.getConnection(url, user, password);
	             Statement statement = connection.createStatement();
	            //  ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENT")
                ) 
                {
                

	            do{
                    ResultSet resultSet = statement.executeQuery("SELECT * FROM STUDENT");
                    System.out.println("Connected to the database successfully!");
                    System.out.println("1. ArrayList Collection");
                    System.out.println("2. LinkedList Collection");
                    System.out.println("3. Stack Collection");
                    System.out.println("4. Queue Collection");
                    System.out.println("5. HashSet Class");
                    System.out.println("6. LinkedHashSet Collection");
                    System.out.println("7. TreeSet Collection");
                    
                    System.out.println("Enter the choice : ");
                    ch=sc.nextInt();

                    switch (ch) {
                        case 1:Arraylist.addArrayList(resultSet);
                            break;
                        case 2:Linkedlist.addLinkedList(resultSet);
                            break;
                        case 3: StackCollec.addStackCollec(resultSet);
                            break;
                        case 4: QueueCollec.addQueue(resultSet);
                            break;
                        case 5: Hashset.addHashSet(resultSet);
                            break;
                        case 6: Linkedhashset.addLinkedHashSet(resultSet);
                            break;
                        case 7: Treeset.addTreeSet(resultSet);
                            break;
                    
                        default:
                            break;
                    }
                }while(ch != 0);

                    
            } catch (SQLException e) {
                System.err.println("Database connection or query failed.");
                e.printStackTrace();
            }
        
		sc.close();
	}
}
