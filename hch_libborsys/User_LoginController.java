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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author ACER
 */
public class User_LoginController implements Initializable {

    // Database connection variables
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    
    // Mouse drag variables
    private double x = 0;
    private double y = 0;
    
    // Password visibility state
    private boolean isPasswordVisible = false;

    // FXML Components - Sign In
    @FXML
    private AnchorPane SignIn_Anc;
    @FXML
    private TextField U_username;
    @FXML
    private PasswordField U_password;
    @FXML
    private TextField U_password_Visible;
    @FXML
    private Button login_btn;
    @FXML
    private Button togglePasswordBtn;
    @FXML
    private Button SignUp_switch;
    @FXML
    private Button ForgotPass_Btn;

    // FXML Components - Sign Up
    @FXML
    private AnchorPane SignUp_Anc;
    @FXML
    private Button SignIn_switch;
    @FXML
    private Button Enter_btn;
    @FXML
    private Button SignUp_Btn;

    // FXML Components - Create Account
    @FXML
    private AnchorPane CreateAcc_Anc;
    @FXML
    private TextField Create_Username;
    @FXML
    private PasswordField Create_Password;
    @FXML
    private PasswordField Create_ConfirmPassword;
    @FXML
    private TextField Create_PasswordVisible;
    @FXML
    private TextField Create_ConfirmPasswordVisible;
    @FXML
    private Button Create_Button;
    @FXML
    private Button Switch_Create;
    @FXML
    private Button toggleCreate_PasswordBtn;
    @FXML
    private Button toggleCreate_ConfirmPasswordBtn;

    // FXML Components - Change Password
    @FXML
    private AnchorPane ChangePass_Anc;
    @FXML
    private TextField ChangePass_Username;
    @FXML
    private TextField ChangePass_CurrentPass;
    @FXML
    private TextField ChangePass_NewPass;
    @FXML
    private TextField ChangePass_NewPassConfirm;
    @FXML
    private Button Change_Button;
    @FXML
    private Button Switch_Create1;
    @FXML
    private TextField Create_PasswordVisible1;
    @FXML
    private TextField Create_ConfirmPasswordVisible1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeKeyHandlers();
        initializePromptTexts();
        initializeStyles();
        initializePasswordVisibility();
    }

    private void initializeKeyHandlers() {
        // Setup key handlers for navigation between text fields
        setupKeyHandler(U_username, U_password, null);
        setupKeyHandler(U_password, null, U_username);

        setupKeyHandler(Create_Username, Create_Password, null);
        setupKeyHandler(Create_Password, Create_ConfirmPassword, Create_Username);
        setupKeyHandler(Create_ConfirmPassword, null, Create_Password);

        setupKeyHandler(ChangePass_Username, ChangePass_CurrentPass, null);
        setupKeyHandler(ChangePass_CurrentPass, ChangePass_NewPass, ChangePass_Username);
        setupKeyHandler(ChangePass_NewPass, ChangePass_NewPassConfirm, ChangePass_CurrentPass);
        setupKeyHandler(ChangePass_NewPassConfirm, null, ChangePass_NewPass);
    }

    private void initializePromptTexts() {
        // Set prompt texts for all text fields
        U_username.setPromptText("Username");
        U_password.setPromptText("Password");
        U_password_Visible.setPromptText("Password");

        Create_Username.setPromptText("Username");
        Create_Password.setPromptText("Password");
        Create_ConfirmPassword.setPromptText("Confirm Password");
        Create_PasswordVisible.setPromptText("Password");
        Create_ConfirmPasswordVisible.setPromptText("Confirm Password");

        ChangePass_Username.setPromptText("Username");
        ChangePass_CurrentPass.setPromptText("Current Password");
        ChangePass_NewPass.setPromptText("New Password");
        ChangePass_NewPassConfirm.setPromptText("Confirm New Password");
    }

    private void initializeStyles() {
        // Apply consistent styling to all text fields
        String textFieldStyle = "-fx-padding: 0 0 0 22;";
        U_username.setStyle(textFieldStyle);
        U_password.setStyle(textFieldStyle);
        U_password_Visible.setStyle(textFieldStyle);
        Create_Username.setStyle(textFieldStyle);
        Create_Password.setStyle(textFieldStyle);
        Create_ConfirmPassword.setStyle(textFieldStyle);
        Create_PasswordVisible.setStyle(textFieldStyle);
        Create_ConfirmPasswordVisible.setStyle(textFieldStyle);
        ChangePass_Username.setStyle(textFieldStyle);
        ChangePass_CurrentPass.setStyle(textFieldStyle);
        ChangePass_NewPass.setStyle(textFieldStyle);
        ChangePass_NewPassConfirm.setStyle(textFieldStyle);
    }

    private void initializePasswordVisibility() {
        // Initial visibility for password fields (hide visible text fields by default)
        U_password_Visible.setVisible(false);
        U_password_Visible.setManaged(false);
        Create_PasswordVisible.setVisible(false);
        Create_PasswordVisible.setManaged(false);
        Create_ConfirmPasswordVisible.setVisible(false);
        Create_ConfirmPasswordVisible.setManaged(false);

        // Bind the text properties for password visibility
        U_password.textProperty().bindBidirectional(U_password_Visible.textProperty());
        Create_Password.textProperty().bindBidirectional(Create_PasswordVisible.textProperty());
        Create_ConfirmPassword.textProperty().bindBidirectional(Create_ConfirmPasswordVisible.textProperty());
    }

    @FXML
    private void togglePasswordVisibility() {
        isPasswordVisible = !isPasswordVisible;

        if (isPasswordVisible) {
            U_password_Visible.setVisible(true);
            U_password_Visible.setManaged(true);
            U_password.setVisible(false);
            U_password.setManaged(false);
            togglePasswordBtn.setText("Hide");
        } else {
            U_password_Visible.setVisible(false);
            U_password_Visible.setManaged(false);
            U_password.setVisible(true);
            U_password.setManaged(true);
            togglePasswordBtn.setText("Show");
        }
    }

    @FXML
    private void toggleCreatePasswordVisibility() {
        boolean isVisible = Create_PasswordVisible.isVisible();

        if (isVisible) {
            Create_PasswordVisible.setVisible(false);
            Create_PasswordVisible.setManaged(false);
            Create_Password.setVisible(true);
            Create_Password.setManaged(true);
            toggleCreate_PasswordBtn.setText("Show");
        } else {
            Create_PasswordVisible.setText(Create_Password.getText());
            Create_PasswordVisible.setVisible(true);
            Create_PasswordVisible.setManaged(true);
            Create_Password.setVisible(false);
            Create_Password.setManaged(false);
            toggleCreate_PasswordBtn.setText("Hide");
        }
    }

    @FXML
    private void toggleCreateConfirmPasswordVisibility() {
        boolean isVisible = Create_ConfirmPasswordVisible.isVisible();

        if (isVisible) {
            Create_ConfirmPasswordVisible.setVisible(false);
            Create_ConfirmPasswordVisible.setManaged(false);
            Create_ConfirmPassword.setVisible(true);
            Create_ConfirmPassword.setManaged(true);
            toggleCreate_ConfirmPasswordBtn.setText("Show");
        } else {
            Create_ConfirmPasswordVisible.setText(Create_ConfirmPassword.getText());
            Create_ConfirmPasswordVisible.setVisible(true);
            Create_ConfirmPasswordVisible.setManaged(true);
            Create_ConfirmPassword.setVisible(false);
            Create_ConfirmPassword.setManaged(false);
            toggleCreate_ConfirmPasswordBtn.setText("Hide");
        }
    }

    private void setupKeyHandler(javafx.scene.control.Control current,
            javafx.scene.control.Control next,
            javafx.scene.control.Control previous) {
        current.setOnKeyPressed((KeyEvent event) -> {
            if ((event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.DOWN) && next != null) {
                next.requestFocus();
            } else if (event.getCode() == KeyCode.UP && previous != null) {
                previous.requestFocus();
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void viewAdminAnc(ActionEvent event) {
        showPane(SignIn_Anc);
    }

    @FXML
    private void viewUserAnc(ActionEvent event) {
        if (event.getSource() == SignUp_switch) {
            showPane(SignUp_Anc);
        }
    }

    @FXML
    private void viewCreateAcc(ActionEvent event) {
        if (event.getSource() == SignUp_Btn) {
            showPane(CreateAcc_Anc);
        }
    }

    @FXML
    private void viewChangePassAcc(ActionEvent event) {
        if (event.getSource() == ForgotPass_Btn) {
            showPane(ChangePass_Anc);
        }
    }

    private void showPane(AnchorPane targetPane) {
        // Hide all panes
        SignIn_Anc.setVisible(false);
        SignUp_Anc.setVisible(false);
        CreateAcc_Anc.setVisible(false);
        ChangePass_Anc.setVisible(false);
        
        // Show target pane
        targetPane.setVisible(true);
    }

    @FXML
    private void Enter() throws IOException {
        navigateToUserPage(Enter_btn);
    }

    @FXML
    public void Login_admin() throws IOException {
        String username = U_username.getText().trim();
        String password = getPasswordText(U_password, U_password_Visible);

        // Validation
        if (username.isEmpty() || password.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Username and Password cannot be empty.");
            return;
        }

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet result = null;

        try {
            // Initialize DB connection if not already done
            if (DB_connection.getConnection() == null) {
                DB_connection.init();
            }
            
            conn = DB_connection.getConnection();
            if (conn == null) {
                showAlert(Alert.AlertType.ERROR, "Database Error", "Could not establish database connection.");
                return;
            }

            String query = "SELECT * FROM user WHERE Username = ? AND Password = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            result = pstmt.executeQuery();
            
            if (result.next()) {
                UserData.setUsername(username);
                showAlert(Alert.AlertType.INFORMATION, "Login Successful", "Successfully logged in!");
                navigateToAdminPage(login_btn);
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
            }

        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Database connection failed: " + e.getMessage());
            Logger.getLogger(User_LoginController.class.getName()).log(Level.SEVERE, "Database error during login", e);
        } finally {
            // Clean up resources
            DB_connection.close(result);
            if (pstmt != null) {
                try { pstmt.close(); } catch (SQLException e) { /* ignore */ }
            }
            // Note: Don't close the connection as it's managed by DB_connection class
        }
    }

    @FXML
    private void handleCreateAccount(ActionEvent event) {
        String newUsername = Create_Username.getText().trim();
        String newPassword = getPasswordText(Create_Password, Create_PasswordVisible);
        String confirmPassword = getPasswordText(Create_ConfirmPassword, Create_ConfirmPasswordVisible);

        // Validation
        if (newUsername.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required.");
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Passwords do not match.");
            return;
        }

        if (newPassword.length() < 6) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Password must be at least 6 characters long.");
            return;
        }

        try (Connection conn = DB_connection.getConnection()) {
            // Check if username already exists
            String checkQuery = "SELECT COUNT(*) FROM user WHERE Username = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, newUsername);
                try (ResultSet checkResult = checkStmt.executeQuery()) {
                    if (checkResult.next() && checkResult.getInt(1) > 0) {
                        showAlert(Alert.AlertType.ERROR, "Registration Failed", "Username already exists. Please choose a different one.");
                        return;
                    }
                }
            }

            // Insert new user
            String insertQuery = "INSERT INTO user (Username, Password) VALUES (?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
                insertStmt.setString(1, newUsername);
                insertStmt.setString(2, newPassword);

                int rowsAffected = insertStmt.executeUpdate();
                if (rowsAffected > 0) {
                    showAlert(Alert.AlertType.INFORMATION, "Registration Successful", "Account created successfully. You can now log in.");
                    clearCreateAccountFields();
                    showPane(SignIn_Anc); // Navigate back to sign in
                } else {
                    showAlert(Alert.AlertType.ERROR, "Registration Failed", "Failed to create account. Please try again.");
                }
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error creating account: " + e.getMessage());
            Logger.getLogger(User_LoginController.class.getName()).log(Level.SEVERE, "Database error during account creation", e);
        }
    }

    @FXML
    private void handleChangePassword(ActionEvent event) {
        String username = ChangePass_Username.getText().trim();
        String currentPassword = ChangePass_CurrentPass.getText();
        String newPassword = ChangePass_NewPass.getText();
        String confirmNewPassword = ChangePass_NewPassConfirm.getText();

        // Validation
        if (username.isEmpty() || currentPassword.isEmpty() || newPassword.isEmpty() || confirmNewPassword.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "All fields are required.");
            return;
        }

        if (!newPassword.equals(confirmNewPassword)) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "New passwords do not match.");
            return;
        }

        if (newPassword.equals(currentPassword)) {
            showAlert(Alert.AlertType.WARNING, "Validation Warning", "New password cannot be the same as the current password.");
            return;
        }

        if (newPassword.length() < 6) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "New password must be at least 6 characters long.");
            return;
        }

        try (Connection conn = DB_connection.getConnection()) {
            // Verify current password
            String checkQuery = "SELECT COUNT(*) FROM user WHERE Username = ? AND Password = ?";
            try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                checkStmt.setString(1, username);
                checkStmt.setString(2, currentPassword);
                try (ResultSet checkResult = checkStmt.executeQuery()) {
                    if (checkResult.next() && checkResult.getInt(1) > 0) {
                        // Update password
                        String updateQuery = "UPDATE user SET Password = ? WHERE Username = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                            updateStmt.setString(1, newPassword);
                            updateStmt.setString(2, username);

                            int rowsAffected = updateStmt.executeUpdate();
                            if (rowsAffected > 0) {
                                showAlert(Alert.AlertType.INFORMATION, "Password Changed", "Password updated successfully.");
                                clearChangePasswordFields();
                                showPane(SignIn_Anc); // Navigate back to sign in
                            } else {
                                showAlert(Alert.AlertType.ERROR, "Password Change Failed", "Failed to update password. Please try again.");
                            }
                        }
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Password Change Failed", "Incorrect username or current password.");
                    }
                }
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error updating password: " + e.getMessage());
            Logger.getLogger(User_LoginController.class.getName()).log(Level.SEVERE, "Database error during password change", e);
        }
    }

    private String getPasswordText(PasswordField passwordField, TextField visibleField) {
        return visibleField.isVisible() ? visibleField.getText() : passwordField.getText();
    }

    private void clearCreateAccountFields() {
        Create_Username.clear();
        Create_Password.clear();
        Create_ConfirmPassword.clear();
        Create_PasswordVisible.clear();
        Create_ConfirmPasswordVisible.clear();
    }

    private void clearChangePasswordFields() {
        ChangePass_Username.clear();
        ChangePass_CurrentPass.clear();
        ChangePass_NewPass.clear();
        ChangePass_NewPassConfirm.clear();
    }

    private void navigateToAdminPage(Button sourceButton) throws IOException {
        navigateToPage(sourceButton, "Admin.fxml");
    }

    private void navigateToUserPage(Button sourceButton) throws IOException {
        navigateToPage(sourceButton, "User_user.fxml");
    }

    private void navigateToPage(Button sourceButton, String fxmlFile) throws IOException {
        sourceButton.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        // Enable window dragging
        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);
        });

        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    public void close() {
        System.exit(0);
    }

    // Static inner class for user data management
    public static class UserData {
        private static String username;
        private static String path;

        public static String getUsername() {
            return username;
        }

        public static void setUsername(String username) {
            UserData.username = username;
        }

        public static String getPath() {
            return path;
        }

        public static void setPath(String path) {
            UserData.path = path;
        }
    }
}