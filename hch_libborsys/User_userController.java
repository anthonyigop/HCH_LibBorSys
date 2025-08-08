/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package hch_libborsys;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class User_userController implements Initializable {

    @FXML
    private AnchorPane ultraform;
    private Button student_user;
    private Button teacher_add;
    @FXML
    private Button logout;

    private boolean studentMenuVisible = false;
    private boolean teacherMenuVisible = false;
    @FXML
    private Button add_book_S;
    @FXML
    private Button ret_book_S;
    @FXML
    private Button add_book_T;
    @FXML
    private Button ret_book_T;
    @FXML
    private VBox studentMenu;
    @FXML
    private VBox teacherMenu;
    private TableView<?> stud_table_user;
    private AnchorPane Student_anc_add;
    private AnchorPane Home_Page_Anc;
    private AnchorPane Stud_ANC;
    private AnchorPane Teacher_ANC;
    private AnchorPane Student_anc_return;
    private AnchorPane Teacher_anc_add;
    private AnchorPane Teacher_anc_return;
    @FXML
    private Button borrowbook_btn;
    @FXML
    private Button returnbook_btn;
    @FXML
    private Button viewbook_btn;
    
    private Stage studentStage = null;
    private Stage teacherStage = null;
    private Stage borrowBookStage = null;
    private Stage returnBookStage = null;
    private Stage viewBookStage = null;
    @FXML
    private AnchorPane bgimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        studentMenu.setVisible(false);
        teacherMenu.setVisible(false);

        add_book_S.setText("Add Book");
        ret_book_S.setText("Return Book");
        add_book_T.setText("Add Book");
        ret_book_T.setText("Return Book");
    }

    private void toggleStudentMenu() {
        studentMenuVisible = !studentMenuVisible;
        studentMenu.setVisible(studentMenuVisible);
        adjustButtonPositions();
    }

    private void toggleTeacherMenu() {
        teacherMenuVisible = !teacherMenuVisible;
        teacherMenu.setVisible(teacherMenuVisible);
        adjustButtonPositions();
    }

    private void StudentBtn() {
        Stud_ANC.setVisible(true);
        Teacher_ANC.setVisible(false);
        Home_Page_Anc.setVisible(false);
        Student_anc_add.setVisible(false);
        Student_anc_return.setVisible(false);
        Teacher_anc_add.setVisible(false);
        Teacher_anc_return.setVisible(false);

    }

    private void STUDBTN() throws IOException {
        if (studentStage != null && studentStage.isShowing()) {
            studentStage.toFront(); // Bring existing stage to front
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("Student_Form_User.fxml"));
            studentStage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                studentStage.setX(event.getScreenX() - x);
                studentStage.setY(event.getScreenY() - y);
            });

            studentStage.initStyle(StageStyle.TRANSPARENT);
            studentStage.setScene(scene);
            studentStage.show();

            // Optional: Add event handler for when the student stage is closed
            studentStage.setOnHidden(event -> studentStage = null);
            studentStage.setOnCloseRequest(event -> studentStage = null);
        }
    }

    private void TEACHBTN() throws IOException {
        if (teacherStage != null && teacherStage.isShowing()) {
            teacherStage.toFront();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("Teacherrr_Form_Userr.fxml"));
            teacherStage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                teacherStage.setX(event.getScreenX() - x);
                teacherStage.setY(event.getScreenY() - y);
            });

            teacherStage.initStyle(StageStyle.TRANSPARENT);
            teacherStage.setScene(scene);
            teacherStage.show();

            teacherStage.setOnHidden(event -> teacherStage = null);
            teacherStage.setOnCloseRequest(event -> teacherStage = null);
        }
    }

    @FXML
    private void BROWBOOK() throws IOException {
        if (borrowBookStage != null && borrowBookStage.isShowing()) {
            borrowBookStage.toFront();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("Borrow_User.fxml"));
            borrowBookStage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                borrowBookStage.setX(event.getScreenX() - x);
                borrowBookStage.setY(event.getScreenY() - y);
            });

            borrowBookStage.initStyle(StageStyle.TRANSPARENT);
            borrowBookStage.setScene(scene);
            borrowBookStage.show();

            borrowBookStage.setOnHidden(event -> borrowBookStage = null);
            borrowBookStage.setOnCloseRequest(event -> borrowBookStage = null);
        }
    }

    @FXML
    private void RETBOOK() throws IOException {
        if (returnBookStage != null && returnBookStage.isShowing()) {
            returnBookStage.toFront();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("ReturnStud.fxml"));
            returnBookStage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                returnBookStage.setX(event.getScreenX() - x);
                returnBookStage.setY(event.getScreenY() - y);
            });

            returnBookStage.initStyle(StageStyle.TRANSPARENT);
            returnBookStage.setScene(scene);
            returnBookStage.show();

            returnBookStage.setOnHidden(event -> returnBookStage = null);
            returnBookStage.setOnCloseRequest(event -> returnBookStage = null);
        }
    }

    @FXML
    private void VIEWBOOK() throws IOException {
        if (viewBookStage != null && viewBookStage.isShowing()) {
            viewBookStage.toFront();
        } else {
            Parent root = FXMLLoader.load(getClass().getResource("TableViewUser.fxml"));
            viewBookStage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                viewBookStage.setX(event.getScreenX() - x);
                viewBookStage.setY(event.getScreenY() - y);
            });

            viewBookStage.initStyle(StageStyle.TRANSPARENT);
            viewBookStage.setScene(scene);
            viewBookStage.show();

            viewBookStage.setOnHidden(event -> viewBookStage = null);
            viewBookStage.setOnCloseRequest(event -> viewBookStage = null);
        }
    }

    private void TeacherBtn() {
        Stud_ANC.setVisible(false);
        Teacher_ANC.setVisible(true);
        Home_Page_Anc.setVisible(false);
        Student_anc_add.setVisible(false);
        Student_anc_return.setVisible(false);
        Teacher_anc_add.setVisible(false);
        Teacher_anc_return.setVisible(false);

    }

    private double x = 0;
    private double y = 0;

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

    private void adjustButtonPositions() {
        double studentOffset = studentMenuVisible ? studentMenu.getChildren().size() * 30 : 0;
        double teacherOffset = teacherMenuVisible ? teacherMenu.getChildren().size() * 30 : 0;

        teacher_add.setLayoutY(student_user.getLayoutY() + student_user.getHeight() + studentOffset);
        teacherMenu.setLayoutY(teacher_add.getLayoutY() + teacher_add.getHeight());
        logout.setLayoutY(teacher_add.getLayoutY() + teacher_add.getHeight() + teacherOffset);
    }
    
    @FXML
    public void minimize() {
        Stage stage = (Stage) ultraform.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    public void close() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Exit Application");
    alert.setHeaderText("Are you sure you want to exit?");
    alert.setContentText("Click OK to exit, Cancel to stay.");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        System.exit(0);
    } else {
    }
    }

    @FXML
    public void maximize() {
        Stage stage = (Stage) ultraform.getScene().getWindow();
        stage.setMaximized(!stage.isMaximized());
    }

}
