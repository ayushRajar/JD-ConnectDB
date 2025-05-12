package Collect;

import java.sql.ResultSet;
import java.util.LinkedList;

public class Linkedlist {
    public static void addLinkedList(ResultSet rs){
        LinkedList<Student> studentLinkedList = new LinkedList<>();
        try{
                while (rs.next()) {
                    String usn = rs.getString("USN");
                    String name = rs.getString("Name");
                    int age = rs.getInt("Age");
                    int sem = rs.getInt("Sem");
                    float cgpa = rs.getFloat("CGPA");

                    Student s = new Student(usn, name, age, sem, cgpa);
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
