package Collect;

import java.sql.ResultSet;
import java.util.Comparator;
import java.util.TreeSet;
import model.Student;

class studentComp implements Comparator<Student> {
    public int compare(Student s1, Student s2){
        return Float.compare(s1.score(), s2.score());
    }
}

public class Treeset {
    public static void addTreeSet(ResultSet rs){
        TreeSet<Student> studentSet = new TreeSet<>(new studentComp());
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
            System.out.println("The TreeSet Collection for the Data are as follows : ");
            System.out.println(studentSet.getClass().getName());
            for (Student s : studentSet) {
                System.out.println(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
