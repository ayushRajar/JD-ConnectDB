package Collect;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Queue;

public class QueueCollec {
    

    public static void addQueue(ResultSet rs){
        Queue<Student> queueStudent = new LinkedList<>();
        try{
            while (rs.next()) {
                    String usn = rs.getString("USN");
                    String name = rs.getString("Name");
                    int age = rs.getInt("Age");
                    int sem = rs.getInt("Sem");
                    float cgpa = rs.getFloat("CGPA");

                    Student s = new Student(usn, name, age, sem, cgpa);
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
