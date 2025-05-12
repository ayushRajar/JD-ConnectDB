package Collect;

import java.sql.ResultSet;
import java.util.ArrayList;

class Student {
    String usn;
    String name;
    int age;
    int sem;
    float cgpa;

        public Student(String usn, String name, int age, int sem, float cgpa) {
            this.usn = usn;
            this.name = name;
            this.age = age;
            this.sem = sem;
            this.cgpa = cgpa;
        }

        @Override
        public String toString() {
            return usn + " - " + name + " - Age: " + age + ", Sem: " + sem + ", CGPA: " + cgpa;
        }
    }


public class Arraylist {
    public static void addArrayList(ResultSet rs){
            ArrayList<Student> studentList= new ArrayList<>();
            try{
                while (rs.next()) {
                    String usn = rs.getString("USN");
                    String name = rs.getString("Name");
                    int age = rs.getInt("Age");
                    int sem = rs.getInt("Sem");
                    float cgpa = rs.getFloat("CGPA");

                    Student s = new Student(usn, name, age, sem, cgpa);
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
