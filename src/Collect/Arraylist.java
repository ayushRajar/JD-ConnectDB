/**
 * Demonstrates usage of ArrayList with Student records from the database.
 */
package Collect;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.Student;

public class Arraylist {
    /**
     * Loads Student records from a ResultSet into an ArrayList and prints them.
     * @param rs The ResultSet containing student data
     */
    public static void addArrayList(ResultSet rs){
        // Create an ArrayList to store Student objects
        ArrayList<Student> studentList= new ArrayList<>();
        try{
            // Iterate through the ResultSet and populate the ArrayList
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

                // Create a Student object and add it to the list
                Student s = new Student(id, name, score, grade, active);
                studentList.add(s);
            }

            // Display the student list
            System.out.println("The ArrayList class of the student is as follows : ");
            System.out.println(studentList.getClass().getName());
            for (Student s : studentList) {
                System.out.println(s);
            }

        } catch (Exception e) {
            // Print stack trace in case of an exception
            e.printStackTrace();
        } 
    }
}
