package Collect;

import java.sql.ResultSet;
import java.util.ArrayList;
import model.Student;

public class Arraylist {
    public static void addArrayList(ResultSet rs){
        ArrayList<Student> studentList= new ArrayList<>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

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
            e.printStackTrace();
        } 
    }
}
