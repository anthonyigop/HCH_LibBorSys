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
public class Teacherrr_Form_UserrController implements Initializable {

    @FXML
    private AnchorPane Teach_User_Main_Anc;
    @FXML
    private Button close;
    private TextField due_date_txtf_teach;
    private TextField fullname_txtf_teach;
    private TextField title_txtf_teach;
    private TextField date_borrowed_txtf_teach;
    private TextField Acc_num_txtf_teach;
    private TextField designation_txtf;
    private TableColumn<Teacher, String> Acc_No_teach;
    private TableColumn<Teacher, String> Full_Name_teach;
    @FXML
    private TableColumn<Teacher, String> Designation;
    private TableColumn<Teacher, String> Title_teach;
    private TableColumn<Teacher, String> Date_borrowed;
    private TableColumn<Teacher, String> Due_Date_teach;

    private ObservableList<Teacher> teacherList = FXCollections.observableArrayList();
    private TableView<Teacher> teach_table_user;
    @FXML
    private TextField FullName_Teach_Add_User;
    @FXML
    private TextField Designation_Teach_Add_User;
    @FXML
    private Button Add_btn_User;
    @FXML
    private Button Update_btn_User;
    @FXML
    private Button Delete;
    @FXML
    private Button ClearAll_btn_User;
    @FXML
    private TableView<Teacher> Teacher_Add_Table;
    @FXML
    private TableColumn<Teacher, String> Full_Name;

    private int selectedTeacherId = -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Delete.setDisable(true);
        Update_btn_User.setDisable(true);

        Teacher_Add_Table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedTeacherId = newSelection.getIdTeacher();
                populateTextFields(newSelection);
                checkTextFieldsForUpdateButton();
                Delete.setDisable(false);
            } else {
                selectedTeacherId = -1;
                CLR_BTN_TEACH();
                Update_btn_User.setDisable(true);
                Delete.setDisable(true);
            }
        });

        FullName_Teach_Add_User.textProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());
        Designation_Teach_Add_User.textProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());

        FullName_Teach_Add_User.setPromptText("Full Name");
        Designation_Teach_Add_User.setPromptText("Designation");

        Full_Name.setCellValueFactory(new PropertyValueFactory<>("Full_Name"));
        Designation.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        loadTeacherData();

    }

    private void populateTextFields(Teacher teacher) {
        FullName_Teach_Add_User.setText(teacher.getFull_Name());
        Designation_Teach_Add_User.setText(teacher.getDesignation());
    }

    private void checkTextFieldsForUpdateButton() {
        boolean allFieldsFilled = !FullName_Teach_Add_User.getText().isEmpty()
                && !Designation_Teach_Add_User.getText().isEmpty();
        Update_btn_User.setDisable(selectedTeacherId == -1 || !allFieldsFilled);
    }

    @FXML
    public void CLR_BTN_TEACH() {
        FullName_Teach_Add_User.clear();
        Designation_Teach_Add_User.clear();
        selectedTeacherId = -1;
        Update_btn_User.setDisable(true);
        Delete.setDisable(true);
        Teacher_Add_Table.getSelectionModel().clearSelection();
    }

    @FXML
    public void deleteTeacher(ActionEvent event) {
        if (selectedTeacherId == -1) {
            showAlert(Alert.AlertType.WARNING, "Delete Warning", "Please select a teacher to delete.");
            return;
        }

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION);
        confirmation.setTitle("Confirm Delete");
        confirmation.setHeaderText(null);
        confirmation.setContentText("Are you sure you want to delete this teacher?");
        confirmation.showAndWait().ifPresent(response -> {
            if (response.getText().equals("OK")) {
                try {
                    DB_connection.init();
                    Connection c = DB_connection.getConnection();
                    PreparedStatement ps;

                    String sql = "DELETE FROM libborsys.teacher WHERE idTeacher = ?";
                    ps = c.prepareStatement(sql);
                    ps.setInt(1, selectedTeacherId);

                    int rowsDeleted = ps.executeUpdate();

                    if (rowsDeleted > 0) {
                        showAlert(Alert.AlertType.INFORMATION, "Delete Successful", "Teacher record deleted successfully!");
                        loadTeacherData();
                        CLR_BTN_TEACH();
                        selectedTeacherId = -1;
                        Update_btn_User.setDisable(true);
                        Delete.setDisable(true);
                        Teacher_Add_Table.getSelectionModel().clearSelection();
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Delete Failed", "Failed to delete teacher record.");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Teacherrr_Form_UserrController.class.getName()).log(Level.SEVERE, null, ex);
                    showAlert(Alert.AlertType.ERROR, "Database Error", "Error deleting teacher record: " + ex.getMessage());
                }
            }
        });
    }

    @FXML
    public void updateTeacher(ActionEvent event) {
        if (selectedTeacherId == -1) {
            showAlert(Alert.AlertType.WARNING, "Update Warning", "Please select a teacher to update.");
            return;
        }

        if (FullName_Teach_Add_User.getText().isEmpty() || Designation_Teach_Add_User.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Update Warning", "Please fill in all fields to update.");
            return;
        }

        try {
            DB_connection.init();
            Connection c = DB_connection.getConnection();
            PreparedStatement ps;

            String sql = "UPDATE libborsys.teacher SET Full_Name = ?, Designation = ? WHERE idTeacher = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, FullName_Teach_Add_User.getText());
            ps.setString(2, Designation_Teach_Add_User.getText());
            ps.setInt(3, selectedTeacherId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Update Successful", "Teacher record updated successfully!");
                loadTeacherData();
                CLR_BTN_TEACH();
                selectedTeacherId = -1;
                Update_btn_User.setDisable(true);
                Delete.setDisable(true);
                Teacher_Add_Table.getSelectionModel().clearSelection();
            } else {
                showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update teacher record.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Teacherrr_Form_UserrController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error updating teacher record: " + ex.getMessage());
        }
    }

    @FXML
    public void Add_teach_btn(ActionEvent event) {
        if (selectedTeacherId != -1) {
            showAlert(Alert.AlertType.WARNING, "Add Warning", "Please clear the selection or fields to add a new teacher.");
            return;
        }

        String fullName = FullName_Teach_Add_User.getText().trim();
        String designation = Designation_Teach_Add_User.getText().trim();

        if (fullName.isEmpty() || designation.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill out all fields to add a new teacher.");
            return;
        }

        try {
            DB_connection.init();
            try (Connection c = DB_connection.getConnection();
                 PreparedStatement checkPs = c.prepareStatement("SELECT Full_Name, Designation FROM libborsys.teacher WHERE Full_Name = ? AND Designation = ?")) {

                checkPs.setString(1, fullName);
                checkPs.setString(2, designation);
                ResultSet rs = checkPs.executeQuery();

                if (rs.next()) {
                    showAlert(Alert.AlertType.ERROR, "Add Error", "A teacher with the Full Name '" + fullName + "' and Designation '" + designation + "' already exists.");
                    return; // Prevent insertion
                }

                // If teacher doesn't exist, proceed with insertion
                try (PreparedStatement insertPs = c.prepareStatement("INSERT INTO libborsys.teacher (Full_Name, Designation) VALUES (?, ?)")) {
                    insertPs.setString(1, fullName);
                    insertPs.setString(2, designation);

                    if (!insertPs.execute()) {
                        showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successfully Added!");
                        loadTeacherData();
                        CLR_BTN_TEACH();
                    }
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Teacherrr_Form_UserrController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error adding teacher: " + ex.getMessage());
        }
    }

    @FXML
    public void selectTeacherRow() {
        int row = Teacher_Add_Table.getSelectionModel().getSelectedIndex();
        if (row != -1) {
            Teacher selectedTeacher = Teacher_Add_Table.getItems().get(row);
            populateTextFields(selectedTeacher);
            selectedTeacherId = selectedTeacher.getIdTeacher();
            checkTextFieldsForUpdateButton();
            Delete.setDisable(false);
        } else {
            selectedTeacherId = -1;
            CLR_BTN_TEACH();
            Update_btn_User.setDisable(true);
            Delete.setDisable(true);
        }
    }

    @FXML
    public void close() {
        Stage stage = (Stage) Teach_User_Main_Anc.getScene().getWindow();
        stage.close();
    }

    void loadTeacherData() {
        DB_connection.init();
        Connection c = DB_connection.getConnection();
        PreparedStatement ps;
        ResultSet rs;
        teacherList.clear();

        try {
            ps = c.prepareStatement("SELECT idTeacher, Full_Name, Designation FROM libborsys.teacher");
            rs = ps.executeQuery();

            while (rs.next()) {
                teacherList.add(new Teacher(
                        rs.getInt("idTeacher"),
                        rs.getString("Full_Name"),
                        rs.getString("Designation")
                ));
            }
            Teacher_Add_Table.setItems(teacherList);
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(Teacherrr_Form_UserrController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error loading teacher data: " + ex.getMessage());
        }
    }
    
    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
