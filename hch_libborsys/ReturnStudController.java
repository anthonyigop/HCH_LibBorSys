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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class ReturnStudController implements Initializable {

    @FXML
    private Button close;
    @FXML
    private TextField AccNo_book_Stud_Ret_txtf;
    @FXML
    private TextField Title_book_Stud_Ret_txtf;
    @FXML
    private TextField Author_book_Stud_Ret_txtf;
    @FXML
    private TextField Category_book_Stud_Ret_txtf;
    @FXML
    private TextField StudentID_Stud_Ret_txtf;
    @FXML
    private TextField FName_Stud_Ret_txtf;
    @FXML
    private TextField Section_Stud_Ret_txtf;
    @FXML
    private TextField YearLvl_Stud_Ret_txtf;
    @FXML
    private TextField Date_Stud_Ret_txtf;
    @FXML
    private TextField DueDate_Stud_Ret_txtf;
    @FXML
    private TextField DateElapsed_Stud_Ret_txtf;
    @FXML
    private Button Add_btn_stud_User;
    @FXML
    private AnchorPane MAIN_ANC;
    @FXML
    private Button bck_btn;
    @FXML
    private AnchorPane Stud_Ret_Anc;
    @FXML
    private Button Search_btn_AccNo;
    @FXML
    private Button Search_btn_AccNo1;
    @FXML
    private Button Clear_all;
    @FXML
    private AnchorPane Teach_Ret_Anc;
    @FXML
    private TextField AccNo_book_Teach_Ret_txtf;
    @FXML
    private TextField Title_book_Teach_Ret_txtf;
    @FXML
    private TextField Author_book_Teach_Ret_txtf;
    @FXML
    private TextField Category_book_Teach_Ret_txtf;
    @FXML
    private TextField FName_Teach_Ret_txtf;
    @FXML
    private TextField Designation_Teach_Ret_txtf;
    @FXML
    private TextField Date_Teach_Ret_txtf;
    @FXML
    private Button Add_btn_stud_User1;
    @FXML
    private Button Clear_all1;
    @FXML
    private AnchorPane Ret_StudTeach_User_Anc;
    @FXML
    private Button Stud_btn;
    @FXML
    private Button Teach_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void backbtn(ActionEvent event) {
        if (event.getSource() == bck_btn) {
            Ret_StudTeach_User_Anc.setVisible(true);
            Stud_Ret_Anc.setVisible(false);
            Teach_Ret_Anc.setVisible(false);
        }
    }

    @FXML
    private void viewStudRet(ActionEvent event) {
        if (event.getSource() == Stud_btn) {
            Ret_StudTeach_User_Anc.setVisible(false);
            Stud_Ret_Anc.setVisible(true);
            Teach_Ret_Anc.setVisible(false);
        }
    }

    @FXML
    private void viewTeachRet(ActionEvent event) {
        if (event.getSource() == Teach_btn) {
            Ret_StudTeach_User_Anc.setVisible(false);
            Stud_Ret_Anc.setVisible(false);
            Teach_Ret_Anc.setVisible(true);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void Return_std_btn(ActionEvent event) {
        if (AccNo_book_Stud_Ret_txtf.getText().isEmpty() || Title_book_Stud_Ret_txtf.getText().isEmpty() || Author_book_Stud_Ret_txtf.getText().isEmpty() || Category_book_Stud_Ret_txtf.getText().isEmpty()
                || StudentID_Stud_Ret_txtf.getText().isEmpty() || FName_Stud_Ret_txtf.getText().isEmpty() || Section_Stud_Ret_txtf.getText().isEmpty() || YearLvl_Stud_Ret_txtf.getText().isEmpty()
                || Date_Stud_Ret_txtf.getText().isEmpty() || DueDate_Stud_Ret_txtf.getText().isEmpty() || DateElapsed_Stud_Ret_txtf.getText().isEmpty()) {
            showAlert("Input Error", "All fields must be filled out.", AlertType.ERROR);
            return;
        }

        Connection connection = null;
        PreparedStatement insertStmt = null;
        PreparedStatement deleteStmt = null;
        PreparedStatement updateInventoryStmt = null;

        try {
            DB_connection.init();
            connection = DB_connection.getConnection();
            connection.setAutoCommit(false);

            String accNo = AccNo_book_Stud_Ret_txtf.getText();

            // 1. Insert the return record
            String insertSql = "INSERT INTO libborsys.return_stud (Full_Name, Section, Year_Level, StudentID, Acc_No, Title, Author, Category, Date, Due_Date, Days_Elapsed) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            insertStmt = connection.prepareStatement(insertSql);
            insertStmt.setString(1, FName_Stud_Ret_txtf.getText());
            insertStmt.setString(2, Section_Stud_Ret_txtf.getText());
            insertStmt.setString(3, YearLvl_Stud_Ret_txtf.getText());
            insertStmt.setString(4, StudentID_Stud_Ret_txtf.getText());
            insertStmt.setString(5, accNo);
            insertStmt.setString(6, Title_book_Stud_Ret_txtf.getText());
            insertStmt.setString(7, Author_book_Stud_Ret_txtf.getText());
            insertStmt.setString(8, Category_book_Stud_Ret_txtf.getText());
            insertStmt.setString(9, Date_Stud_Ret_txtf.getText());
            insertStmt.setString(10, DueDate_Stud_Ret_txtf.getText());
            insertStmt.setString(11, DateElapsed_Stud_Ret_txtf.getText());
            insertStmt.executeUpdate();

            // 2. Delete the borrowing record
            String deleteSql = "DELETE FROM libborsys.borrow_stud WHERE Acc_No = ?";
            deleteStmt = connection.prepareStatement(deleteSql);
            deleteStmt.setString(1, accNo);
            int rowsDeleted = deleteStmt.executeUpdate();

            // 3. Update the inventory status to "Available"
            String updateInventorySql = "UPDATE libborsys.inventory SET Status = 'Available' WHERE Acc_No = ?";
            updateInventoryStmt = connection.prepareStatement(updateInventorySql);
            updateInventoryStmt.setString(1, accNo);
            int rowsUpdated = updateInventoryStmt.executeUpdate();

            connection.commit();

            if (rowsDeleted > 0 && rowsUpdated > 0) {
                showAlert("Success", "Book successfully returned and inventory updated!", AlertType.INFORMATION);
                clearReturnFields();
            } else {
                showAlert("Error", "Return process incomplete. Failed to update inventory or delete borrow record.", AlertType.ERROR);
                connection.rollback();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReturnStudController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert("Database Error", "An error occurred during the return process: " + ex.getMessage(), AlertType.ERROR);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    Logger.getLogger(ReturnStudController.class.getName()).log(Level.SEVERE, "Error rolling back transaction", rollbackEx);
                    showAlert("Database Error", "Error rolling back transaction: " + rollbackEx.getMessage(), AlertType.ERROR);
                }
            }
        } finally {
            try {
                if (insertStmt != null) {
                    insertStmt.close();
                }
                if (deleteStmt != null) {
                    deleteStmt.close();
                }
                if (updateInventoryStmt != null) {
                    updateInventoryStmt.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException closeEx) {
                Logger.getLogger(ReturnStudController.class.getName()).log(Level.SEVERE, "Error closing database resources", closeEx);
                showAlert("Database Error", "Error closing database resources: " + closeEx.getMessage(), AlertType.ERROR);
            }
        }
    }

    @FXML
    public void Return_teach_btn(ActionEvent event) {
        if (AccNo_book_Teach_Ret_txtf.getText().isEmpty() || Title_book_Teach_Ret_txtf.getText().isEmpty() || Author_book_Teach_Ret_txtf.getText().isEmpty() || Category_book_Teach_Ret_txtf.getText().isEmpty()
                || FName_Teach_Ret_txtf.getText().isEmpty() || Designation_Teach_Ret_txtf.getText().isEmpty() || Date_Teach_Ret_txtf.getText().isEmpty()) {
            showAlert("Input Error", "All fields must be filled out.", AlertType.ERROR);
            return;
        }

        Connection connection = null;
        PreparedStatement insertStmt = null;
        PreparedStatement deleteStmt = null;
        PreparedStatement updateInventoryStmt = null;

        try {
            DB_connection.init();
            connection = DB_connection.getConnection();
            connection.setAutoCommit(false);

            String accNo = AccNo_book_Teach_Ret_txtf.getText();

            // 1. Insert the return record
            String insertSql = "INSERT INTO libborsys.return_teach (Full_Name, Designation, Acc_No, Title, Author, Category, Date) VALUES (?, ?, ?, ?, ?, ?, ?)";
            insertStmt = connection.prepareStatement(insertSql);
            insertStmt.setString(1, FName_Teach_Ret_txtf.getText());
            insertStmt.setString(2, Designation_Teach_Ret_txtf.getText());
            insertStmt.setString(3, accNo);
            insertStmt.setString(4, Title_book_Teach_Ret_txtf.getText());
            insertStmt.setString(5, Author_book_Teach_Ret_txtf.getText());
            insertStmt.setString(6, Category_book_Teach_Ret_txtf.getText());
            insertStmt.setString(7, Date_Teach_Ret_txtf.getText());
            insertStmt.executeUpdate();

            // 2. Delete the borrowing record
            String deleteSql = "DELETE FROM libborsys.borrow_teach WHERE Acc_No = ?";
            deleteStmt = connection.prepareStatement(deleteSql);
            deleteStmt.setString(1, accNo);
            int rowsDeleted = deleteStmt.executeUpdate();

            // 3. Update the inventory status to "Available"
            String updateInventorySql = "UPDATE libborsys.inventory SET Status = 'Available' WHERE Acc_No = ?";
            updateInventoryStmt = connection.prepareStatement(updateInventorySql);
            updateInventoryStmt.setString(1, accNo);
            int rowsUpdated = updateInventoryStmt.executeUpdate();

            connection.commit();

            if (rowsDeleted > 0 && rowsUpdated > 0) {
                showAlert("Success", "Book successfully returned and inventory updated!", AlertType.INFORMATION);
                clearTeacherReturnFields();
            } else {
                showAlert("Error", "Return process incomplete. Failed to update inventory or delete borrow record.", AlertType.ERROR);
                connection.rollback();
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReturnStudController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert("Database Error", "An error occurred during the return process: " + ex.getMessage(), AlertType.ERROR);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    Logger.getLogger(ReturnStudController.class.getName()).log(Level.SEVERE, "Error rolling back transaction", rollbackEx);
                    showAlert("Database Error", "Error rolling back transaction: " + rollbackEx.getMessage(), AlertType.ERROR);
                }
            }
        } finally {
            try {
                if (insertStmt != null) {
                    insertStmt.close();
                }
                if (deleteStmt != null) {
                    deleteStmt.close();
                }
                if (updateInventoryStmt != null) {
                    updateInventoryStmt.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException closeEx) {
                Logger.getLogger(ReturnStudController.class.getName()).log(Level.SEVERE, "Error closing database resources", closeEx);
                showAlert("Database Error", "Error closing database resources: " + closeEx.getMessage(), AlertType.ERROR);
            }
        }
    }

    @FXML
    private void clearReturnFields() {
        AccNo_book_Stud_Ret_txtf.clear();
        Title_book_Stud_Ret_txtf.clear();
        Author_book_Stud_Ret_txtf.clear();
        Category_book_Stud_Ret_txtf.clear();
        StudentID_Stud_Ret_txtf.clear();
        FName_Stud_Ret_txtf.clear();
        Section_Stud_Ret_txtf.clear();
        YearLvl_Stud_Ret_txtf.clear();
        Date_Stud_Ret_txtf.clear();
        DueDate_Stud_Ret_txtf.clear();
        DateElapsed_Stud_Ret_txtf.clear();
    }

    @FXML
    private void clearTeacherReturnFields() {
        FName_Teach_Ret_txtf.clear();
        Designation_Teach_Ret_txtf.clear();
        AccNo_book_Teach_Ret_txtf.clear();
        Title_book_Teach_Ret_txtf.clear();
        Author_book_Teach_Ret_txtf.clear();
        Category_book_Teach_Ret_txtf.clear();
        Date_Teach_Ret_txtf.clear();
    }

    @FXML
    private void loadStudentDataByID(ActionEvent event) {
        String accno = AccNo_book_Stud_Ret_txtf.getText().trim();

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

            String sql = "SELECT Full_Name, Section, Year_Level, StudentID, Acc_No, Title, Author, Category, Date, Due_Date  FROM libborsys.borrow_stud WHERE Acc_No = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, accno);
            rs = ps.executeQuery();

            if (rs.next()) {
                Title_book_Stud_Ret_txtf.setText(rs.getString("Title"));
                Author_book_Stud_Ret_txtf.setText(rs.getString("Author"));
                Category_book_Stud_Ret_txtf.setText(rs.getString("Category"));
                StudentID_Stud_Ret_txtf.setText(rs.getString("StudentID"));
                FName_Stud_Ret_txtf.setText(rs.getString("Full_Name"));
                Section_Stud_Ret_txtf.setText(rs.getString("Section"));
                YearLvl_Stud_Ret_txtf.setText(rs.getString("Year_Level"));
                Date_Stud_Ret_txtf.setText(rs.getString("Date"));
                DueDate_Stud_Ret_txtf.setText(rs.getString("Due_Date"));

                LocalDate dueDate = LocalDate.parse(rs.getString("Due_Date"));
                LocalDate today = LocalDate.now();
                long daysElapsed = ChronoUnit.DAYS.between(dueDate, today);

                if (daysElapsed > 0) {
                    DateElapsed_Stud_Ret_txtf.setText(Long.toString(daysElapsed));
                } else {
                    DateElapsed_Stud_Ret_txtf.setText("0");
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("No student found with Acc #: " + accno);
                alert.showAndWait();
                Title_book_Stud_Ret_txtf.setText(rs.getString("Title"));
                Author_book_Stud_Ret_txtf.setText(rs.getString("Author"));
                Category_book_Stud_Ret_txtf.setText(rs.getString("Category"));
                StudentID_Stud_Ret_txtf.setText(rs.getString("StudentID"));
                FName_Stud_Ret_txtf.setText(rs.getString("Full_Name"));
                Section_Stud_Ret_txtf.setText(rs.getString("Section"));
                YearLvl_Stud_Ret_txtf.setText(rs.getString("Year_Level"));
                Date_Stud_Ret_txtf.setText(rs.getString("Date"));
                DueDate_Stud_Ret_txtf.setText(rs.getString("Due_Date"));
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while accessing the database.");
            alert.showAndWait();
        }
    }

    @FXML
    private void loadStudentDataByID_2(ActionEvent event) {
        String studid = StudentID_Stud_Ret_txtf.getText().trim();

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

            String sql = "SELECT Full_Name, Section, Year_Level, StudentID, Acc_No, Title, Author, Category, Date, Due_Date  FROM libborsys.borrow_stud WHERE StudentID = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, studid);
            rs = ps.executeQuery();

            if (rs.next()) {
                AccNo_book_Stud_Ret_txtf.setText(rs.getString("Acc_No"));
                Title_book_Stud_Ret_txtf.setText(rs.getString("Title"));
                Author_book_Stud_Ret_txtf.setText(rs.getString("Author"));
                Category_book_Stud_Ret_txtf.setText(rs.getString("Category"));
                FName_Stud_Ret_txtf.setText(rs.getString("Full_Name"));
                Section_Stud_Ret_txtf.setText(rs.getString("Section"));
                YearLvl_Stud_Ret_txtf.setText(rs.getString("Year_Level"));
                Date_Stud_Ret_txtf.setText(rs.getString("Date"));
                DueDate_Stud_Ret_txtf.setText(rs.getString("Due_Date"));

                LocalDate dueDate = LocalDate.parse(rs.getString("Due_Date"));
                LocalDate today = LocalDate.now();
                long daysElapsed = ChronoUnit.DAYS.between(dueDate, today);

                if (daysElapsed > 0) {
                    DateElapsed_Stud_Ret_txtf.setText(Long.toString(daysElapsed));
                } else {
                    DateElapsed_Stud_Ret_txtf.setText("0");
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("No student found with ID: " + studid);
                alert.showAndWait();
                AccNo_book_Stud_Ret_txtf.setText(rs.getString("Acc_No"));
                Title_book_Stud_Ret_txtf.setText(rs.getString("Title"));
                Author_book_Stud_Ret_txtf.setText(rs.getString("Author"));
                Category_book_Stud_Ret_txtf.setText(rs.getString("Category"));
                FName_Stud_Ret_txtf.setText(rs.getString("Full_Name"));
                Section_Stud_Ret_txtf.setText(rs.getString("Section"));
                YearLvl_Stud_Ret_txtf.setText(rs.getString("Year_Level"));
                Date_Stud_Ret_txtf.setText(rs.getString("Date"));
                DueDate_Stud_Ret_txtf.setText(rs.getString("Due_Date"));
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while accessing the database.");
            alert.showAndWait();
        }
    }

    @FXML
    private void loadTeacherDataByID(ActionEvent event) {
        String accnoT = AccNo_book_Teach_Ret_txtf.getText().trim();

        if (accnoT.isEmpty()) {
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

            String sql = "SELECT Full_Name, Designation, Acc_No, Title, Author, Category, Date FROM libborsys.borrow_teach WHERE Acc_No = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, accnoT);
            rs = ps.executeQuery();

            if (rs.next()) {

                FName_Teach_Ret_txtf.setText(rs.getString("Full_Name"));
                Designation_Teach_Ret_txtf.setText(rs.getString("Designation"));
                AccNo_book_Teach_Ret_txtf.setText(rs.getString("Acc_No"));
                Title_book_Teach_Ret_txtf.setText(rs.getString("Title"));
                Author_book_Teach_Ret_txtf.setText(rs.getString("Author"));
                Category_book_Teach_Ret_txtf.setText(rs.getString("Category"));
                Date_Teach_Ret_txtf.setText(rs.getString("Date"));

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("No Teacher found with Acc #: " + accnoT);
                alert.showAndWait();
                FName_Teach_Ret_txtf.setText(rs.getString("Full_Name"));
                Designation_Teach_Ret_txtf.setText(rs.getString("Designation"));
                AccNo_book_Teach_Ret_txtf.setText(rs.getString("Acc_No"));
                Title_book_Teach_Ret_txtf.setText(rs.getString("Title"));
                Author_book_Teach_Ret_txtf.setText(rs.getString("Author"));
                Category_book_Teach_Ret_txtf.setText(rs.getString("Category"));
                Date_Teach_Ret_txtf.setText(rs.getString("Date"));
            }

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Database Error");
            alert.setHeaderText(null);
            alert.setContentText("An error occurred while accessing the database.");
            alert.showAndWait();
        }
    }

    @FXML
    public void close() {
        Stage stage = (Stage) MAIN_ANC.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String no_Record, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
