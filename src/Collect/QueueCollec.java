/**
 * Demonstrates usage of Queue with Student records from the database.
 */
package Collect;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Queue;
import model.Student;

public class QueueCollec {
    /**
     * Loads Student records from a ResultSet into a Queue and prints them in FIFO order.
     * @param rs The ResultSet containing student data
     */
    public static void addQueue(ResultSet rs){
        // Create a Queue to hold Student objects
        Queue<Student> queueStudent = new LinkedList<>();
        try{
            // Iterate through the ResultSet and populate the Queue
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

                // Create a Student object and add it to the Queue
                Student s = new Student(id, name, score, grade, active);
                queueStudent.add(s);
            }
            // Display the student list
            System.out.println("The Queue Collection for the Data are as follows : ");
            System.out.println(queueStudent.getClass().getName());
            for (Student s : queueStudent) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
