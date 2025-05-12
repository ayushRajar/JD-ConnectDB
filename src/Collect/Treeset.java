package Collect;

import java.sql.ResultSet;
import java.util.Comparator;
import java.util.TreeSet;
class studentComp implements Comparator{
    public int compare(Object o1,Object o2){
        Student s1 = (Student)o1;
        Student s2 = (Student)o2;

        if(s1.cgpa > s2.cgpa){
            return 1;
        }else{
            return -1;
        }
    }
}

public class Treeset {
    public static void addTreeSet(ResultSet rs){
        TreeSet<Student> studentSet = new TreeSet<>(new studentComp());
        
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
