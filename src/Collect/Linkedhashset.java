package Collect;

import java.sql.ResultSet;

import java.util.LinkedHashSet;

public class Linkedhashset {
    public static void addLinkedHashSet(ResultSet rs){
        LinkedHashSet<Student> studentSet = new LinkedHashSet<>();
        try{
                while (rs.next()) {
                    String usn = rs.getString("USN");
                    String name = rs.getString("Name");
                    int age = rs.getInt("Age");
                    int sem = rs.getInt("Sem");
                    float cgpa = rs.getFloat("CGPA");

                    Student s = new Student(usn, name, age, sem, cgpa);
                    studentSet.add(s);
            }

            // Display the student list
            System.out.println("The LinkedHashSet Collection for the Data are as follows : ");
            System.out.println(studentSet.getClass().getName());
            for (Student s : studentSet) {
                System.out.println(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
