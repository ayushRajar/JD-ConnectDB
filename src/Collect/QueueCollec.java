package Collect;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Queue;
import model.Student;

public class QueueCollec {
    public static void addQueue(ResultSet rs){
        Queue<Student> queueStudent = new LinkedList<>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

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
