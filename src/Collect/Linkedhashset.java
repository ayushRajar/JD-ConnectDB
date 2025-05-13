/**
 * Demonstrates usage of LinkedHashSet with Student records from the database.
 */
package Collect;

import java.sql.ResultSet;
import java.util.LinkedHashSet;
import model.Student;

public class Linkedhashset {
    /**
     * Loads Student records from a ResultSet into a LinkedHashSet and prints them (insertion order).
     * @param rs The ResultSet containing student data
     */
    public static void addLinkedHashSet(ResultSet rs){
        // Create a LinkedHashSet to store Student objects
        LinkedHashSet<Student> studentSet = new LinkedHashSet<>();
        try{
            // Iterate through the ResultSet and populate the LinkedHashSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

                // Create a Student object and add it to the LinkedHashSet
                Student s = new Student(id, name, score, grade, active);
                studentSet.add(s);
            }
            // Display the student list
            System.out.println("The LinkedHashSet Collection for the Data are as follows : ");
            System.out.println(studentSet.getClass().getName());
            for (Student s : studentSet) {
                System.out.println(s);
            }
        } catch (Exception e) {
            // Print stack trace in case of an exception
            e.printStackTrace();
        }
    }
}
