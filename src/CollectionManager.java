import java.sql.*;
import java.util.*;
import model.Student;
import model.Course;

public class CollectionManager<T> {
    private Collection<T> collection;
    private String tableName;

    public CollectionManager(String collectionType, String tableName) {
        this.tableName = tableName;
        this.collection = createCollection(collectionType);
    }

    private Collection<T> createCollection(String type) {
        return switch (type) {
            case "ArrayList" -> new ArrayList<>();
            case "LinkedList" -> new LinkedList<>();
            case "Stack" -> new Stack<>();
            case "Queue" -> new LinkedList<>();
            case "HashSet" -> new HashSet<>();
            case "TreeSet" -> new TreeSet<>(Comparator.comparing(Object::toString));
            default -> new ArrayList<>();
        };
    }

    public void switchCollection(String newType) {
        Collection<T> newCollection = createCollection(newType);
        newCollection.addAll(this.collection);
        this.collection = newCollection;
    }

    public Collection<T> getCollection() {
        // Stack: LIFO (reverse order)
        if (collection instanceof Stack) {
            List<T> list = new ArrayList<>(collection);
            Collections.reverse(list);
            return list;
        }
        // LinkedList: insertion order
        if (collection instanceof LinkedList) {
            return new ArrayList<>(collection);
        }
        // ArrayList: insertion order
        if (collection instanceof ArrayList) {
            return new ArrayList<>(collection);
        }
        // Queue: insertion order (using LinkedList)
        if (collection instanceof Queue) {
            return new ArrayList<>(collection);
        }
        // TreeSet: sorted order
        if (collection instanceof TreeSet) {
            return new ArrayList<>(collection);
        }
        // HashSet: arbitrary order
        if (collection instanceof HashSet) {
            return new ArrayList<>(collection);
        }
        // Default: as is
        return new ArrayList<>(collection);
    }

    public void clearAndLoadFromDB() {
        collection.clear();
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
            while (rs.next()) {
                if (tableName.equals("STUDENT")) {
                    @SuppressWarnings("unchecked")
                    T obj = (T) new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getFloat("score"),
                        rs.getString("grade").charAt(0),
                        rs.getBoolean("active")
                    );
                    collection.add(obj);
                } else {
                    @SuppressWarnings("unchecked")
                    T obj = (T) new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getFloat("credits"),
                        rs.getString("section").charAt(0),
                        rs.getBoolean("available")
                    );
                    collection.add(obj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addToDBAndCollection(T obj) {
        collection.add(obj);
        try (Connection conn = DatabaseManager.getConnection()) {
            if (tableName.equals("STUDENT")) {
                Student s = (Student) obj;
                String sql = "INSERT INTO STUDENT (usn, name, score, grade, active) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, s.usn());
                    ps.setString(2, s.name());
                    ps.setFloat(3, s.score());
                    ps.setString(4, String.valueOf(s.grade()));
                    ps.setBoolean(5, s.active());
                    ps.executeUpdate();
                }
            } else {
                Course c = (Course) obj;
                String sql = "INSERT INTO COURSE (courseCode, title, credits, section, available) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setInt(1, c.courseCode());
                    ps.setString(2, c.title());
                    ps.setFloat(3, c.credits());
                    ps.setString(4, String.valueOf(c.section()));
                    ps.setBoolean(5, c.available());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromDBAndCollection(int identifier) {
        collection.removeIf(obj -> {
            if (tableName.equals("STUDENT")) {
                return ((Student) obj).usn() == identifier;
            } else {
                return ((Course) obj).courseCode() == identifier;
            }
        });
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 tableName.equals("STUDENT")
                     ? "DELETE FROM STUDENT WHERE usn = ?"
                     : "DELETE FROM COURSE WHERE courseCode = ?")) {
            ps.setInt(1, identifier);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}