/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hch_libborsys;

/**
 *
 * @author ACER
 */
public class Teacher {
    
    //Sa mySQL ni
    private int idTeacher;
    private String Full_Name;
    private String Designation;
    
    
    public Teacher(int idTeacher, String Full_Name, String Designation) {
        this.idTeacher = idTeacher;
        this.Full_Name = Full_Name;
        this.Designation = Designation;
    }
    
    public int getIdTeacher() {
        return idTeacher;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public String getDesignation() {
        return Designation;
    }
    
    public void setIdTeacher(int idTeacher) { 
        this.idTeacher = idTeacher;
    }

    public void setFull_Name(String Full_Name) {
        this.Full_Name = Full_Name;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    
    
    @Override
    public String toString() {
        return "Teacher{" +
                "Fullname='" + Full_Name + '\'' +
                ", Designation='" + Designation + '\'' +         
                '}';
    }
    
    
}
