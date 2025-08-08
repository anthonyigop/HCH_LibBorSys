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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class AddBookAdminController implements Initializable {

    private int selectedBookId = -1;
    @FXML
    private TextField Title_txtf;
    @FXML
    private TextField AccNo_txtf;
    @FXML
    private TextField Author_txtf;
    @FXML
    private TextField Category_txtf;
    @FXML
    private Button Add_btn_User;
    @FXML
    private Button Update_btn_User;
    @FXML
    private Button Delete;
    @FXML
    private Button ClearAll_btn_User;
    @FXML
    private ComboBox<String> Status_txtf;
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
    private AnchorPane MainAnc;
    @FXML
    private Button close1;
    @FXML
    private TextField Search_Book_txtf;
    @FXML
    private Button Book_refresh_btn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Acc_No.setCellValueFactory(new PropertyValueFactory<>("Acc_No"));
        Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        loadBookData();

        Search_Book_txtf.textProperty().addListener((observable, oldValue, newValue) -> {
            searchBookData(newValue);
        });

        Delete.setDisable(true);
        Update_btn_User.setDisable(true);

        Books_Table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                selectedBookId = newSelection.getIdInventory();
                populateTextFields(newSelection);
                checkTextFieldsForUpdateButton();
                Delete.setDisable(false);
            } else {
                selectedBookId = -1;
                CLR_BTN_STD();
                Update_btn_User.setDisable(true);
                Delete.setDisable(true);
            }
        });

        Title_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());
        AccNo_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());
        Author_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());
        Category_txtf.textProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());
        Status_txtf.valueProperty().addListener((observable, oldValue, newValue) -> checkTextFieldsForUpdateButton());

        Title_txtf.setPromptText("Title");
        AccNo_txtf.setPromptText("Accession No");
        Author_txtf.setPromptText("Author");
        Category_txtf.setPromptText("Category");
        Status_txtf.setPromptText("Status");

        Title.setCellValueFactory(new PropertyValueFactory<>("Title"));
        Acc_No.setCellValueFactory(new PropertyValueFactory<>("Acc_No"));
        Author.setCellValueFactory(new PropertyValueFactory<>("Author"));
        Category.setCellValueFactory(new PropertyValueFactory<>("Category"));
        Status.setCellValueFactory(new PropertyValueFactory<>("Status"));
        loadBookData();

        Status_txtf.getItems().addAll("Available", "Unavailable");
        Update_btn_User.setDisable(true);
        Delete.setDisable(true);
    }

    private void populateTextFields(ViewTable book) {
        Title_txtf.setText(book.getTitle());
        AccNo_txtf.setText(book.getAcc_No());
        Author_txtf.setText(book.getAuthor());
        Category_txtf.setText(book.getCategory());
        Status_txtf.setValue(book.getStatus());
    }

    private void checkTextFieldsForUpdateButton() {
        boolean allFieldsFilled = !Title_txtf.getText().isEmpty()
                && !AccNo_txtf.getText().isEmpty()
                && !Author_txtf.getText().isEmpty()
                && !Category_txtf.getText().isEmpty()
                && Status_txtf.getValue() != null;
        Update_btn_User.setDisable(selectedBookId == -1 || !allFieldsFilled);
    }

    public void selectStudentRow() {
        int row = Books_Table.getSelectionModel().getSelectedIndex();
        if (row != -1) {
            ViewTable selectedBook = Books_Table.getItems().get(row);

            Title_txtf.setText(selectedBook.getTitle());
            AccNo_txtf.setText(selectedBook.getAcc_No());
            Author_txtf.setText(selectedBook.getAuthor());
            Category_txtf.setText(selectedBook.getCategory());
            Status_txtf.setValue(selectedBook.getStatus());
            selectedBookId = selectedBook.getIdInventory();
            Delete.setDisable(false);
            Update_btn_User.setDisable(false);
        } else {
            selectedBookId = -1;
            Delete.setDisable(true);
            Update_btn_User.setDisable(true);
        }
    }

    @FXML
    public void Add_std_btn(ActionEvent event) {
        if (selectedBookId != -1) {
            showAlert(Alert.AlertType.WARNING, "Add Warning", "Please clear the selection or fields to add a new student.");
            return;
        }

        String title = Title_txtf.getText().trim();
        String acc_no = AccNo_txtf.getText().trim();
        String author = Author_txtf.getText().trim();
        String category = Category_txtf.getText().trim();
        String status = Status_txtf.getValue();

        if (title.isEmpty() || acc_no.isEmpty() || author.isEmpty() || category.isEmpty() || status == null) {
            showAlert(Alert.AlertType.ERROR, "Input Error", "Please fill out all fields to add a new student.");
            return;
        }

        try {
            DB_connection.init();
            try (Connection c = DB_connection.getConnection(); PreparedStatement checkPs = c.prepareStatement("SELECT Title, Acc_No, Author, Category, Status FROM libborsys.inventory WHERE Title = ? AND Acc_No = ? AND Author = ? AND Category = ? AND Status = ?")) {

                checkPs.setString(1, title);
                checkPs.setString(2, acc_no);
                checkPs.setString(3, author);
                checkPs.setString(4, category);
                checkPs.setString(5, status);
                ResultSet rs = checkPs.executeQuery();

                if (rs.next()) {
                    showAlert(Alert.AlertType.ERROR, "Add Error", "A book with the Title '" + title + "', Accession No '" + acc_no + "', Author '" + author + "', and Category '" + category + "' already exists.");
                    return; // Prevent insertion
                }

                // If student doesn't exist, proceed with insertion
                try (PreparedStatement insertPs = c.prepareStatement("INSERT INTO libborsys.inventory (Title, Acc_No, Author, Category, Status) VALUES (?, ?, ?, ?, ?)")) {
                    insertPs.setString(1, title);
                    insertPs.setString(2, acc_no);
                    insertPs.setString(3, author);
                    insertPs.setString(4, category);
                    insertPs.setString(5, status);

                    if (!insertPs.execute()) {
                        showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successfully Added!");
                        loadBookData();
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
    private void updateStudent(ActionEvent event) {
        if (selectedBookId == -1) {
            showAlert(Alert.AlertType.WARNING, "Update Warning", "Please select a student to update.");
            return;
        }

        if (Title_txtf.getText().isEmpty() || AccNo_txtf.getText().isEmpty()
                || Author_txtf.getText().isEmpty() || Category_txtf.getText().isEmpty() || Status_txtf.getValue() == null) {
            showAlert(Alert.AlertType.WARNING, "Update Warning", "Please fill in all fields to update.");
            return;
        }

        try {
            DB_connection.init();
            Connection c = DB_connection.getConnection();
            PreparedStatement ps;

            String sql = "UPDATE libborsys.inventory SET Title = ?, Acc_No = ?, Author = ?, Category = ?, Status = ? WHERE idInventory = ?";
            ps = c.prepareStatement(sql);
            ps.setString(1, Title_txtf.getText());
            ps.setString(2, AccNo_txtf.getText());
            ps.setString(3, Author_txtf.getText());
            ps.setString(4, Category_txtf.getText());
            ps.setString(5, Status_txtf.getValue());
            ps.setInt(6, selectedBookId);

            int rowsUpdated = ps.executeUpdate();

            if (rowsUpdated > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Update Successful", "Student record updated successfully!");
                loadBookData();
                CLR_BTN_STD();
                selectedBookId = -1;
                Update_btn_User.setDisable(true);
                Delete.setDisable(true);
                Books_Table.getSelectionModel().clearSelection();
            } else {
                showAlert(Alert.AlertType.ERROR, "Update Failed", "Failed to update student record.");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, null, ex);
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error updating student record: " + ex.getMessage());
        }
    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        if (selectedBookId == -1) {
            showAlert(Alert.AlertType.WARNING, "Delete Warning", "Please select a book to delete.");
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Are you sure you want to delete this book?");
        alert.setContentText("Title: " + Title_txtf.getText() + "\nAcc_No: " + AccNo_txtf.getText());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                DB_connection.init();
                Connection c = DB_connection.getConnection();
                PreparedStatement ps;

                String sql = "DELETE FROM libborsys.inventory WHERE idInventory = ?"; // Assuming 'id' is the primary key
                ps = c.prepareStatement(sql);
                ps.setInt(1, selectedBookId);

                int rowsDeleted = ps.executeUpdate();

                if (rowsDeleted > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Delete Successful", "Book record deleted successfully!");
                    loadBookData();
                    CLR_BTN_STD();
                    selectedBookId = -1;
                    Update_btn_User.setDisable(true);
                    Delete.setDisable(true);
                    Books_Table.getSelectionModel().clearSelection();
                } else {
                    showAlert(Alert.AlertType.ERROR, "Delete Failed", "Failed to delete book record.");
                }

            } catch (SQLException ex) {
                Logger.getLogger(AddBookAdminController.class.getName()).log(Level.SEVERE, null, ex);  //changed class name
                showAlert(Alert.AlertType.ERROR, "Database Error", "Error deleting book record: " + ex.getMessage());
            }
        }
    }

    @FXML
    private void CLR_BTN_STD() {
        Title_txtf.clear();
        AccNo_txtf.clear();
        Author_txtf.clear();
        Category_txtf.clear();
        Status_txtf.setValue(null);
        selectedBookId = -1;
        Update_btn_User.setDisable(true);
        Delete.setDisable(true);
        Books_Table.getSelectionModel().clearSelection();
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
    
    @FXML
    public void refreshbooks() {
        loadBookData();
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

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    @FXML
    public void close() {
        Stage stage = (Stage) MainAnc.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void refreshbooks(ActionEvent event) {
    }
}
