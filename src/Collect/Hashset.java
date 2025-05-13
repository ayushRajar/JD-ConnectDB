package Collect;

import java.sql.ResultSet;
import java.util.HashSet;
import model.Student;

public class Hashset {

    public static void addHashSet(ResultSet rs){
        HashSet<Student> studentSet = new HashSet<>();
        try{
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

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
            e.printStackTrace();
        }

    }
    
}
