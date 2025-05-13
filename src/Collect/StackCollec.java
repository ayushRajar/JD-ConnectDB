/**
 * Demonstrates usage of Stack with Student records from the database.
 */
package Collect;

import java.sql.ResultSet;
import java.util.Stack;
import model.Student;

public class StackCollec {
    /**
     * Loads Student records from a ResultSet into a Stack and prints them in LIFO order.
     * @param rs The ResultSet containing student data
     */
    public static void addStackCollec(ResultSet rs){
        // Create a stack to hold Student objects
        Stack<Student> stackStudent= new Stack<>();
        try{
            // Iterate through the ResultSet and populate the stack
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

                // Create a Student object and push it onto the stack
                Student s = new Student(id, name, score, grade, active);
                stackStudent.push(s);
            }

            // Display the student list
            System.out.println("The Stack class of the student is as follows : ");
            System.out.println(stackStudent.getClass().getName());
            while(!stackStudent.isEmpty()) {
                // Pop and print each Student object from the stack
                System.out.println(stackStudent.pop());
            }

        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        } 

    }
}
