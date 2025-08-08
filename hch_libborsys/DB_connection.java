/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hch_libborsys;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Jamie Eduardo Rosal <Jamiewertalmighty@gmail.com>
 */
public class DB_connection {

    private static Connection Myconnection;
    private static final String CONFIG_FILE = "db_config.txt";
    private static boolean hasShownSuccessMessage = false; 

    private static String host;
    private static String port;
    private static String dbName;
    private static String username;
    private static String password;

    public static void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            File config = new File(CONFIG_FILE);
            if (config.exists()) {
                loadConfig();  
            } else {
                promptUserForConfig();  
                saveConfig();
            }

            connect();

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "MySQL JDBC Driver not found.", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connection Failed:\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void connect() throws SQLException {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName +
                "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
        Myconnection = DriverManager.getConnection(url, username, password);
        
        if (!hasShownSuccessMessage) {
            JOptionPane.showMessageDialog(null, "Connected successfully!", "DB Connection", JOptionPane.INFORMATION_MESSAGE);
            hasShownSuccessMessage = true;
        }
    }

    private static void promptUserForConfig() {
        host = JOptionPane.showInputDialog(null, "Enter MySQL Server IP Address:", "Database Setup", JOptionPane.QUESTION_MESSAGE);
        port = JOptionPane.showInputDialog(null, "Enter MySQL Port (default: 3306):", "Database Setup", JOptionPane.QUESTION_MESSAGE);
        if (port == null || port.isEmpty()) port = "3306";
        dbName = JOptionPane.showInputDialog(null, "Enter Database Name:", "Database Setup", JOptionPane.QUESTION_MESSAGE);
        username = JOptionPane.showInputDialog(null, "Enter MySQL Username:", "Database Setup", JOptionPane.QUESTION_MESSAGE);
        password = JOptionPane.showInputDialog(null, "Enter MySQL Password:", "Database Setup", JOptionPane.QUESTION_MESSAGE);
    }

    private static void saveConfig() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write("host=" + host + "\n");
            writer.write("port=" + port + "\n");
            writer.write("database=" + dbName + "\n");
            writer.write("username=" + username + "\n");
            writer.write("password=" + password + "\n");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to save DB config: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void loadConfig() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            Properties props = new Properties();
            props.load(reader);

            host = props.getProperty("host");
            port = props.getProperty("port");
            dbName = props.getProperty("database");
            username = props.getProperty("username");
            password = props.getProperty("password");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Failed to load DB config. Re-entering...", "Warning", JOptionPane.WARNING_MESSAGE);
            promptUserForConfig();
            saveConfig();
        }
    }

    public static Connection getConnection() {
        if (Myconnection != null) {
            return Myconnection;
        } else {
            try {
                connect();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Reconnection failed: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return Myconnection;
        }
    }

    public static void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.println("Error closing ResultSet: " + e);
            }
        }
    }

}