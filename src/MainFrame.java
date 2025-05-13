import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import model.Student;
import model.Course;

import java.awt.*;
import java.util.Collection;

public class MainFrame extends JFrame {
    private JComboBox<String> tableSelector;
    private JComboBox<String> collectionSelector;
    private JTextField[] dataFields;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    private static final String[] TABLES = {"STUDENT", "COURSE"};
    private static final String[] COLLECTIONS = {"ArrayList", "LinkedList", "Stack", "Queue", "HashSet", "TreeSet"};

    private CollectionManager<? extends Object> collectionManager;

    public MainFrame() {
        setTitle("Database Collection Manager");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
        setLocationRelativeTo(null);

        // Top panel for selections
        JPanel topPanel = new JPanel();
        tableSelector = new JComboBox<>(TABLES);
        collectionSelector = new JComboBox<>(COLLECTIONS);
        topPanel.add(new JLabel("Table:"));
        topPanel.add(tableSelector);
        topPanel.add(new JLabel("Collection:"));
        topPanel.add(collectionSelector);

        // Data entry panel
        JPanel entryPanel = new JPanel();
        entryPanel.setLayout(new GridBagLayout());
        entryPanel.setBackground(new Color(240, 248, 255)); // Light blue background
        entryPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 2, true),
            "Enter Record Details",
            0, 0, new Font("Arial", Font.BOLD, 16), new Color(70, 130, 180)));
        updateDataFields("STUDENT", entryPanel);

        // Listen for table selection changes
        tableSelector.addActionListener(e -> {
            entryPanel.removeAll();
            updateDataFields((String) tableSelector.getSelectedItem(), entryPanel);
            entryPanel.revalidate();
            entryPanel.repaint();
            setupCollectionManager();
        });

        // Listen for collection selection changes
        collectionSelector.addActionListener(e -> {
            if (collectionManager != null) {
                collectionManager.switchCollection((String) collectionSelector.getSelectedItem());
                displayData();
            }
        });

        // Table for displaying data
        tableModel = new DefaultTableModel();
        dataTable = new JTable(tableModel);
        dataTable.setRowHeight(28);
        dataTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dataTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
        dataTable.setGridColor(new Color(70, 130, 180));
        dataTable.setSelectionBackground(new Color(255, 228, 181));
        dataTable.setSelectionForeground(new Color(25, 25, 112));
        JScrollPane tableScroll = new JScrollPane(dataTable);
        tableScroll.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));

        // Layout
        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(entryPanel, BorderLayout.CENTER);
        add(tableScroll, BorderLayout.EAST);

        // --- Add control buttons ---
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(224, 255, 255));
        buttonPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(70, 130, 180), 1),
            BorderFactory.createEmptyBorder(8, 8, 8, 8)));
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton displayButton = new JButton("Display");
        addButton.setBackground(new Color(60, 179, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createLineBorder(new Color(34, 139, 34), 2));
        deleteButton.setBackground(new Color(220, 20, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        deleteButton.setFocusPainted(false);
        deleteButton.setBorder(BorderFactory.createLineBorder(new Color(178, 34, 34), 2));
        displayButton.setBackground(new Color(70, 130, 180));
        displayButton.setForeground(Color.WHITE);
        displayButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        displayButton.setFocusPainted(false);
        displayButton.setBorder(BorderFactory.createLineBorder(new Color(25, 25, 112), 2));
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(displayButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addRecord());
        deleteButton.addActionListener(e -> deleteRecord());
        displayButton.addActionListener(e -> displayData());

        // Initialize collection manager
        setupCollectionManager();
    }

    private void setupCollectionManager() {
        String table = (String) tableSelector.getSelectedItem();
        String collection = (String) collectionSelector.getSelectedItem();
        if ("STUDENT".equals(table)) {
            collectionManager = new CollectionManager<Student>(collection, table);
        } else {
            collectionManager = new CollectionManager<Course>(collection, table);
        }
        collectionManager.clearAndLoadFromDB();
        displayData();
    }

    private void updateDataFields(String table, JPanel panel) {
        panel.removeAll();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color labelColor = new Color(25, 25, 112);
        Color fieldBg = new Color(255, 255, 255);
        Color fieldFg = new Color(0, 51, 102);
        if (table.equals("STUDENT")) {
            dataFields = new JTextField[4];
            String[] labels = {"Name (String)", "Score (float)", "Grade (char)", "Active (boolean)"};
            for (int i = 0; i < 4; i++) {
                gbc.gridx = 0;
                gbc.gridy = i;
                JLabel lbl = new JLabel(labels[i]);
                lbl.setFont(labelFont);
                lbl.setForeground(labelColor);
                panel.add(lbl, gbc);
                gbc.gridx = 1;
                dataFields[i] = new JTextField(12);
                dataFields[i].setFont(fieldFont);
                dataFields[i].setBackground(fieldBg);
                dataFields[i].setForeground(fieldFg);
                dataFields[i].setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));
                panel.add(dataFields[i], gbc);
            }
        } else {
            dataFields = new JTextField[4];
            String[] labels = {"Title (String)", "Credits (float)", "Section (char)", "Available (boolean)"};
            for (int i = 0; i < 4; i++) {
                gbc.gridx = 0;
                gbc.gridy = i;
                JLabel lbl = new JLabel(labels[i]);
                lbl.setFont(labelFont);
                lbl.setForeground(labelColor);
                panel.add(lbl, gbc);
                gbc.gridx = 1;
                dataFields[i] = new JTextField(12);
                dataFields[i].setFont(fieldFont);
                dataFields[i].setBackground(fieldBg);
                dataFields[i].setForeground(fieldFg);
                dataFields[i].setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 1));
                panel.add(dataFields[i], gbc);
            }
        }
        panel.revalidate();
        panel.repaint();
    }

    @SuppressWarnings("unchecked")
    private void addRecord() {
        String table = (String) tableSelector.getSelectedItem();
        try {
            if ("STUDENT".equals(table)) {
                String name = dataFields[0].getText();
                float score = Float.parseFloat(dataFields[1].getText());
                char grade = dataFields[2].getText().charAt(0);
                boolean active = Boolean.parseBoolean(dataFields[3].getText());
                Student s = new Student(0, name, score, grade, active); // id is auto
                ((CollectionManager<Student>) collectionManager).addToDBAndCollection(s);
            } else {
                String title = dataFields[0].getText();
                float credits = Float.parseFloat(dataFields[1].getText());
                char section = dataFields[2].getText().charAt(0);
                boolean available = Boolean.parseBoolean(dataFields[3].getText());
                Course c = new Course(0, title, credits, section, available); // id is auto
                ((CollectionManager<Course>) collectionManager).addToDBAndCollection(c);
            }
            collectionManager.clearAndLoadFromDB();
            displayData();
            // Clear all data fields after adding
            for (int i = 0; i < dataFields.length; i++) {
                dataFields[i].setText("");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input: " + ex.getMessage());
        }
    }

    private void deleteRecord() {
        int selectedRow = dataTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to delete.");
            return;
        }
        int key = Integer.parseInt(tableModel.getValueAt(selectedRow, 0).toString());
        String table = (String) tableSelector.getSelectedItem();
        if ("STUDENT".equals(table)) {
            @SuppressWarnings("unchecked")
            CollectionManager<Student> studentManager = (CollectionManager<Student>) collectionManager;
            studentManager.deleteFromDBAndCollection(key);
        } else {
            @SuppressWarnings("unchecked")
            CollectionManager<Course> courseManager = (CollectionManager<Course>) collectionManager;
            courseManager.deleteFromDBAndCollection(key);
        }
        collectionManager.clearAndLoadFromDB();
        displayData();
    }

    private void displayData() {
        String table = (String) tableSelector.getSelectedItem();
        collectionManager.clearAndLoadFromDB();
        Collection<?> data = collectionManager.getCollection();

        if ("STUDENT".equals(table)) {
            tableModel.setColumnIdentifiers(new String[]{"ID", "Name", "Score", "Grade", "Active"});
            tableModel.setRowCount(0);
            for (Object obj : data) {
                Student s = (Student) obj;
                tableModel.addRow(new Object[]{s.id(), s.name(), s.score(), s.grade(), s.active()});
            }
        } else {
            tableModel.setColumnIdentifiers(new String[]{"ID", "Title", "Credits", "Section", "Available"});
            tableModel.setRowCount(0);
            for (Object obj : data) {
                Course c = (Course) obj;
                tableModel.addRow(new Object[]{c.id(), c.title(), c.credits(), c.section(), c.available()});
            }
        }
    }

    public static void main(String[] args) {
        // Initialize DB
        DatabaseManager.initializeDatabase();

        // Show GUI
        SwingUtilities.invokeLater(() -> new MainFrame().setVisible(true));
    }
}