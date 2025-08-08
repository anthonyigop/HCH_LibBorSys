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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class TableViewUserController implements Initializable {

    @FXML
    private Button close;
   
    private ObservableList<ViewTable> bookList = FXCollections.observableArrayList();
    private FilteredList<ViewTable> filteredBookList;
    @FXML
    private TableColumn<ViewTable, String> Title;
    @FXML
    private TableColumn<ViewTable, String> Acc_No;
    @FXML
    private TableColumn<ViewTable, String> Author;
    @FXML
    private TextField Search_Borr_Teach_txtf;
    @FXML
    private AnchorPane ViewBook_Anc_Main;
    @FXML
    private TableColumn<ViewTable, String> Category;
    @FXML
    private TableColumn<ViewTable, String> Status;
    @FXML
    private TableView<ViewTable> Student_Add_Table_View;

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

        // Initialize FilteredList
        filteredBookList = new FilteredList<>(bookList, p -> true);

        // Set the filter Predicate whenever the filter changes.
        Search_Borr_Teach_txtf.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredBookList.setPredicate(book -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare every field with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (book.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches title.
                } else if (book.getAcc_No().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches Acc_No.
                } else if (book.getAuthor().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches author.
                } else if (book.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches category.
                } else if (book.getStatus().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches status.
                }

                return false; // Does not match.
            });
        });

        // Wrap the FilteredList in a SortedList.
        SortedList<ViewTable> sortedBookList = new SortedList<>(filteredBookList);

        // Bind the SortedList comparator to the TableView comparator.
        sortedBookList.comparatorProperty().bind(Student_Add_Table_View.comparatorProperty());

        // Add sorted (and filtered) data to the table.
        Student_Add_Table_View.setItems(sortedBookList);
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

            c.close();

        } catch (SQLException ex) {
            Logger.getLogger(Student_Form_UserController.class.getName()).log(Level.SEVERE, "Database error: ", ex);
        }
    }
     
    @FXML
    public void close() {
        Stage stage = (Stage) ViewBook_Anc_Main.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void closeTableViewUser() {
        Stage stage = (Stage) ViewBook_Anc_Main.getScene().getWindow();
        stage.close();
    }

}
