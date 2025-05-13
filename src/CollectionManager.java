import java.sql.*;
import java.util.*;
import model.Student;
import model.Course;

/**
 * CollectionManager manages a Java collection of Student or Course objects,
 * synchronizing it with the database and supporting different collection types.
 * @param <T> The type of object managed (Student or Course)
 */
public class CollectionManager<T> {
    /** The underlying Java collection (ArrayList, Stack, etc.) */
    private Collection<T> collection;
    /** The name of the database table ("STUDENT" or "COURSE") */
    private String tableName;

    /**
     * Constructs a CollectionManager for the given collection type and table.
     * @param collectionType The type of collection (ArrayList, Stack, etc.)
     * @param tableName The database table name
     */
    public CollectionManager(String collectionType, String tableName) {
        this.tableName = tableName;
        this.collection = createCollection(collectionType);
    }

    /**
     * Creates a new collection instance based on the type and table.
     * For TreeSet, uses a comparator to sort by score (Student) or credits (Course).
     * @param type The collection type
     * @return The new collection
     */
    private Collection<T> createCollection(String type) {
        if (type.equals("TreeSet")) {
            if (tableName.equals("STUDENT")) {
                // TreeSet for Student: sort by score ascending
                Comparator<T> comp = (a, b) -> {
                    Student s1 = (Student) a;
                    Student s2 = (Student) b;
                    return Float.compare(s1.score(), s2.score());
                };
                return new TreeSet<>(comp);
            } else {
                // TreeSet for Course: sort by credits ascending
                Comparator<T> comp = (a, b) -> {
                    Course c1 = (Course) a;
                    Course c2 = (Course) b;
                    return Float.compare(c1.credits(), c2.credits());
                };
                return new TreeSet<>(comp);
            }
        }
        return switch (type) {
            case "ArrayList" -> new ArrayList<>();
            case "LinkedList" -> new LinkedList<>();
            case "Stack" -> new Stack<>();
            case "Queue" -> new LinkedList<>();
            case "HashSet" -> new HashSet<>();
            default -> new ArrayList<>();
        };
    }

    /**
     * Switches the current collection to a new type, preserving the data.
     * @param newType The new collection type
     */
    public void switchCollection(String newType) {
        Collection<T> newCollection = createCollection(newType);
        newCollection.addAll(this.collection);
        this.collection = newCollection;
    }

    /**
     * Returns the current collection.
     * @return The collection
     */
    public Collection<T> getCollection() {
        return collection;
    }

    /**
     * Loads all records from the database into the collection, preserving the semantics
     * of the collection type (Stack, Queue, TreeSet, etc.).
     */
    public void clearAndLoadFromDB() {
        collection.clear();
        List<T> tempList = new ArrayList<>();
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
                    tempList.add(obj);
                } else {
                    @SuppressWarnings("unchecked")
                    T obj = (T) new Course(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getFloat("credits"),
                        rs.getString("section").charAt(0),
                        rs.getBoolean("available")
                    );
                    tempList.add(obj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Now add to collection according to its type
        if (collection instanceof Stack) {
            // Stack: LIFO, so add in reverse order
            List<T> reversed = new ArrayList<>(tempList);
            Collections.reverse(reversed);
            collection.addAll(reversed);
        } else if (collection instanceof Queue || collection instanceof LinkedList || collection instanceof ArrayList) {
            // Queue/LinkedList/ArrayList: preserve DB order
            collection.addAll(tempList);
        } else if (collection instanceof TreeSet) {
            // TreeSet: will be sorted by comparator
            collection.addAll(tempList);
        } else if (collection instanceof HashSet) {
            // HashSet: no order, but add all
            collection.addAll(tempList);
        } else {
            // Default: preserve DB order
            collection.addAll(tempList);
        }
    }

    /**
     * Adds a record to the collection and inserts it into the database.
     * @param obj The object to add
     */
    public void addToDBAndCollection(T obj) {
        collection.add(obj);
        try (Connection conn = DatabaseManager.getConnection()) {
            if (tableName.equals("STUDENT")) {
                Student s = (Student) obj;
                String sql = "INSERT INTO STUDENT (name, score, grade, active) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, s.name());
                    ps.setFloat(2, s.score());
                    ps.setString(3, String.valueOf(s.grade()));
                    ps.setBoolean(4, s.active());
                    ps.executeUpdate();
                }
            } else {
                Course c = (Course) obj;
                String sql = "INSERT INTO COURSE (title, credits, section, available) VALUES (?, ?, ?, ?)";
                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, c.title());
                    ps.setFloat(2, c.credits());
                    ps.setString(3, String.valueOf(c.section()));
                    ps.setBoolean(4, c.available());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a record from the collection and the database by its identifier (id).
     * @param identifier The id of the record to delete
     */
    public void deleteFromDBAndCollection(int identifier) {
        collection.removeIf(obj -> {
            if (tableName.equals("STUDENT")) {
                return ((Student) obj).id() == identifier;
            } else {
                return ((Course) obj).id() == identifier;
            }
        });
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 tableName.equals("STUDENT")
                     ? "DELETE FROM STUDENT WHERE id = ?"
                     : "DELETE FROM COURSE WHERE id = ?")) {
            ps.setInt(1, identifier);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}