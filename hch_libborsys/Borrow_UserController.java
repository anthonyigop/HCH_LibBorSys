/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hch_libborsys;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class Borrow_UserController implements Initializable {

    @FXML
    private Button close1;
    @FXML
    private AnchorPane Borr_StudTeach_User_Anc;
    @FXML
    private AnchorPane Borr_Student_User_Anc;
    @FXML
    private TextField StudID_Stud_Borr_txtf;
    @FXML
    private TextField FName_Stud_Borr_txtf;
    @FXML
    private TextField Section_Stud_Borr_txtf;
    @FXML
    private TextField YearLvl_Stud_Borr_txtf;
    @FXML
    private TextField AccNo_book_Stud_Borr_txtf;
    @FXML
    private TextField Title_book_Stud_Borr_txtf;
    @FXML
    private TextField Author_book_Stud_Borr_txtf;
    @FXML
    private TextField Category_book_Stud_Borr_txtf;
    @FXML
    private TextField Date_Stud_Borr_txtf;
    @FXML
    private TextField DueDate_Stud_Borr_txtf;
    @FXML
    private Button Add_btn_stud_User;
    @FXML
    private Button update_btn_stud_User;
    @FXML
    private Button delete_btn_stud_User;
    @FXML
    private Button ClrAll_btn_stud_User;
    @FXML
    private TextField Search_Borr_Stud_txtf;

    private ObservableList<BorrowStud> studList = FXCollections.observableArrayList();
    private FilteredList<BorrowStud> filteredStudList;

    @FXML
    private TableView<BorrowStud> Student_Add_Table;
    @FXML
    private TableColumn<BorrowStud, String> Full_Name;
    @FXML
    private TableColumn<BorrowStud, String> Section;
    @FXML
    private TableColumn<BorrowStud, String> Year_Lvl;
    @FXML
    private TableColumn<BorrowStud, String> StudentID;
    @FXML
    private TableColumn<BorrowStud, String> Acc_No;
    @FXML
    private TableColumn<BorrowStud, String> Title;
    @FXML
    private TableColumn<BorrowStud, String> Author;
    @FXML
    private TableColumn<BorrowStud, String> Category;
    @FXML
    private TableColumn<BorrowStud, String> Date;
    @FXML
    private TableColumn<BorrowStud, String> Due_Date;
    @FXML
    private AnchorPane Borr_Teacher_User_Anc;
    @FXML
    private TextField FName_Teach_Borr_txtf;
    @FXML
    private TextField AccNo_book_Teach_Borr_txtf;
    @FXML
    private TextField Title_book_Teach_Borr_txtf;
    @FXML
    private TextField Author_book_Teach_Borr_txtf;
    @FXML
    private TextField Category_book_Teach_Borr_txtf;
    @FXML
    private TextField Date_Teach_Borr_txtf;
    @FXML
    private Button Add_btn_teach_User;
    @FXML
    private Button Update_btn_teach_User;
    @FXML
    private Button Delete_btn_teach_User;
    @FXML
    private Button ClrAll_btn_teach_User;
    @FXML
    private TextField Search_Borr_Teach_txtf;
    @FXML
    private TableView<BorrowTeach> Teach_Add_Table;
    @FXML
    private AnchorPane Borrowbk_Main_Anc;

    private ObservableList<BorrowTeach> teachList = FXCollections.observableArrayList();
    private FilteredList<BorrowTeach> filteredTeachList;

    @FXML
    private TableColumn<BorrowTeach, String> Full_Name_teach;
    @FXML
    private TableColumn<BorrowTeach, String> Designation_teach;
    @FXML
    private TableColumn<BorrowTeach, String> Acc_No_teach;
    @FXML
    private TableColumn<BorrowTeach, String> Title_teach;
    @FXML
    private TableColumn<BorrowTeach, String> Author_teach;
    @FXML
    private TableColumn<BorrowTeach, String> Category_teach;
    @FXML
    private TableColumn<BorrowTeach, String> Date_teach;
    @FXML
    private TextField Designation_Teach_Borr_txtf;
    @FXML
    private Button Stud_btn;
    @FXML
    private Button Teach_btn;
    @FXML
    private Button bck_btn;
    @FXML
    private Button StudID_btn;
    @FXML
    private Button AccNo_btn;

    private int selectedBorrStudtId = -1;
    private int selectedBorrTeachId = -1;
    @FXML
    private Button loadtablestud;
    @FXML
    private Button AccNo_btn1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Update_btn_teach_User.setDisable(true);
        Delete_btn_teach_User.setDisable(true);
        update_btn_stud_User.setDisable(true);
        delete_btn_stud_User.setDisable(true);

        // Listener for AccNo_book_Stud_Borr_txtf to auto-calculate Due Date
        AccNo_book_Stud_Borr_txtf.focusedProperty().addListener((obs, oldFocus, newFocus) -> {
            if (newFocus) { // When the AccNo field gains focus
                LocalDate currentDate = LocalDate.now(ZoneId.of("Asia/Manila"));
                LocalDate dueDate = currentDate.plusDays(7);

                // Ensure DueDate doesn't fall on a weekend (Saturday or Sunday)
                while (dueDate.getDayOfWeek() == DayOfWeek.SATURDAY || dueDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    dueDate = dueDate.plusDays(1);
                }
                DueDate_Stud_Borr_txtf.setText(dueDate.toString());
            }
        });

        Student_Add_Table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedBorrStudtId = newSelection.getIdborrow_stud();
                populateStudTextFields(newSelection);
                checkStudTextFieldsForUpdateButton();
                delete_btn_stud_User.setDisable(false);
            } else {
                selectedBorrStudtId = -1;
                Clear_Stud_Fields(null);
                update_btn_stud_User.setDisable(true);
                delete_btn_stud_User.setDisable(true);
            }
        });

        Teach_Add_Table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedBorrTeachId = newSelection.getIdTeacher();
                populateTeachTextFields(newSelection);
                checkTeachTextFieldsForUpdateButton();
                Delete_btn_teach_User.setDisable(false);
            } else {
                selectedBorrTeachId = -1;
                Clear_Stud_Fields(null);
                Update_btn_teach_User.setDisable(true);
                Delete_btn_teach_User.setDisable(true);
            }
        });

        // Text property listeners for Student fields
        FName_Stud_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkStudTextFieldsForUpdateButton());
        Section_Stud_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkStudTextFieldsForUpdateButton());
        YearLvl_Stud_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkStudTextFieldsForUpdateButton());
        StudID_Stud_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkStudTextFieldsForUpdateButton());
        AccNo_book_Stud_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkStudTextFieldsForUpdateButton());
        Title_book_Stud_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkStudTextFieldsForUpdateButton());
        Author_book_Stud_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkStudTextFieldsForUpdateButton());
        Category_book_Stud_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkStudTextFieldsForUpdateButton());
        Date_Stud_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkStudTextFieldsForUpdateButton());
        DueDate_Stud_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkStudTextFieldsForUpdateButton());

        // Text property listeners for Teacher fields
        FName_Teach_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTeachTextFieldsForUpdateButton());
        Designation_Teach_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTeachTextFieldsForUpdateButton());
        AccNo_book_Teach_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTeachTextFieldsForUpdateButton());
        Title_book_Teach_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTeachTextFieldsForUpdateButton());
        Author_book_Teach_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTeachTextFieldsForUpdateButton());
        Category_book_Teach_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTeachTextFieldsForUpdateButton());
        Date_Teach_Borr_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTeachTextFieldsForUpdateButton());

        LocalDate currentDate = LocalDate.now(java.time.ZoneId.of("Asia/Manila"));
        Date_Stud_Borr_txtf.setText(currentDate.toString());
        Date_Teach_Borr_txtf.setText(currentDate.toString());

        Full_Name.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));
        Section.setCellValueFactory(new PropertyValueFactory<>("Section"));
        Year_Lvl.setCellValueFactory(new PropertyValueFactory<>("Year_Level"));
        StudentID.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        Acc_No.setCellValueFactory(new PropertyValueFactory<>("Acc_No"));
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Due_Date.setCellValueFactory(new PropertyValueFactory<>("Due_Date"));
        loadBorrStud();

        Full_Name_teach.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));
        Designation_teach.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        Acc_No_teach.setCellValueFactory(new PropertyValueFactory<>("Acc_No"));
        Title_teach.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Author_teach.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Category_teach.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Date_teach.setCellValueFactory(new PropertyValueFactory<>("Date"));
        loadBorrTeach();

        //student
        filteredStudList = new FilteredList<>(studList, p -> true);
        Search_Borr_Stud_txtf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredStudList.setPredicate(borrowStud -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (borrowStud.getFull_Name().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowStud.getSection().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowStud.getYear_Level().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowStud.getStudentID().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowStud.getAcc_No().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowStud.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowStud.getAuthor().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowStud.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowStud.getDate().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowStud.getDue_Date().toLowerCase().contains(lowerCaseFilter)) {
                        return true;

                    }
                    return false;
                });
            }
        });

        SortedList<BorrowStud> sortedStudList = new SortedList<>(filteredStudList);
        sortedStudList.comparatorProperty().bind(Student_Add_Table.comparatorProperty());
        Student_Add_Table.setItems(sortedStudList);

        //teacher
        filteredTeachList = new FilteredList<>(teachList, p -> true);
        Search_Borr_Teach_txtf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                filteredTeachList.setPredicate(borrowTeach -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (borrowTeach.getFull_Name().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowTeach.getDesignation().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowTeach.getAcc_No().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowTeach.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowTeach.getAuthor().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowTeach.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    } else if (borrowTeach.getDate().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            }
        });

        SortedList<BorrowTeach> sortedTeachList = new SortedList<>(filteredTeachList);
        sortedTeachList.comparatorProperty().bind(Teach_Add_Table.comparatorProperty());
        Teach_Add_Table.setItems(sortedTeachList);
    }

    // Populate TextFields for Student
    private void populateStudTextFields(BorrowStud student) {
        if (student != null) {
            FName_Stud_Borr_txtf.setText(student.getFull_Name());
            Section_Stud_Borr_txtf.setText(student.getSection());
            YearLvl_Stud_Borr_txtf.setText(student.getYear_Level());
            StudID_Stud_Borr_txtf.setText(student.getStudentID());
            AccNo_book_Stud_Borr_txtf.setText(student.getAcc_No());
            Title_book_Stud_Borr_txtf.setText(student.getTitle());
            Author_book_Stud_Borr_txtf.setText(student.getAuthor());
            Category_book_Stud_Borr_txtf.setText(student.getCategory());
            Date_Stud_Borr_txtf.setText(student.getDate());
            DueDate_Stud_Borr_txtf.setText(student.getDue_Date());
        }
    }

    // Check if all required Student TextFields are filled to enable Update button
    private void checkStudTextFieldsForUpdateButton() {
        boolean allFieldsFilled = !FName_Stud_Borr_txtf.getText().isEmpty()
                && !Section_Stud_Borr_txtf.getText().isEmpty()
                && !YearLvl_Stud_Borr_txtf.getText().isEmpty()
                && !StudID_Stud_Borr_txtf.getText().isEmpty()
                && !AccNo_book_Stud_Borr_txtf.getText().isEmpty()
                && !Title_book_Stud_Borr_txtf.getText().isEmpty()
                && !Author_book_Stud_Borr_txtf.getText().isEmpty()
                && !Category_book_Stud_Borr_txtf.getText().isEmpty()
                && !Date_Stud_Borr_txtf.getText().isEmpty()
                && !DueDate_Stud_Borr_txtf.getText().isEmpty();
        update_btn_stud_User.setDisable(!allFieldsFilled);
    }

    // Populate TextFields for Teacher
    private void populateTeachTextFields(BorrowTeach teacher) {
        if (teacher != null) {
            FName_Teach_Borr_txtf.setText(teacher.getFull_Name());
            Designation_Teach_Borr_txtf.setText(teacher.getDesignation());
            AccNo_book_Teach_Borr_txtf.setText(teacher.getAcc_No());
            Title_book_Teach_Borr_txtf.setText(teacher.getTitle());
            Author_book_Teach_Borr_txtf.setText(teacher.getAuthor());
            Category_book_Teach_Borr_txtf.setText(teacher.getCategory());
            Date_Teach_Borr_txtf.setText(teacher.getDate());
        }
    }

    // Check if all required Teacher TextFields are filled to enable Update button
    private void checkTeachTextFieldsForUpdateButton() {
        boolean allFieldsFilled = !FName_Teach_Borr_txtf.getText().isEmpty()
                && !Designation_Teach_Borr_txtf.getText().isEmpty()
                && !AccNo_book_Teach_Borr_txtf.getText().isEmpty()
                && !Title_book_Teach_Borr_txtf.getText().isEmpty()
                && !Author_book_Teach_Borr_txtf.getText().isEmpty()
                && !Category_book_Teach_Borr_txtf.getText().isEmpty()
                && !Date_Teach_Borr_txtf.getText().isEmpty();
        Update_btn_teach_User.setDisable(!allFieldsFilled);
    }

    @FXML
    private void close(ActionEvent event) {
        Stage stage = (Stage) Borrowbk_Main_Anc.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void Add_Stud_btn(ActionEvent event) throws IOException {
        if (FName_Stud_Borr_txtf.getText().isEmpty() || Section_Stud_Borr_txtf.getText().isEmpty()
                || YearLvl_Stud_Borr_txtf.getText().isEmpty() || StudID_Stud_Borr_txtf.getText().isEmpty() || AccNo_book_Stud_Borr_txtf.getText().isEmpty()
                || Title_book_Stud_Borr_txtf.getText().isEmpty() || Author_book_Stud_Borr_txtf.getText().isEmpty() || Category_book_Stud_Borr_txtf.getText().isEmpty()
                || Date_Stud_Borr_txtf.getText().isEmpty() || DueDate_Stud_Borr_txtf.getText().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled out.");
            alert.showAndWait();
            return;
        }

        String fullName = FName_Stud_Borr_txtf.getText().trim();
        String section = Section_Stud_Borr_txtf.getText().trim();
        String yearLevel = YearLvl_Stud_Borr_txtf.getText().trim();
        String studentID = StudID_Stud_Borr_txtf.getText().trim();
        String accNo = AccNo_book_Stud_Borr_txtf.getText().trim();
        String dueDateStr = DueDate_Stud_Borr_txtf.getText().trim();

        try {
            DB_connection.init();
            try (Connection c = DB_connection.getConnection(); PreparedStatement checkBorrowingLimitPs = c.prepareStatement("SELECT idborrow_stud FROM libborsys.borrow_stud WHERE StudentID = ? AND Due_Date >= CURRENT_DATE")) {

                checkBorrowingLimitPs.setString(1, studentID);
                ResultSet rsBorrowingLimit = checkBorrowingLimitPs.executeQuery();

                if (rsBorrowingLimit.next()) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Borrowing Limit");
                    alert.setHeaderText(null);
                    alert.setContentText("Student ID '" + studentID + "' already has a currently borrowed book. Please wait until it is returned.");
                    Clear_Stud_Fields(event);
                    alert.showAndWait();
                    return; // Prevent insertion
                }

                try (PreparedStatement checkAvailabilityPs = c.prepareStatement("SELECT Status FROM libborsys.inventory WHERE Acc_No = ?")) {
                    checkAvailabilityPs.setString(1, accNo);
                    ResultSet rsAvailability = checkAvailabilityPs.executeQuery();

                    if (rsAvailability.next()) {
                        String status = rsAvailability.getString("Status");
                        if (!"Available".equalsIgnoreCase(status)) {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Borrow Error");
                            alert.setHeaderText(null);
                            alert.setContentText("The book with Accession Number '" + accNo + "' is currently " + status + " and cannot be borrowed.");
                            alert.showAndWait();
                            return; // Prevent insertion
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Database Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Error: Book with Accession Number '" + accNo + "' not found in inventory.");
                        alert.showAndWait();
                        return; // Prevent insertion
                    }
                }

                // If no active borrowing and book is available, proceed with insertion
                try (PreparedStatement insertPs = c.prepareStatement("INSERT INTO libborsys.borrow_stud (Full_Name, Section, Year_Level, StudentID, Acc_No, Title, Author, Category, Date, Due_Date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
                    insertPs.setString(1, fullName);
                    insertPs.setString(2, section);
                    insertPs.setString(3, yearLevel);
                    insertPs.setString(4, studentID);
                    insertPs.setString(5, accNo);
                    insertPs.setString(6, Title_book_Stud_Borr_txtf.getText());
                    insertPs.setString(7, Author_book_Stud_Borr_txtf.getText());
                    insertPs.setString(8, Category_book_Stud_Borr_txtf.getText());
                    insertPs.setString(9, Date_Stud_Borr_txtf.getText());
                    insertPs.setString(10, dueDateStr);

                    if (!insertPs.execute()) {
                        // Update the inventory status to "Unavailable"
                        try (PreparedStatement updateInventoryPs = c.prepareStatement("UPDATE libborsys.inventory SET Status = 'Unavailable' WHERE Acc_No = ?")) {
                            updateInventoryPs.setString(1, accNo);
                            updateInventoryPs.executeUpdate();

                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Book borrowed successfully!");
                            alert.showAndWait();
                            loadBorrStud();
                            closeTableViewUser();
                            // Optionally clear the input fields after successful borrowing
                            Clear_Stud_Fields(null);
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, null, ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("Error adding borrowing record: " + ex.getMessage());
            alert.showAndWait();
        }
    }
        public void closeTableViewUser() throws IOException{
                Parent root = FXMLLoader.load(getClass().getResource("TableViewUser.fxml"));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);
                    stage.close();

}
    @FXML
    public void Add_Teacher_btn(ActionEvent event) {
        if (FName_Teach_Borr_txtf.getText().isEmpty() || Designation_Teach_Borr_txtf.getText().isEmpty()
            || AccNo_book_Teach_Borr_txtf.getText().isEmpty() || Title_book_Teach_Borr_txtf.getText().isEmpty()
            || Author_book_Teach_Borr_txtf.getText().isEmpty() || Category_book_Teach_Borr_txtf.getText().isEmpty()
            || Date_Teach_Borr_txtf.getText().isEmpty()) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Input Error");
        alert.setHeaderText(null);
        alert.setContentText("All fields must be filled out.");
        alert.showAndWait();
        return;
    }

    String fullName = FName_Teach_Borr_txtf.getText().trim();
    String designation = Designation_Teach_Borr_txtf.getText().trim();
    String accNo = AccNo_book_Teach_Borr_txtf.getText().trim();
    String borrowDateStr = Date_Teach_Borr_txtf.getText().trim(); // Assuming you might want to track borrow date for teachers

    try {
        DB_connection.init();
        try (Connection c = DB_connection.getConnection();
             // Check if the teacher has already borrowed this specific book and hasn't returned it
             PreparedStatement checkBorrowPs = c.prepareStatement("SELECT idborrow_teach FROM libborsys.borrow_teach WHERE Full_Name = ? AND Designation = ? AND Acc_No = ?")) {

            checkBorrowPs.setString(1, fullName);
            checkBorrowPs.setString(2, designation);
            checkBorrowPs.setString(3, accNo);
            ResultSet rsBorrow = checkBorrowPs.executeQuery();

            if (rsBorrow.next()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Borrow Error");
                alert.setHeaderText(null);
                alert.setContentText("Teacher '" + fullName + "' with Designation '" + designation + "' has already borrowed the book with Accession Number '" + accNo + "' and has not returned it yet.");
                alert.showAndWait();
                return; // Prevent insertion
            }

            // Check if the book is available
            try (PreparedStatement checkAvailabilityPs = c.prepareStatement("SELECT Status FROM libborsys.inventory WHERE Acc_No = ?")) {
                checkAvailabilityPs.setString(1, accNo);
                ResultSet rsAvailability = checkAvailabilityPs.executeQuery();

                if (rsAvailability.next()) {
                    String status = rsAvailability.getString("Status");
                    if (!"Available".equalsIgnoreCase(status)) {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Borrow Error");
                        alert.setHeaderText(null);
                        alert.setContentText("The book with Accession Number '" + accNo + "' is currently " + status + " and cannot be borrowed.");
                        alert.showAndWait();
                        return; // Prevent insertion
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Database Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Error: Book with Accession Number '" + accNo + "' not found in inventory.");
                    alert.showAndWait();
                    return; // Prevent insertion
                }
            }

            // If no existing borrowing and book is available, proceed with insertion
            try (PreparedStatement insertPs = c.prepareStatement("INSERT INTO libborsys.borrow_teach (Full_Name, Designation, Acc_No, Title, Author, Category, Date) VALUES (?, ?, ?, ?, ?, ?, ?)")) {
                insertPs.setString(1, fullName);
                insertPs.setString(2, designation);
                insertPs.setString(3, accNo);
                insertPs.setString(4, Title_book_Teach_Borr_txtf.getText());
                insertPs.setString(5, Author_book_Teach_Borr_txtf.getText());
                insertPs.setString(6, Category_book_Teach_Borr_txtf.getText());
                insertPs.setString(7, borrowDateStr);

                if (!insertPs.execute()) {
                    // Update the inventory status to "Unavailable"
                    try (PreparedStatement updateInventoryPs = c.prepareStatement("UPDATE libborsys.inventory SET Status = 'Unavailable' WHERE Acc_No = ?")) {
                        updateInventoryPs.setString(1, accNo);
                        updateInventoryPs.executeUpdate();

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Added!");
                        alert.showAndWait();
                        loadBorrTeach();
                        Clear_Teach_Fields(null); // Assuming you have this method
                    }
                }
            }
        }

    } catch (SQLException ex) {
        Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, null, ex);
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Database Error");
        alert.setHeaderText(null);
        alert.setHeaderText(null);
        alert.setContentText("Error adding borrowing record: " + ex.getMessage());
        alert.showAndWait();
    }
    }

    @FXML
    public void Delete_Stud_btn(ActionEvent event) {
        if (selectedBorrStudtId == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deletion Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a student record to delete.");
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Deletion");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you want to delete the selected student?");
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    DB_connection.init();
                    Connection c = DB_connection.getConnection();
                    PreparedStatement ps;

                    String sql = "DELETE FROM libborsys.borrow_stud WHERE idborrow_stud = ?";
                    ps = c.prepareStatement(sql);
                    ps.setInt(1, selectedBorrStudtId);

                    int rowsAffected = ps.executeUpdate();
                    if (rowsAffected > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Student record successfully deleted!");
                        Clear_Stud_Fields(event);
                        alert.showAndWait();
                        loadBorrStud();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Deletion Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to delete the selected student record.");
                        alert.showAndWait();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
    }

    @FXML
    public void Delete_Teacher_btn(ActionEvent event) {
        if (selectedBorrTeachId == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Deletion Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a teacher record to delete.");
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Deletion");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you want to delete the selected teacher and mark the book as available?");
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                Connection c = null;
                PreparedStatement psBorrow = null;
                PreparedStatement psUpdateInventory = null;
                ResultSet rsBorrow = null;
                try {
                    DB_connection.init();
                    c = DB_connection.getConnection();
                    c.setAutoCommit(false); // Start a transaction

                    // 1. Retrieve the Acc_No of the borrowed book
                    String selectAccNoSql = "SELECT Acc_No FROM libborsys.borrow_teach WHERE idborrow_teach = ?";
                    psBorrow = c.prepareStatement(selectAccNoSql);
                    psBorrow.setInt(1, selectedBorrTeachId);
                    rsBorrow = psBorrow.executeQuery();

                    String accNo = null;
                    if (rsBorrow.next()) {
                        accNo = rsBorrow.getString("Acc_No");
                    }

                    if (accNo != null) {
                        // 2. Delete the record from borrow_teach
                        String deleteBorrowSql = "DELETE FROM libborsys.borrow_teach WHERE idborrow_teach = ?";
                        psBorrow = c.prepareStatement(deleteBorrowSql);
                        psBorrow.setInt(1, selectedBorrTeachId);
                        int borrowRowsAffected = psBorrow.executeUpdate();

                        if (borrowRowsAffected > 0) {
                            // 3. Update the inventory table
                            String updateInventorySql = "UPDATE libborsys.inventory SET Status = 'Available' WHERE Acc_No = ?";
                            psUpdateInventory = c.prepareStatement(updateInventorySql);
                            psUpdateInventory.setString(1, accNo);
                            int inventoryRowsAffected = psUpdateInventory.executeUpdate();

                            if (inventoryRowsAffected >= 0) { // It's okay if no book was found to update
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Information Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Teacher record deleted and book status updated to Available!");
                                Clear_Teach_Fields(event);
                                alert.showAndWait();
                                loadBorrTeach(); // Reload the teacher borrow table
                                c.commit(); // Commit the transaction
                            } else {
                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                alert.setTitle("Update Error");
                                alert.setHeaderText(null);
                                alert.setContentText("Failed to update book status in inventory.");
                                alert.showAndWait();
                                c.rollback(); // Rollback the transaction
                            }
                        } else {
                            Alert alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Deletion Error");
                            alert.setHeaderText(null);
                            alert.setContentText("Failed to delete the selected teacher record.");
                            alert.showAndWait();
                            c.rollback(); // Rollback the transaction
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Could not retrieve Acc_No for the selected teacher.");
                        alert.showAndWait();
                        c.rollback(); // Rollback the transaction
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, null, ex);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Database Error");
                    alert.setHeaderText(null);
                    alert.setContentText("An error occurred during the deletion and update process: " + ex.getMessage());
                    alert.showAndWait();
                    if (c != null) {
                        try {
                            c.rollback(); // Rollback on error
                        } catch (SQLException rollbackEx) {
                            Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, "Rollback failed: ", rollbackEx);
                        }
                    }
                } 
            }
        });
    }

    @FXML
    public void Update_Stud_btn(ActionEvent event) {
        if (selectedBorrStudtId == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a student record to update.");
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Update");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you want to update the selected student's record?");
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    DB_connection.init();
                    Connection c = DB_connection.getConnection();
                    PreparedStatement ps;

                    String sql = "UPDATE libborsys.borrow_stud SET Full_Name = ?, Section = ?, Year_Level = ?, Acc_No = ?, Title = ?, Author = ?, Category = ?, Date = ?, Due_Date = ? WHERE idborrow_stud = ?";
                    ps = c.prepareStatement(sql);
                    ps.setString(1, FName_Stud_Borr_txtf.getText());
                    ps.setString(2, Section_Stud_Borr_txtf.getText());
                    ps.setString(3, YearLvl_Stud_Borr_txtf.getText());
                    ps.setString(4, AccNo_book_Stud_Borr_txtf.getText());
                    ps.setString(5, Title_book_Stud_Borr_txtf.getText());
                    ps.setString(6, Author_book_Stud_Borr_txtf.getText());
                    ps.setString(7, Category_book_Stud_Borr_txtf.getText());
                    ps.setString(8, Date_Stud_Borr_txtf.getText());
                    ps.setString(9, DueDate_Stud_Borr_txtf.getText());
                    ps.setInt(10, selectedBorrStudtId); // Use the stored primary key

                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Student record successfully updated!");
                        alert.showAndWait();
                        loadBorrStud();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Update Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to update the selected student record.");
                        alert.showAndWait();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    public void Update_Teacher_btn(ActionEvent event) {
        if (selectedBorrTeachId == -1) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Update Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a teacher record to update.");
            alert.showAndWait();
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Update");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you want to update the selected teacher's record?");
        confirmation.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    DB_connection.init();
                    Connection c = DB_connection.getConnection();
                    PreparedStatement ps;

                    String sql = "UPDATE libborsys.borrow_teach SET Full_Name = ?, Designation = ?, Acc_No = ?, Title = ?, Author = ?, Category = ?, Date = ? WHERE idborrow_teach = ?";
                    ps = c.prepareStatement(sql);
                    ps.setString(1, FName_Teach_Borr_txtf.getText());
                    ps.setString(2, Designation_Teach_Borr_txtf.getText());
                    ps.setString(3, AccNo_book_Teach_Borr_txtf.getText());
                    ps.setString(4, Title_book_Teach_Borr_txtf.getText());
                    ps.setString(5, Author_book_Teach_Borr_txtf.getText());
                    ps.setString(6, Category_book_Teach_Borr_txtf.getText());
                    ps.setString(7, Date_Teach_Borr_txtf.getText());
                    ps.setInt(8, selectedBorrTeachId); // Use the stored primary key

                    int rowsUpdated = ps.executeUpdate();
                    if (rowsUpdated > 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Teacher record successfully updated!");
                        alert.showAndWait();
                        loadBorrTeach();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Update Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Failed to update the selected teacher record.");
                        alert.showAndWait();
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    @FXML
    public void Clear_Stud_Fields(ActionEvent event) {
        FName_Stud_Borr_txtf.clear();
        Section_Stud_Borr_txtf.clear();
        YearLvl_Stud_Borr_txtf.clear();
        StudID_Stud_Borr_txtf.clear();
        AccNo_book_Stud_Borr_txtf.clear();
        Title_book_Stud_Borr_txtf.clear();
        Author_book_Stud_Borr_txtf.clear();
        Category_book_Stud_Borr_txtf.clear();
        update_btn_stud_User.setDisable(true);
        delete_btn_stud_User.setDisable(true);
        Add_btn_stud_User.setDisable(false);
        selectedBorrStudtId = -1;
    }

    @FXML
    public void Clear_Teach_Fields(ActionEvent event) {
        FName_Teach_Borr_txtf.clear();
        Designation_Teach_Borr_txtf.clear();
        AccNo_book_Teach_Borr_txtf.clear();
        Title_book_Teach_Borr_txtf.clear();
        Author_book_Teach_Borr_txtf.clear();
        Category_book_Teach_Borr_txtf.clear();
        Update_btn_teach_User.setDisable(true);
        Delete_btn_teach_User.setDisable(true);
        Add_btn_teach_User.setDisable(false);
        selectedBorrTeachId = -1;
    }

    @FXML
    private void loadStudID(ActionEvent event) {
        String studid = StudID_Stud_Borr_txtf.getText().trim();

        if (studid.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a Student ID.");
            alert.showAndWait();
            return;
        }

        try {
            DB_connection.init();
            Connection c = DB_connection.getConnection();
            PreparedStatement ps;
            ResultSet rs;

            String sql = "SELECT Full_Name, Section, Year_Lvl FROM libborsys.student WHERE Student_ID = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, studid);
            rs = ps.executeQuery();

            if (rs.next()) {
                FName_Stud_Borr_txtf.setText(rs.getString("Full_Name"));
                Section_Stud_Borr_txtf.setText(rs.getString("Section"));
                YearLvl_Stud_Borr_txtf.setText(rs.getString("Year_Lvl"));
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("No student found with Student ID: " + studid);
                alert.showAndWait();
                FName_Stud_Borr_txtf.clear();
                Section_Stud_Borr_txtf.clear();
                YearLvl_Stud_Borr_txtf.clear();
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while accessing the database.");
            alert.showAndWait();
        }
    }

    @FXML
    private void loadAccNo(ActionEvent event) {
        String accno = AccNo_book_Stud_Borr_txtf.getText().trim();

        if (accno.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an Accession Number.");
            alert.showAndWait();
            return;
        }

        try {
            DB_connection.init();
            Connection c = DB_connection.getConnection();
            PreparedStatement ps;
            ResultSet rs;

            String sql = "SELECT Title, Author, Category, Status FROM libborsys.inventory WHERE Acc_No = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, accno);
            rs = ps.executeQuery();

            if (rs.next()) {
                String status = rs.getString("Status");
                if ("Available".equalsIgnoreCase(status)) {
                    Title_book_Stud_Borr_txtf.setText(rs.getString("Title"));
                    Author_book_Stud_Borr_txtf.setText(rs.getString("Author"));
                    Category_book_Stud_Borr_txtf.setText(rs.getString("Category"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("The book with Accession Number '" + accno + "' is currently " + status + " and cannot be borrowed.");
                    alert.showAndWait();
                    Title_book_Stud_Borr_txtf.clear();
                    Author_book_Stud_Borr_txtf.clear();
                    Category_book_Stud_Borr_txtf.clear();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("No book found with Acc Number: " + accno);
                alert.showAndWait();
                Title_book_Stud_Borr_txtf.clear();
                Author_book_Stud_Borr_txtf.clear();
                Category_book_Stud_Borr_txtf.clear();
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while accessing the database.");
            alert.showAndWait();
        }
    }

    @FXML
    private void loadAccNoTeach(ActionEvent event) {
        String accno = AccNo_book_Teach_Borr_txtf.getText().trim();

        if (accno.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an Accession #.");
            alert.showAndWait();
            return;
        }

        try {
            DB_connection.init();
            Connection c = DB_connection.getConnection();
            PreparedStatement ps;
            ResultSet rs;

            String sql = "SELECT Title, Author, Category, Status FROM libborsys.inventory WHERE Acc_No = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, accno);
            rs = ps.executeQuery();

            if (rs.next()) {
                String status = rs.getString("Status");
                if ("Available".equalsIgnoreCase(status)) {
                    Title_book_Teach_Borr_txtf.setText(rs.getString("Title"));
                    Author_book_Teach_Borr_txtf.setText(rs.getString("Author"));
                    Category_book_Teach_Borr_txtf.setText(rs.getString("Category"));
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning");
                    alert.setHeaderText(null);
                    alert.setContentText("The book with Accession Number '" + accno + "' is currently " + status + " and cannot be borrowed.");
                    alert.showAndWait();
                    Title_book_Teach_Borr_txtf.clear();
                    Author_book_Teach_Borr_txtf.clear();
                    Category_book_Teach_Borr_txtf.clear();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("No book found with Acc #: " + accno);
                alert.showAndWait();
                Title_book_Teach_Borr_txtf.clear();
                Author_book_Teach_Borr_txtf.clear();
                Category_book_Teach_Borr_txtf.clear();
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while accessing the database.");
            alert.showAndWait();
        }
    }

    @FXML
    private void backbtn(ActionEvent event) {
        if (event.getSource() == bck_btn) {
            Borr_StudTeach_User_Anc.setVisible(true);
            Borr_Student_User_Anc.setVisible(false);
            Borr_Teacher_User_Anc.setVisible(false);
        }
    }

    @FXML
    private void viewStudBor(ActionEvent event) {
        if (event.getSource() == Stud_btn) {
            Borr_StudTeach_User_Anc.setVisible(false);
            Borr_Student_User_Anc.setVisible(true);
            Borr_Teacher_User_Anc.setVisible(false);
        }
    }

    @FXML
    private void viewTeachBor(ActionEvent event) {
        if (event.getSource() == Teach_btn) {
            Borr_StudTeach_User_Anc.setVisible(false);
            Borr_Student_User_Anc.setVisible(false);
            Borr_Teacher_User_Anc.setVisible(true);
        }
    }

    void loadBorrStud() {
        DB_connection.init();

        Connection c = DB_connection.getConnection();

        PreparedStatement ps;
        ResultSet rs;
        studList.clear();

        try {
            ps = c.prepareStatement("SELECT idborrow_stud, Full_Name, Section, Year_Level, StudentID, Acc_No, Title, Author, Category, Date, Due_Date FROM libborsys.borrow_stud");
            rs = ps.executeQuery();

            while (rs.next()) {
                studList.add(new BorrowStud(
                        rs.getInt("idborrow_stud"),
                        rs.getString("Full_Name"),
                        rs.getString("Section"),
                        rs.getString("Year_Level"),
                        rs.getString("StudentID"),
                        rs.getString("Acc_No"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Date"),
                        rs.getString("Due_Date")
                ));
            }

            Student_Add_Table.setItems(studList);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
        }
    }

    @FXML
    public void selectStudentRow() {
        int row = Student_Add_Table.getSelectionModel().getSelectedIndex();
        if (row != -1) {
            BorrowStud selectedBorrowStud = Student_Add_Table.getItems().get(row);

            // Populate TextFields based on the selected row
            FName_Stud_Borr_txtf.setText(selectedBorrowStud.getFull_Name());
            Section_Stud_Borr_txtf.setText(selectedBorrowStud.getSection());
            StudID_Stud_Borr_txtf.setText(selectedBorrowStud.getStudentID());
            YearLvl_Stud_Borr_txtf.setText(selectedBorrowStud.getYear_Level());
            AccNo_book_Stud_Borr_txtf.setText(selectedBorrowStud.getAcc_No());
            Title_book_Stud_Borr_txtf.setText(selectedBorrowStud.getTitle());
            Author_book_Stud_Borr_txtf.setText(selectedBorrowStud.getAuthor());
            Category_book_Stud_Borr_txtf.setText(selectedBorrowStud.getCategory());
            Date_Stud_Borr_txtf.setText(selectedBorrowStud.getDate());
            DueDate_Stud_Borr_txtf.setText(selectedBorrowStud.getDue_Date());

            // You might want to also update the selectedBorrowStud variable here
            selectedBorrowStud = selectedBorrowStud; // Assign the selected item to the class-level variable
            checkStudTextFieldsForUpdateButton(); // Enable/disable update button
            delete_btn_stud_User.setDisable(false); // Enable delete button
        }
    }

    void loadBorrTeach() {
        DB_connection.init();
        Connection c = DB_connection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        teachList.clear();

        try {
            ps = c.prepareStatement("SELECT idborrow_teach, Full_Name, Designation, Acc_No, Title, Author, Category, Date FROM libborsys.borrow_teach");
            rs = ps.executeQuery();

            while (rs.next()) {
                teachList.add(new BorrowTeach(
                        rs.getInt("idborrow_teach"), // Assuming your BorrowTeach constructor now takes the ID
                        rs.getString("Full_Name"),
                        rs.getString("Designation"),
                        rs.getString("Acc_No"),
                        rs.getString("Title"),
                        rs.getString("Author"),
                        rs.getString("Category"),
                        rs.getString("Date")
                ));
            }

            Teach_Add_Table.setItems(teachList);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Borrow_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
        }
    }

    @FXML
    public void selectTeacherRow() {
        int row = Teach_Add_Table.getSelectionModel().getSelectedIndex();
        if (row != -1) {
            BorrowTeach selectedBorrowTeach = Teach_Add_Table.getItems().get(row);

            // Populate TextFields based on the selected row
            FName_Teach_Borr_txtf.setText(selectedBorrowTeach.getFull_Name());
            Designation_Teach_Borr_txtf.setText(selectedBorrowTeach.getDesignation());
            AccNo_book_Teach_Borr_txtf.setText(selectedBorrowTeach.getAcc_No());
            Title_book_Teach_Borr_txtf.setText(selectedBorrowTeach.getTitle());
            Author_book_Teach_Borr_txtf.setText(selectedBorrowTeach.getAuthor());
            Category_book_Teach_Borr_txtf.setText(selectedBorrowTeach.getCategory());
            Date_Teach_Borr_txtf.setText(selectedBorrowTeach.getDate());

            // Update the selectedBorrowTeach variable
            selectedBorrowTeach = selectedBorrowTeach; // Assign the selected item
            checkTeachTextFieldsForUpdateButton(); // Enable/disable update button
            Delete_btn_teach_User.setDisable(false); // Enable delete button
        }
    }
    
    @FXML
    public void loadtableSTUD() {
        loadBorrStud();
    }
    
    @FXML
    public void loadtableTEACH() {
        loadBorrTeach();
    }

}
