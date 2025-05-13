package Collect;

import java.sql.ResultSet;
import java.util.Stack;
import model.Student;

public class StackCollec {
    public static void addStackCollec(ResultSet rs){
        Stack<Student> stackStudent= new Stack<>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

                Student s = new Student(id, name, score, grade, active);
                stackStudent.push(s);
            }

            // Display the student list
            System.out.println("The Stack class of the student is as follows : ");
            System.out.println(stackStudent.getClass().getName());
            while(!stackStudent.isEmpty()) {
                System.out.println(stackStudent.pop());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } 

    }
}
