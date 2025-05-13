package Collect;

import java.sql.ResultSet;
import java.util.LinkedList;
import model.Student;

public class Linkedlist {
    public static void addLinkedList(ResultSet rs){
        LinkedList<Student> studentLinkedList = new LinkedList<>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

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
            e.printStackTrace();
        }
    }
}
