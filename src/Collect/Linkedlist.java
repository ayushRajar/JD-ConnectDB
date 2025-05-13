/**
 * Demonstrates usage of LinkedList with Student records from the database.
 */
package Collect;

import java.sql.ResultSet;
import java.util.LinkedList;
import model.Student;

public class Linkedlist {
    /**
     * Loads Student records from a ResultSet into a LinkedList and prints them.
     * @param rs The ResultSet containing student data
     */
    public static void addLinkedList(ResultSet rs){
        LinkedList<Student> studentLinkedList = new LinkedList<>();
        try{
            // Iterate through the ResultSet to fetch student data
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

                // Create a Student object and add it to the LinkedList
                Student s = new Student(id, name, score, grade, active);
                studentLinkedList.add(s);
            }
            // Display the student list
            System.out.println("The Linked List Collection for the Data are as follows : ");
            System.out.println(studentLinkedList.getClass().getName());
            for (Student s : studentLinkedList) {
                System.out.println(s);
            }
        } catch (Exception e) {
            // Print stack trace in case of an exception
            e.printStackTrace();
        }
    }
}
