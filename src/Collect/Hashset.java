/**
 * Demonstrates usage of HashSet with Student records from the database.
 */
package Collect;

import java.sql.ResultSet;
import java.util.HashSet;
import model.Student;

public class Hashset {

    /**
     * Loads Student records from a ResultSet into a HashSet and prints them (no order guaranteed).
     * @param rs The ResultSet containing student data
     */
    public static void addHashSet(ResultSet rs){
        // Create a HashSet to store Student objects
        HashSet<Student> studentSet = new HashSet<>();
        try{
            // Iterate through the ResultSet and populate the HashSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

                // Create a Student object and add it to the HashSet
                Student s = new Student(id, name, score, grade, active);
                studentSet.add(s);
            }

            // Display the student list
            System.out.println("The Hash Set Collection for the Data are as follows : ");
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
