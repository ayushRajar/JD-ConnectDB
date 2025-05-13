/**
 * Demonstrates usage of TreeSet with Student records from the database, sorted by score.
 */
package Collect;

import java.sql.ResultSet;
import java.util.Comparator;
import java.util.TreeSet;
import model.Student;

/**
 * Comparator for sorting students by score in ascending order.
 */
class studentComp implements Comparator<Student> {
    public int compare(Student s1, Student s2){
        return Float.compare(s1.score(), s2.score());
    }
}

public class Treeset {
    /**
     * Loads Student records from a ResultSet into a TreeSet (sorted by score) and prints them.
     * @param rs The ResultSet containing student data
     */
    public static void addTreeSet(ResultSet rs){
        // Create a TreeSet with a custom comparator to sort students by score
        TreeSet<Student> studentSet = new TreeSet<>(new studentComp());
        try{
            // Iterate through the ResultSet and populate the TreeSet
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float score = rs.getFloat("score");
                char grade = rs.getString("grade").charAt(0);
                boolean active = rs.getBoolean("active");

                // Create a Student object and add it to the TreeSet
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
