package Collect;

import java.sql.ResultSet;
import java.util.Stack;

public class StackCollec {
    public static void addStackCollec(ResultSet rs){
        Stack<Student> stackStudent= new Stack<>();
        try{
                while (rs.next()) {
                    String usn = rs.getString("USN");
                    String name = rs.getString("Name");
                    int age = rs.getInt("Age");
                    int sem = rs.getInt("Sem");
                    float cgpa = rs.getFloat("CGPA");

                    Student s = new Student(usn, name, age, sem, cgpa);
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
