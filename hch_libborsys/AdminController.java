/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hch_libborsys;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javafx.application.Platform;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AdminController implements Initializable {

    @FXML
    private Button closebtn1;
    @FXML
    private Button closebtn2;
    @FXML
    private Button closebtn;
    @FXML
    private Button logout;
    @FXML
    private Button Student_Btn;
    @FXML
    private Button Book_Btn;
    @FXML
    private Button RetBook_Btn;
    @FXML
    private Button Pending_Btn;
    @FXML
    private AnchorPane Pending_Anc;
    @FXML
    private Button Stud_Pending_Btn;
    @FXML
    private Button Teach_Pending_Btn;
    @FXML
    private AnchorPane Stud_Pending_Anc;
    private ObservableList<PendBookStudTableView> Pendbooklist = FXCollections.observableArrayList();
    @FXML
    private TableView<PendBookStudTableView> Stud_Pending_Table;
    @FXML
    private TableColumn<PendBookStudTableView, String> Full_Name_PendStud;
    @FXML
    private TableColumn<PendBookStudTableView, String> Section_PendStud;
    @FXML
    private TableColumn<PendBookStudTableView, String> YearLevel_PendStud;
    @FXML
    private TableColumn<PendBookStudTableView, String> StudentID_PendStud;
    @FXML
    private TableColumn<PendBookStudTableView, String> AccNo_PendStud;
    @FXML
    private TableColumn<PendBookStudTableView, String> Title_PendStud;
    @FXML
    private TableColumn<PendBookStudTableView, String> Author_PendStud;
    @FXML
    private TableColumn<PendBookStudTableView, String> Category_PendStud;
    @FXML
    private TableColumn<PendBookStudTableView, String> Date_PendStud;
    @FXML
    private TableColumn<PendBookStudTableView, String> DueDate_PendStud;
    @FXML
    private TextField Search_Pending_Stud_txtf;
    @FXML
    private AnchorPane Teach_Pending_Anc;
    private ObservableList<PendBookTeachTableView> PendbooklistT = FXCollections.observableArrayList();
    @FXML
    private TableView<PendBookTeachTableView> Teach_Pending_Table;
    @FXML
    private TableColumn<PendBookTeachTableView, String> Full_Name_PendTeach;
    @FXML
    private TableColumn<PendBookTeachTableView, String> Designation_PendTeach;
    @FXML
    private TableColumn<PendBookTeachTableView, String> AccNo_PendTeach;
    @FXML
    private TableColumn<PendBookTeachTableView, String> TItle_PendTeach;
    @FXML
    private TableColumn<PendBookTeachTableView, String> Author_PendTeach;
    @FXML
    private TableColumn<PendBookTeachTableView, String> Category_PendTeach;
    @FXML
    private TableColumn<PendBookTeachTableView, String> Date_PendTeach;
    @FXML
    private TextField Search_Pending_Teach_txtf;
    @FXML
    private AnchorPane RetBook_Anc;
    @FXML
    private Button Stud_Ret_Btn;
    @FXML
    private Button Teach_Ret_Btn;
    @FXML
    private AnchorPane Stud_Ret_Anc;
    private ObservableList<RetBookTableView> Retbooklist = FXCollections.observableArrayList();
    @FXML
    private TableView<RetBookTableView> Stud_Ret_Table;
    @FXML
    private TableColumn<RetBookTableView, String> Full_Name_RetStud;
    @FXML
    private TableColumn<RetBookTableView, String> Section_RetStud;
    @FXML
    private TableColumn<RetBookTableView, String> Year_Level_RetStud;
    @FXML
    private TableColumn<RetBookTableView, String> StudentID_RetStud;
    @FXML
    private TableColumn<RetBookTableView, String> Acc_No_RetStud;
    @FXML
    private TableColumn<RetBookTableView, String> Title_RetStud;
    @FXML
    private TableColumn<RetBookTableView, String> Author_RetStud;
    @FXML
    private TableColumn<RetBookTableView, String> Category_RetStud;
    @FXML
    private TableColumn<RetBookTableView, String> Date_RetStud;
    @FXML
    private TableColumn<RetBookTableView, String> Due_Date_RetStud;
    @FXML
    private TableColumn<RetBookTableView, String> Days_Elapsed_RetStud;
    @FXML
    private TextField Search_Ret_Stud_txtf;
    @FXML
    private AnchorPane Teach_Ret_Anc;

    private ObservableList<RetBookTeachTableView> RetbookTeachlist = FXCollections.observableArrayList();
    @FXML
    private TableView<RetBookTeachTableView> Teach_Ret_Table;
    @FXML
    private TableColumn<RetBookTeachTableView, String> Full_Name_RetTeach;
    @FXML
    private TableColumn<RetBookTeachTableView, String> Designation_RetTeach;
    @FXML
    private TableColumn<RetBookTeachTableView, String> Acc_No_RetTeach;
    @FXML
    private TableColumn<RetBookTeachTableView, String> Title_RetTeach;
    @FXML
    private TableColumn<RetBookTeachTableView, String> Author_RetTeach;
    @FXML
    private TableColumn<RetBookTeachTableView, String> Category_RetTeach;
    @FXML
    private TableColumn<RetBookTeachTableView, String> Date_RetTeach;
    @FXML
    private TextField Search_Ret_Teach_txtf;
    @FXML
    private AnchorPane Books_Anc;
    @FXML
    private Button AddBook_Btn;
    @FXML
    private TextField Search_Book_txtf;
    @FXML
    private Button Book_refresh_btn;
    @FXML
    private AnchorPane Students_Anc;
    @FXML
    private Button AddBook_Btn1;
    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    @FXML
    private TableView<Student> AddStud_Table;
    @FXML
    private TextField Search_Stud_txtf;
    @FXML
    private Button Student_refresh_btn;

    private Stage studentStage = null;
    private Stage teacherStage = null;

    private double x = 0;
    private double y = 0;
    @FXML
    private AnchorPane MainForm;
    @FXML
    private TableColumn<Student, String> Full_Name;
    @FXML
    private TableColumn<Student, String> Section;
    @FXML
    private TableColumn<Student, String> Year_Lvl;
    @FXML
    private TableColumn<Student, String> Student_ID;
    private ObservableList<ViewTable> bookList = FXCollections.observableArrayList();
    @FXML
    private TableView<ViewTable> Books_Table;
    @FXML
    private TableColumn<ViewTable, String> Title;
    @FXML
    private TableColumn<ViewTable, String> Acc_No;
    @FXML
    private TableColumn<ViewTable, String> Author;
    @FXML
    private TableColumn<ViewTable, String> Category;
    @FXML
    private TableColumn<ViewTable, String> Status;
    @FXML
    private Button RetBook_btn;
    @FXML
    private Button BrwBook_Pending_Btn;
    @FXML
    private Button Student_refresh_btn1;
    @FXML
    private ComboBox<String> academicYearComboBox;
    @FXML
    private Button Student_refresh_btn2;
    @FXML
    private Button Student_refresh_btn21;

    private FilteredList<RetBookTableView> filteredRetbooklist;
    private FilteredList<RetBookTeachTableView> filteredRetbookTeachlist;
    private FilteredList<PendBookStudTableView> filteredPendbooklist;
    private FilteredList<PendBookTeachTableView> filteredPendbooklistT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ObservableList<RetBookTableView> Retbooklist = FXCollections.observableArrayList();
        ObservableList<RetBookTeachTableView> RetbookTeachlist = FXCollections.observableArrayList();
        ObservableList<PendBookStudTableView> Pendbooklist = FXCollections.observableArrayList();
        ObservableList<PendBookTeachTableView> PendbooklistT = FXCollections.observableArrayList();

        // --- START: Academic Year ComboBox Initialization ---
        ObservableList<String> academicYears = FXCollections.observableArrayList();

        // Fixed academic year range: AY 2025-2026 to AY 2029-2030 (5 academic years)
        int startYearFixed = 2025;
        int endYearFixed = startYearFixed + 4;

        for (int year = startYearFixed; year <= endYearFixed; year++) {
            academicYears.add("AY " + year + "-" + (year + 1));
        }

        academicYearComboBox.setItems(academicYears);
        academicYearComboBox.setPromptText("Select a school year");

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        Month currentMonth = currentDate.getMonth();

        String defaultAcademicYear = null;

        if (currentMonth.getValue() >= Month.MAY.getValue()) {
            defaultAcademicYear = "AY " + currentYear + "-" + (currentYear + 1);
        } else {
            defaultAcademicYear = "AY " + (currentYear - 1) + "-" + currentYear;
        }

        if (academicYears.contains(defaultAcademicYear)) {
            academicYearComboBox.getSelectionModel().select(defaultAcademicYear);
            SessionManager.getInstance().setCurrentAcademicYear(defaultAcademicYear);
            System.out.println("Default Academic Year set to: " + defaultAcademicYear);
        } else {
            academicYearComboBox.getSelectionModel().clearSelection();
            SessionManager.getInstance().setCurrentAcademicYear(null);
            System.out.println("Calculated default academic year not found in list. No default selected.");
        }
        // --- END of default selection logic ---

        Full_Name.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));
        Section.setCellValueFactory(new PropertyValueFactory<>("Section"));
        Student_ID.setCellValueFactory(new PropertyValueFactory<>("Student_ID"));
        Year_Lvl.setCellValueFactory(new PropertyValueFactory<>("Year_Lvl"));
        loadStudentData();

        Search_Stud_txtf.textProperty().addListener((observable, oldValue, newValue) -> {
            searchStudentData(newValue);
        });

        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Acc_No.setCellValueFactory(new PropertyValueFactory<>("Acc_No"));
        Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        loadBookData();

        Search_Book_txtf.textProperty().addListener((observable, oldValue, newValue) -> {
            searchBookData(newValue);
        });

        Full_Name_RetStud.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));
        Section_RetStud.setCellValueFactory(new PropertyValueFactory<>("Section"));
        Year_Level_RetStud.setCellValueFactory(new PropertyValueFactory<>("Year_Level"));
        StudentID_RetStud.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        Acc_No_RetStud.setCellValueFactory(new PropertyValueFactory<>("Acc_No"));
        Title_RetStud.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Author_RetStud.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Category_RetStud.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Date_RetStud.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Due_Date_RetStud.setCellValueFactory(new PropertyValueFactory<>("Due_Date"));
        Days_Elapsed_RetStud.setCellValueFactory(new PropertyValueFactory<>("Days_Elapsed"));
        loadRetBookStudData();

        Search_Ret_Stud_txtf.textProperty().addListener((observable, oldValue, newValue) -> {
            searchStudentRetData(newValue);
        });

        Full_Name_RetTeach.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));
        Designation_RetTeach.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        Acc_No_RetTeach.setCellValueFactory(new PropertyValueFactory<>("Acc_No"));
        Title_RetTeach.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Author_RetTeach.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Category_RetTeach.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Date_RetTeach.setCellValueFactory(new PropertyValueFactory<>("Date"));
        loadRetBookTeachData();

        Search_Ret_Teach_txtf.textProperty().addListener((observable, oldValue, newValue) -> {
            searchTeachRetData(newValue);
        });

        Full_Name_PendStud.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));
        Section_PendStud.setCellValueFactory(new PropertyValueFactory<>("Section"));
        YearLevel_PendStud.setCellValueFactory(new PropertyValueFactory<>("Year_Level"));
        StudentID_PendStud.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        AccNo_PendStud.setCellValueFactory(new PropertyValueFactory<>("Acc_No"));
        Title_PendStud.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Author_PendStud.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Category_PendStud.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Date_PendStud.setCellValueFactory(new PropertyValueFactory<>("Date"));
        DueDate_PendStud.setCellValueFactory(new PropertyValueFactory<>("Due_Date"));
        loadPendBookStudData();

        Search_Pending_Stud_txtf.textProperty().addListener((observable, oldValue, newValue) -> {
            searchStudPendData(newValue);
        });

        Full_Name_PendTeach.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));
        Designation_PendTeach.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        AccNo_PendTeach.setCellValueFactory(new PropertyValueFactory<>("Acc_No"));
        TItle_PendTeach.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Author_PendTeach.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Category_PendTeach.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Date_PendTeach.setCellValueFactory(new PropertyValueFactory<>("Date"));
        loadPendBookTeachData();

        Search_Pending_Teach_txtf.textProperty().addListener((observable, oldValue, newValue) -> {
            searchTeachPendData(newValue);
        });

        academicYearComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.startsWith("AY ")) {
                String[] split = newValue.substring(3).split("-");
                int startYear = Integer.parseInt(split[0]);
                int endYear = Integer.parseInt(split[1]);

                LocalDate from = LocalDate.of(startYear, Month.MAY, 1);
                LocalDate to = LocalDate.of(endYear, Month.APRIL, 30);

                filterAllTablesByAcademicYear(from, to);
            }
        });

    }

    private void filterAllTablesByAcademicYear(LocalDate from, LocalDate to) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // --- Filter: Returned Books (Student) ---
        ObservableList<RetBookTableView> filteredRetStud = FXCollections.observableArrayList();
        for (RetBookTableView rec : Retbooklist) {
            try {
                LocalDate date = LocalDate.parse(rec.getDate(), formatter);
                if (!date.isBefore(from) && !date.isAfter(to)) {
                    filteredRetStud.add(rec);
                }
            } catch (Exception e) {
                System.out.println("Invalid date (student return): " + rec.getDate());
            }
        }
        Stud_Ret_Table.setItems(filteredRetStud);

        // --- Filter: Returned Books (Teacher) ---
        ObservableList<RetBookTeachTableView> filteredRetTeach = FXCollections.observableArrayList();
        for (RetBookTeachTableView rec : RetbookTeachlist) {
            try {
                LocalDate date = LocalDate.parse(rec.getDate(), formatter);
                if (!date.isBefore(from) && !date.isAfter(to)) {
                    filteredRetTeach.add(rec);
                }
            } catch (Exception e) {
                System.out.println("Invalid date (teacher return): " + rec.getDate());
            }
        }
        Teach_Ret_Table.setItems(filteredRetTeach);

        // --- Filter: Pending Books (Student) ---
        ObservableList<PendBookStudTableView> filteredPendStud = FXCollections.observableArrayList();
        for (PendBookStudTableView rec : Pendbooklist) {
            try {
                LocalDate date = LocalDate.parse(rec.getDate(), formatter);
                if (!date.isBefore(from) && !date.isAfter(to)) {
                    filteredPendStud.add(rec);
                }
            } catch (Exception e) {
                System.out.println("Invalid date (student pending): " + rec.getDate());
            }
        }
        Stud_Pending_Table.setItems(filteredPendStud);

        // --- Filter: Pending Books (Teacher) ---
        ObservableList<PendBookTeachTableView> filteredPendTeach = FXCollections.observableArrayList();
        for (PendBookTeachTableView rec : PendbooklistT) {
            try {
                LocalDate date = LocalDate.parse(rec.getDate(), formatter);
                if (!date.isBefore(from) && !date.isAfter(to)) {
                    filteredPendTeach.add(rec);
                }
            } catch (Exception e) {
                System.out.println("Invalid date (teacher pending): " + rec.getDate());
            }
        }
        Teach_Pending_Table.setItems(filteredPendTeach);
    }

    // New method to handle search functionality
    private void searchStudentData(String query) {
        DB_connection.init();
        Connection c = DB_connection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<Student> filteredList = FXCollections.observableArrayList(); //create new list

        try {
            // Use a parameterized query to prevent SQL injection and improve performance
            String sql = "SELECT idStudent, Full_Name, Section, Year_Lvl, Student_ID FROM libborsys.student "
                    + "WHERE Full_Name LIKE ? OR Section LIKE ? OR Year_Lvl LIKE ? OR Student_ID LIKE ?";
            ps = c.prepareStatement(sql);

            // Add wildcards (%) to the query to search for partial matches
            String searchQuery = "%" + query + "%";  //added wildcards
            ps.setString(1, searchQuery);
            ps.setString(2, searchQuery);
            ps.setString(3, searchQuery);
            ps.setString(4, searchQuery);

            rs = ps.executeQuery();

            while (rs.next()) {
                filteredList.add(new Student( //use filtered list
                        rs.getString("Full_Name"),
                        rs.getString("Section"),
                        rs.getString("Year_Lvl"),
                        rs.getString("Student_ID"),
                        rs.getInt("idStudent")
                ));
            }

            AddStud_Table.setItems(filteredList); // Set the table with filtered data
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error searching student data: " + ex.getMessage()); //show error
        }
    }

    private void searchBookData(String query) {
        DB_connection.init();
        Connection c = DB_connection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<ViewTable> filteredList = FXCollections.observableArrayList(); //create new list

        try {
            // Use a parameterized query to prevent SQL injection and improve performance
            String sql = "SELECT idInventory, Title, Acc_No, Author, Category, Status FROM libborsys.inventory "
                    + "WHERE Title LIKE ? OR Acc_No LIKE ? OR Author LIKE ? OR Category LIKE ? OR Status LIKE ?";
            ps = c.prepareStatement(sql);

            // Add wildcards (%) to the query to search for partial matches
            String searchQuery = "%" + query + "%";  //added wildcards
            ps.setString(1, searchQuery);
            ps.setString(2, searchQuery);
            ps.setString(3, searchQuery);
            ps.setString(4, searchQuery);
            ps.setString(5, searchQuery);

            rs = ps.executeQuery();

            while (rs.next()) {
                filteredList.add(new ViewTable(//use filtered list
                        rs.getString("Title"),
                        rs.getString("Acc_No"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Status"),
                        rs.getInt("idInventory")
                ));
            }

            Books_Table.setItems(filteredList); // Set the table with filtered data
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error searching student data: " + ex.getMessage()); //show error
        }
    }

    private void searchStudentRetData(String query) {
        DB_connection.init();
        Connection c = DB_connection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<RetBookTableView> filteredList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT idreturn_stud, Full_Name, Section, Year_Level, StudentID, Acc_No, Title, Author, Category, Date, Due_Date, Days_Elapsed\n"
                    + "FROM libborsys.return_stud\n"
                    + "WHERE Full_Name LIKE ?\n"
                    + "   OR Section LIKE ?\n"
                    + "   OR Year_Level LIKE ?\n"
                    + "   OR StudentID LIKE ?\n"
                    + "   OR Acc_No LIKE ?\n"
                    + "   OR Title LIKE ?\n"
                    + "   OR Author LIKE ?\n"
                    + "   OR Category LIKE ?\n"
                    + "   OR Date LIKE ?\n"
                    + "   OR Due_Date LIKE ?\n"
                    + "   OR Days_Elapsed LIKE ?";
            ps = c.prepareStatement(sql);

            String searchQuery = "%" + query + "%";
            for (int i = 1; i <= 11; i++) {
                ps.setString(i, searchQuery);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                filteredList.add(new RetBookTableView(
                        rs.getString("Full_Name"),
                        rs.getString("Section"),
                        rs.getString("Year_Level"),
                        rs.getString("StudentID"),
                        rs.getString("Acc_No"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Date"),
                        rs.getString("Due_Date"),
                        rs.getString("Days_Elapsed"),
                        rs.getInt("idreturn_stud")
                ));
            }

            Stud_Ret_Table.setItems(filteredList);
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error searching student data: " + ex.getMessage()); //show error
        }
    }

    private void searchTeachRetData(String query) {
        DB_connection.init();
        Connection c = DB_connection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<RetBookTeachTableView> filteredList = FXCollections.observableArrayList(); //create new list

        try {
            String sql = "SELECT idreturn_teach, Full_Name, Designation, Acc_No, Title, Author, Category, Date FROM libborsys.return_teach "
                    + "WHERE Full_Name LIKE ? OR Designation LIKE ? OR Acc_No LIKE ? OR Title LIKE ? OR Author LIKE ? OR Category LIKE ? OR Date LIKE ?";
            ps = c.prepareStatement(sql);

            String searchQuery = "%" + query + "%";  //added wildcards
            ps.setString(1, searchQuery);
            ps.setString(2, searchQuery);
            ps.setString(3, searchQuery);
            ps.setString(4, searchQuery);
            ps.setString(5, searchQuery);
            ps.setString(6, searchQuery);
            ps.setString(7, searchQuery);

            rs = ps.executeQuery();

            while (rs.next()) {
                filteredList.add(new RetBookTeachTableView(//use filtered list
                        rs.getString("Full_Name"),
                        rs.getString("Designation"),
                        rs.getString("Acc_No"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Date"),
                        rs.getInt("idreturn_teach")
                ));
            }

            Teach_Ret_Table.setItems(filteredList); // Set the table with filtered data
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error searching student data: " + ex.getMessage()); //show error
        }
    }

    private void searchStudPendData(String query) {
        DB_connection.init();
        Connection c = DB_connection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<PendBookStudTableView> filteredList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT idborrow_stud, Full_Name, Section, Year_Level, StudentID, Acc_No, Title, Author, Category, Date, Due_Date\n"
                    + "FROM libborsys.borrow_stud\n"
                    + "WHERE Full_Name LIKE ?\n"
                    + "   OR Section LIKE ?\n"
                    + "   OR Year_Level LIKE ?\n"
                    + "   OR StudentID LIKE ?\n"
                    + "   OR Acc_No LIKE ?\n"
                    + "   OR Title LIKE ?\n"
                    + "   OR Author LIKE ?\n"
                    + "   OR Category LIKE ?\n"
                    + "   OR Date LIKE ?\n"
                    + "   OR Due_Date LIKE ?\n";
            ps = c.prepareStatement(sql);
            String searchQuery = "%" + query + "%";
            for (int i = 1; i <= 10; i++) {
                ps.setString(i, searchQuery);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                filteredList.add(new PendBookStudTableView( //use filtered list
                        rs.getString("Full_Name"),
                        rs.getString("Section"),
                        rs.getString("Year_Level"),
                        rs.getString("StudentID"),
                        rs.getString("Acc_No"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Date"),
                        rs.getString("Due_Date"),
                        rs.getInt("idborrow_stud")
                ));
            }

            Stud_Pending_Table.setItems(filteredList);
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error searching student data: " + ex.getMessage());
        }
    }

    private void searchTeachPendData(String query) {
        DB_connection.init();
        Connection c = DB_connection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        ObservableList<PendBookTeachTableView> filteredList = FXCollections.observableArrayList();

        try {
            String sql = "SELECT idborrow_teach, Full_Name, Designation, Acc_No, Title, Author, Category, Date FROM libborsys.borrow_teach "
                    + "WHERE Full_Name LIKE ? OR Designation LIKE ? OR Acc_No LIKE ? OR Title LIKE ? OR Author LIKE ? OR Category LIKE ? OR Date LIKE ?";
            ps = c.prepareStatement(sql);

            String searchQuery = "%" + query + "%";
            ps.setString(1, searchQuery);
            ps.setString(2, searchQuery);
            ps.setString(3, searchQuery);
            ps.setString(4, searchQuery);
            ps.setString(5, searchQuery);
            ps.setString(6, searchQuery);
            ps.setString(7, searchQuery);

            rs = ps.executeQuery();

            while (rs.next()) {
                filteredList.add(new PendBookTeachTableView(
                        rs.getString("Full_Name"),
                        rs.getString("Designation"),
                        rs.getString("Acc_No"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Date"),
                        rs.getInt("idborrow_teach")
                ));
            }

            Teach_Pending_Table.setItems(filteredList);
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error searching student data: " + ex.getMessage());
        }
    }

    @FXML
    private void AddStud(ActionEvent event) {
        if (studentStage != null && studentStage.isShowing()) {
            studentStage.toFront(); // Bring existing stage to front
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Student_Form_User.fxml"));
                studentStage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent mouseEvent) -> {
                    x = mouseEvent.getSceneX();
                    y = mouseEvent.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent mouseEvent) -> {
                    studentStage.setX(mouseEvent.getScreenX() - x);
                    studentStage.setY(mouseEvent.getScreenY() - y);
                });

                studentStage.initStyle(StageStyle.TRANSPARENT);
                studentStage.setScene(scene);
                studentStage.show();

                // Optional: Add event handler for when the student stage is closed
                studentStage.setOnHidden(e -> studentStage = null);
                studentStage.setOnCloseRequest(e -> studentStage = null);
            } catch (Exception e) {
                e.printStackTrace();
                // Consider showing an error message to the user
            }
        }
    }

    @FXML
    private void AddBook(ActionEvent event) {
        if (studentStage != null && studentStage.isShowing()) {
            studentStage.toFront();
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("AddBookAdmin.fxml"));
                studentStage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent mouseEvent) -> {
                    x = mouseEvent.getSceneX();
                    y = mouseEvent.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent mouseEvent) -> {
                    studentStage.setX(mouseEvent.getScreenX() - x);
                    studentStage.setY(mouseEvent.getScreenY() - y);
                });

                studentStage.initStyle(StageStyle.TRANSPARENT);
                studentStage.setScene(scene);
                studentStage.show();

                // Optional: Add event handler for when the student stage is closed
                studentStage.setOnHidden(e -> studentStage = null);
                studentStage.setOnCloseRequest(e -> studentStage = null);
            } catch (Exception e) {
                e.printStackTrace();
                // Consider showing an error message to the user
            }
        }
    }

    @FXML
    private void RetBook(ActionEvent event) {
        if (studentStage != null && studentStage.isShowing()) {
            studentStage.toFront(); // Bring existing stage to front
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("ReturnStud.fxml"));
                studentStage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent mouseEvent) -> {
                    x = mouseEvent.getSceneX();
                    y = mouseEvent.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent mouseEvent) -> {
                    studentStage.setX(mouseEvent.getScreenX() - x);
                    studentStage.setY(mouseEvent.getScreenY() - y);
                });

                studentStage.initStyle(StageStyle.TRANSPARENT);
                studentStage.setScene(scene);
                studentStage.show();

                // Optional: Add event handler for when the student stage is closed
                studentStage.setOnHidden(e -> studentStage = null);
                studentStage.setOnCloseRequest(e -> studentStage = null);
            } catch (Exception e) {
                e.printStackTrace();
                // Consider showing an error message to the user
            }
        }
    }

    @FXML
    private void BorrBook(ActionEvent event) {
        if (studentStage != null && studentStage.isShowing()) {
            studentStage.toFront(); // Bring existing stage to front
        } else {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Borrow_User.fxml"));
                studentStage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent mouseEvent) -> {
                    x = mouseEvent.getSceneX();
                    y = mouseEvent.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent mouseEvent) -> {
                    studentStage.setX(mouseEvent.getScreenX() - x);
                    studentStage.setY(mouseEvent.getScreenY() - y);
                });

                studentStage.initStyle(StageStyle.TRANSPARENT);
                studentStage.setScene(scene);
                studentStage.show();

                // Optional: Add event handler for when the student stage is closed
                studentStage.setOnHidden(e -> studentStage = null);
                studentStage.setOnCloseRequest(e -> studentStage = null);
            } catch (Exception e) {
                e.printStackTrace();
                // Consider showing an error message to the user
            }
        }
    }

    void loadStudentData() {
        DB_connection.init();

        Connection c = DB_connection.getConnection();

        PreparedStatement ps;
        ResultSet rs;
        studentList.clear();

        try {
            ps = c.prepareStatement("SELECT idStudent, Full_Name, Section, Year_Lvl, Student_ID FROM libborsys.student");
            rs = ps.executeQuery();

            while (rs.next()) {
                studentList.add(new Student(
                        rs.getString("Full_Name"),
                        rs.getString("Section"),
                        rs.getString("Year_Lvl"),
                        rs.getString("Student_ID"),
                        rs.getInt("idStudent")
                ));
            }

            AddStud_Table.setItems(studentList);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
        }
    }

    void loadBookData() {
        DB_connection.init();

        Connection c = DB_connection.getConnection();

        PreparedStatement ps;
        ResultSet rs;
        bookList.clear();

        try {
            ps = c.prepareStatement("SELECT idInventory, Title, Acc_No, Author, Category, Status FROM libborsys.inventory");
            rs = ps.executeQuery();

            while (rs.next()) {
                bookList.add(new ViewTable(
                        rs.getString("Title"),
                        rs.getString("Acc_No"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Status"),
                        rs.getInt("idInventory")
                ));
            }

            Books_Table.setItems(bookList);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
        }
    }

    void loadRetBookStudData() {
        DB_connection.init();

        Connection c = DB_connection.getConnection();

        PreparedStatement ps;
        ResultSet rs;
        Retbooklist.clear();

        try {
            ps = c.prepareStatement("SELECT idreturn_stud, Full_Name, Section, Year_Level, StudentID, Acc_No, Title, Author, Category, Date, Due_Date, Days_Elapsed FROM libborsys.return_stud");
            rs = ps.executeQuery();

            while (rs.next()) {
                Retbooklist.add(new RetBookTableView(
                        rs.getString("Full_Name"),
                        rs.getString("Section"),
                        rs.getString("Year_Level"),
                        rs.getString("StudentID"),
                        rs.getString("Acc_No"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Date"),
                        rs.getString("Due_Date"),
                        rs.getString("Days_Elapsed"),
                        rs.getInt("idreturn_stud")
                ));
            }

            Stud_Ret_Table.setItems(Retbooklist);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
        }
    }

    void loadRetBookTeachData() {
        DB_connection.init();

        Connection c = DB_connection.getConnection();

        PreparedStatement ps;
        ResultSet rs;
        RetbookTeachlist.clear();

        try {
            ps = c.prepareStatement("SELECT idreturn_teach, Full_Name, Designation, Acc_No, Title, Author, Category, Date FROM libborsys.return_teach");
            rs = ps.executeQuery();

            while (rs.next()) {
                RetbookTeachlist.add(new RetBookTeachTableView(
                        rs.getString("Full_Name"),
                        rs.getString("Designation"),
                        rs.getString("Acc_No"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Date"),
                        rs.getInt("idreturn_teach")
                ));
            }

            Teach_Ret_Table.setItems(RetbookTeachlist);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
        }
    }

    void loadPendBookStudData() {
        DB_connection.init();

        Connection c = DB_connection.getConnection();

        PreparedStatement ps;
        ResultSet rs;
        Pendbooklist.clear();

        try {
            ps = c.prepareStatement("SELECT idborrow_stud, Full_Name, Section, Year_Level, StudentID, Acc_No, Title, Author, Category, Date, Due_Date FROM libborsys.borrow_stud");
            rs = ps.executeQuery();

            while (rs.next()) {
                Pendbooklist.add(new PendBookStudTableView(
                        rs.getString("Full_Name"),
                        rs.getString("Section"),
                        rs.getString("Year_Level"),
                        rs.getString("StudentID"),
                        rs.getString("Acc_No"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Date"),
                        rs.getString("Due_Date"),
                        rs.getInt("idborrow_stud")
                ));
            }

            Stud_Pending_Table.setItems(Pendbooklist);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
        }
    }

    void loadPendBookTeachData() {
        DB_connection.init();

        Connection c = DB_connection.getConnection();

        PreparedStatement ps;
        ResultSet rs;
        PendbooklistT.clear();

        try {
            ps = c.prepareStatement("SELECT idborrow_teach, Full_Name, Designation, Acc_No, Title, Author, Category, Date FROM libborsys.borrow_teach");
            rs = ps.executeQuery();

            while (rs.next()) {
                PendbooklistT.add(new PendBookTeachTableView(
                        rs.getString("Full_Name"),
                        rs.getString("Designation"),
                        rs.getString("Acc_No"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Date"),
                        rs.getInt("idborrow_teach")
                ));
            }

            Teach_Pending_Table.setItems(PendbooklistT);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
        }
    }

    @FXML
    public void refresh() {
        loadStudentData();
    }

    @FXML
    public void refreshpendstud() {
        loadPendBookStudData();
    }

    @FXML
    public void refreshpendteach() {
        loadPendBookTeachData();
    }

    @FXML
    public void refreshbooks() {
        loadBookData();
    }
    
    public void refreshRetBookStud() {
        loadRetBookStudData();
    }

    public void refreshRetBookTeach() {
        loadRetBookTeachData();
    }

    public void refreshPendBookStud() {
        Pendbooklist.clear();

        DB_connection.init();
        Connection c = DB_connection.getConnection();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String selectedYear = academicYearComboBox.getValue();
        if (selectedYear == null || !selectedYear.startsWith("AY ")) {
            return;
        }

        String[] split = selectedYear.substring(3).split("-");
        int startYear = Integer.parseInt(split[0]);
        int endYear = Integer.parseInt(split[1]);

        LocalDate from = LocalDate.of(startYear, Month.MAY, 1);
        LocalDate to = LocalDate.of(endYear, Month.APRIL, 30);

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM libborsys.borrow_stud");
            ResultSet rs = ps.executeQuery();

            ObservableList<PendBookStudTableView> filtered = FXCollections.observableArrayList();

            while (rs.next()) {
                String dateStr = rs.getString("Date");
                try {
                    LocalDate date = LocalDate.parse(dateStr, formatter);
                    if (!date.isBefore(from) && !date.isAfter(to)) {
                        filtered.add(new PendBookStudTableView(
                                rs.getString("Full_Name"),
                                rs.getString("Section"),
                                rs.getString("Year_Level"),
                                rs.getString("StudentID"),
                                rs.getString("Acc_No"),
                                rs.getString("Title"),
                                rs.getString("Author"),
                                rs.getString("Category"),
                                rs.getString("Date"),
                                rs.getString("Due_Date"),
                                rs.getInt("idborrow_stud")
                        ));
                    }
                } catch (Exception e) {
                    System.out.println("Invalid date format (student pending): " + dateStr);
                }
            }

            Stud_Pending_Table.setItems(filtered);
            c.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void refreshPendBookTeach() {
        PendbooklistT.clear();

        DB_connection.init();
        Connection c = DB_connection.getConnection();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String selectedYear = academicYearComboBox.getValue();
        if (selectedYear == null || !selectedYear.startsWith("AY ")) {
            return;
        }

        String[] split = selectedYear.substring(3).split("-");
        int startYear = Integer.parseInt(split[0]);
        int endYear = Integer.parseInt(split[1]);

        LocalDate from = LocalDate.of(startYear, Month.MAY, 1);
        LocalDate to = LocalDate.of(endYear, Month.APRIL, 30);

        try {
            PreparedStatement ps = c.prepareStatement("SELECT * FROM libborsys.borrow_teach");
            ResultSet rs = ps.executeQuery();

            ObservableList<PendBookTeachTableView> filtered = FXCollections.observableArrayList();

            while (rs.next()) {
                String dateStr = rs.getString("Date");
                try {
                    LocalDate date = LocalDate.parse(dateStr, formatter);
                    if (!date.isBefore(from) && !date.isAfter(to)) {
                        filtered.add(new PendBookTeachTableView(
                                rs.getString("Full_Name"),
                                rs.getString("Designation"),
                                rs.getString("Acc_No"),
                                rs.getString("Title"),
                                rs.getString("Author"),
                                rs.getString("Category"),
                                rs.getString("Date"),
                                rs.getInt("idborrow_teach")
                        ));
                    }
                } catch (Exception e) {
                    System.out.println("Invalid date format (teacher pending): " + dateStr);
                }
            }

            Teach_Pending_Table.setItems(filtered);
            c.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void filterAcademicYearFor(String target) {
        String selectedYear = academicYearComboBox.getValue();
        if (selectedYear == null || !selectedYear.startsWith("AY ")) {
            return;
        }

        String[] split = selectedYear.substring(3).split("-");
        int startYear = Integer.parseInt(split[0]);
        int endYear = Integer.parseInt(split[1]);

        LocalDate from = LocalDate.of(startYear, Month.MAY, 1);
        LocalDate to = LocalDate.of(endYear, Month.APRIL, 30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (target.equals("stud_ret")) {
            filteredRetbooklist.setPredicate(rec -> {
                try {
                    LocalDate date = LocalDate.parse(rec.getDate(), formatter);
                    return !date.isBefore(from) && !date.isAfter(to);
                } catch (Exception e) {
                    return false;
                }
            });
        }

        if (target.equals("teach_ret")) {
            filteredRetbookTeachlist.setPredicate(rec -> {
                try {
                    LocalDate date = LocalDate.parse(rec.getDate(), formatter);
                    return !date.isBefore(from) && !date.isAfter(to);
                } catch (Exception e) {
                    return false;
                }
            });
        }

        if (target.equals("stud_pend")) {
            filteredPendbooklist.setPredicate(rec -> {
                try {
                    LocalDate date = LocalDate.parse(rec.getDate(), formatter);
                    return !date.isBefore(from) && !date.isAfter(to);
                } catch (Exception e) {
                    return false;
                }
            });
        }

        if (target.equals("teach_pend")) {
            filteredPendbooklistT.setPredicate(rec -> {
                try {
                    LocalDate date = LocalDate.parse(rec.getDate(), formatter);
                    return !date.isBefore(from) && !date.isAfter(to);
                } catch (Exception e) {
                    return false;
                }
            });
        }
    }

    @FXML
    public void viewStudRegist() {
        Students_Anc.setVisible(true);
        RetBook_Anc.setVisible(false);
        Books_Anc.setVisible(false);
        Pending_Anc.setVisible(false);
    }

    @FXML
    public void viewRetBook() {
        Students_Anc.setVisible(false);
        RetBook_Anc.setVisible(true);
        Books_Anc.setVisible(false);
        Pending_Anc.setVisible(false);
    }

    @FXML
    public void viewRetBookSTUD() {
        Students_Anc.setVisible(false);
        RetBook_Anc.setVisible(true);
        Books_Anc.setVisible(false);
        Pending_Anc.setVisible(false);
        Stud_Ret_Anc.setVisible(true);
        Teach_Ret_Anc.setVisible(false);
    }

    @FXML
    public void viewRetBookTEACH() {
        Students_Anc.setVisible(false);
        RetBook_Anc.setVisible(true);
        Books_Anc.setVisible(false);
        Pending_Anc.setVisible(false);
        Stud_Ret_Anc.setVisible(false);
        Teach_Ret_Anc.setVisible(true);
    }

    @FXML
    public void viewBooks() {
        Students_Anc.setVisible(false);
        RetBook_Anc.setVisible(false);
        Books_Anc.setVisible(true);
        Pending_Anc.setVisible(false);
    }

    @FXML
    public void viewPending() {
        Students_Anc.setVisible(false);
        RetBook_Anc.setVisible(false);
        Books_Anc.setVisible(false);
        Pending_Anc.setVisible(true);
    }

    @FXML
    public void viewPendingSTUD() {
        Students_Anc.setVisible(false);
        RetBook_Anc.setVisible(false);
        Books_Anc.setVisible(false);
        Pending_Anc.setVisible(true);
        Stud_Pending_Anc.setVisible(true);
        Teach_Pending_Anc.setVisible(false);
    }

    @FXML
    public void logout() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> option = alert.showAndWait();
        try {
            if (option.get().equals(ButtonType.OK)) {

                logout.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("User_Login.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void viewPendingTEACH() {
        Students_Anc.setVisible(false);
        RetBook_Anc.setVisible(false);
        Books_Anc.setVisible(false);
        Pending_Anc.setVisible(true);
        Stud_Pending_Anc.setVisible(false);
        Teach_Pending_Anc.setVisible(true);
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) MainForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @FXML
    public void maximize() {
        Stage stage = (Stage) MainForm.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

    private void showAlert(Alert.AlertType alertType, String database_Error, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
