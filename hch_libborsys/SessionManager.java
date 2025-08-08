/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hch_libborsys;

/**
 *
 * @author ACER
 */
public class SessionManager {
    private static SessionManager instance;
    private String currentAcademicYear; // This will store "AY 2025-2026", "AY 2026-2027", etc.

    // Private constructor to enforce Singleton pattern
    private SessionManager() {
        // Initialize with a default or null, will be set by User_LoginController
        this.currentAcademicYear = null;
    }

    // Static method to get the single instance of the class
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    // Getter for the current academic year
    public String getCurrentAcademicYear() {
        return currentAcademicYear;
    }

    // Setter for the current academic year
    public void setCurrentAcademicYear(String year) {
        this.currentAcademicYear = year;
        // Optionally, you can add a listener or print for debugging
        System.out.println("SessionManager: Current Academic Year set to: " + year);
    }
}
