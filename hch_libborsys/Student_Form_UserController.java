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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class Student_Form_UserController implements Initializable {

    private int selectedStudentId = -1;

    private TextField Acc_No_txtf_Stud_User;
    @FXML
    private AnchorPane Stud_User_Main_Anc;
    @FXML
    private TableColumn<Student, String> Full_Name;
    @FXML
    private TableColumn<Student, String> Section;
    @FXML
    private TableColumn<Student, String> Student_ID;

    private ObservableList<Student> studentList = FXCollections.observableArrayList();
    @FXML
    private TableView<Student> Student_Add_Table;
    @FXML
    private TextField FullName_Stud_Add_User;
    @FXML
    private TextField Section_Stud_Add_User;
    @FXML
    private TextField YearLvl_Stud_Add_User;
    @FXML
    private TextField StudentID_Stud_Add_User;
    @FXML
    private Button Add_btn_User;
    @FXML
    private Button Update_btn_User;
    @FXML
    private Button Delete;
    @FXML
    private Button ClearAll_btn_User;
    @FXML
    private TableColumn<Student, String> Year_Lvl;
    @FXML
    private AnchorPane Student_anc_add;
    @FXML
    private Button close1;
    @FXML
    private TextField Search_Stud_txtf;
    @FXML
    private Button Student_refresh_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Full_Name.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));
        Section.setCellValueFactory(new PropertyValueFactory<>("Section"));
        Student_ID.setCellValueFactory(new PropertyValueFactory<>("Student_ID"));
        Year_Lvl.setCellValueFactory(new PropertyValueFactory<>("Year_Lvl"));
        loadStudentData();

        Search_Stud_txtf.textProperty().addListener((observable, oldValue, newValue) -> {
            searchStudentData(newValue);
        });
        
        Delete.setDisable(true);
        Update_btn_User.setDisable(true);

        Student_Add_Table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedStudentId = newSelection.getIdStudent();
                populateTextFields(newSelection);
                checkTextFieldsForUpdateButton();
                Delete.setDisable(false);
            } else {
                selectedStudentId = -1;
                CLR_BTN_STD();
                Update_btn_User.setDisable(true);
                Delete.setDisable(true);
            }
        });

        FullName_Stud_Add_User.textProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());
        Section_Stud_Add_User.textProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());
        YearLvl_Stud_Add_User.textProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());
        StudentID_Stud_Add_User.textProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());

        FullName_Stud_Add_User.setPromptText("Full Name");
        Section_Stud_Add_User.setPromptText("Section");
        YearLvl_Stud_Add_User.setPromptText("Year Level");
        StudentID_Stud_Add_User.setPromptText("Student ID");

        Full_Name.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));
        Section.setCellValueFactory(new PropertyValueFactory<>("Section"));
        Student_ID.setCellValueFactory(new PropertyValueFactory<>("Student_ID"));
        Year_Lvl.setCellValueFactory(new PropertyValueFactory<>("Year_Lvl"));
        loadStudentData();

    }

    private void populateTextFields(Student student) {
        FullName_Stud_Add_User.setText(student.getFull_Name());
        Section_Stud_Add_User.setText(student.getSection());
        YearLvl_Stud_Add_User.setText(student.getYear_Lvl());
        StudentID_Stud_Add_User.setText(student.getStudent_ID());
    }

    private void checkTextFieldsForUpdateButton() {
        boolean allFieldsFilled = !FullName_Stud_Add_User.getText().isEmpty()
                && !Section_Stud_Add_User.getText().isEmpty()
                && !YearLvl_Stud_Add_User.getText().isEmpty()
                && !StudentID_Stud_Add_User.getText().isEmpty();
        Update_btn_User.setDisable(selectedStudentId == -1 || !allFieldsFilled);
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    public void close() {
        Stage stage = (Stage) Stud_User_Main_Anc.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void Add_std_btn(ActionEvent event) {
       if (selectedStudentId != -1) {
            showAlert(Alert.AlertType.WARNING, "Add Warning", "Please clear the selection or fields to add a new student.");
            return;
        }

        String fullName = FullName_Stud_Add_User.getText().trim();
        String section = Section_Stud_Add_User.getText().trim();
        String yearLvl = YearLvl_Stud_Add_User.getText().trim();
        String studentID = StudentID_Stud_Add_User.getText().trim();

        if (fullName.isEmpty() || section.isEmpty() || yearLvl.isEmpty() || studentID.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill out all fields to add a new student.");
            return;
        }

        try {
            DB_connection.init();
            try (Connection c = DB_connection.getConnection();
                 PreparedStatement checkPs = c.prepareStatement("SELECT Full_Name, Section, Year_Lvl, Student_ID FROM libborsys.student WHERE Full_Name = ? AND Section = ? AND Year_Lvl = ? AND Student_ID = ?")) {

                checkPs.setString(1, fullName);
                checkPs.setString(2, section);
                checkPs.setString(3, yearLvl);
                checkPs.setString(4, studentID);
                ResultSet rs = checkPs.executeQuery();

                if (rs.next()) {
                    showAlert(Alert.AlertType.ERROR, "Add Error", "A student with the Full Name '" + fullName + "', Section '" + section + "', Year Level '" + yearLvl + "', and Student ID '" + studentID + "' already exists.");
                    return; // Prevent insertion
                }

                // If student doesn't exist, proceed with insertion
                try (PreparedStatement insertPs = c.prepareStatement("INSERT INTO libborsys.student (Full_Name, Section, Year_Lvl, Student_ID) VALUES (?, ?, ?, ?)")) {
                    insertPs.setString(1, fullName);
                    insertPs.setString(2, section);
                    insertPs.setString(3, yearLvl);
                    insertPs.setString(4, studentID);

                    if (!insertPs.execute()) {
                        showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successfully Added!");
                        loadStudentData();
                        CLR_BTN_STD();
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error adding student: " + ex.getMessage());
        }
    }

    @FXML
    public void updateStudent(ActionEvent event) {
        if(selectedStudentId == -1) {
            showAlert(Alert.AlertType.WARNING, "Update Warning", "Please select a student to update.");
            return;
        }

        if (FullName_Stud_Add_User.getText().isEmpty() || Section_Stud_Add_User.getText().isEmpty()
                || YearLvl_Stud_Add_User.getText().isEmpty() || StudentID_Stud_Add_User.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Update Warning", "Please fill in all fields to update.");
            return;
        }

        try {
            DB_connection.init();
            Connection c = DB_connection.getConnection();
            PreparedStatement ps;

            String sql = "UPDATE libborsys.student SET Full_Name = ?, Section = ?, Year_Lvl = ?, Student_ID = ? WHERE idStudent = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, FullName_Stud_Add_User.getText());
            ps.setString(2, Section_Stud_Add_User.getText());
            ps.setString(3, YearLvl_Stud_Add_User.getText());
            ps.setString(4, StudentID_Stud_Add_User.getText());
            ps.setInt(5, selectedStudentId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Update Successful", "Student record updated successfully!");
                loadStudentData();
                CLR_BTN_STD();
                selectedStudentId = -1;
                Update_btn_User.setDisable(true);
                Delete.setDisable(true);
                Student_Add_Table.getSelectionModel().clearSelection();
            } else {
                showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update student record.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error updating student record: " + ex.getMessage());
        }
    }

    @FXML
    public void deleteStudent(ActionEvent event) {
        if (selectedStudentId == -1) {
            showAlert(Alert.AlertType.WARNING, "Delete Warning", "Please select a student to delete.");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Delete");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you want to delete this student?");
        confirmation.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK")) {
                try {
                    DB_connection.init();
                    Connection c = DB_connection.getConnection();
                    PreparedStatement ps;

                    String sql = "DELETE FROM libborsys.student WHERE idStudent = ?";
                    ps = c.prepareStatement(sql);
                    ps.setInt(1, selectedStudentId);

                    int rowsDeleted = ps.executeUpdate();

                    if (rowsDeleted > 0) {
                        showAlert(Alert.AlertType.INFORMATION, "Delete Successful", "Student record deleted successfully!");
                        loadStudentData();
                        CLR_BTN_STD();
                        selectedStudentId = -1;
                        Update_btn_User.setDisable(true);
                        Delete.setDisable(true);
                        Student_Add_Table.getSelectionModel().clearSelection();
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Delete Failed", "Failed to delete student record.");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, null, ex);
                    showAlert(Alert.AlertType.ERROR, "Database Error", "Error deleting student record: " + ex.getMessage());
                }
            }
        });
    }

    @FXML
    public void CLR_BTN_STD() {
        FullName_Stud_Add_User.clear();
        Section_Stud_Add_User.clear();
        YearLvl_Stud_Add_User.clear();
        StudentID_Stud_Add_User.clear();
        selectedStudentId = -1;
        Update_btn_User.setDisable(true);
        Delete.setDisable(true);
        Student_Add_Table.getSelectionModel().clearSelection();
    }

    @FXML
    public void selectStudentRow() {
        int row = Student_Add_Table.getSelectionModel().getSelectedIndex();
        if (row != -1) {
            Student selectedStudent = Student_Add_Table.getItems().get(row);

            FullName_Stud_Add_User.setText(selectedStudent.getFull_Name());
            Section_Stud_Add_User.setText(selectedStudent.getSection());
            YearLvl_Stud_Add_User.setText(selectedStudent.getYear_Lvl());
            StudentID_Stud_Add_User.setText(selectedStudent.getStudent_ID());
            selectedStudentId = selectedStudent.getIdStudent();
            Delete.setDisable(false);
            Update_btn_User.setDisable(false);
        } else {
            selectedStudentId = -1;
            Delete.setDisable(true);
            Update_btn_User.setDisable(true);
        }
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

            Student_Add_Table.setItems(filteredList); // Set the table with filtered data
            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error searching student data: " + ex.getMessage()); //show error
        }
    }
    
    @FXML
    public void refresh() {
        loadStudentData();
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

            Student_Add_Table.setItems(studentList);

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
        }
    }

    @FXML
    private void refresh(ActionEvent event) {
    }

}
