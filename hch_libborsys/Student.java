/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hch_libborsys;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 * @author ACER
 */
public class Student {
    
    //Sa mySQL ni
    private String Full_Name;
    private String Section;
    private String Year_Lvl;
    private String Student_ID;
    private int idStudent;
  
    
    public Student(String Full_Name, String Section, String Year_Lvl, String Student_ID, int idStudent) {
        this.Full_Name = Full_Name;
        this.Section = Section;
        this.Year_Lvl = Year_Lvl;
        this.Student_ID = Student_ID;
        this.idStudent = idStudent;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public String getSection() {
        return Section;
    }

    public String getYear_Lvl() {
        return Year_Lvl;
    }

    public String getStudent_ID() {
        return Student_ID;
    }
    
    public int getIdStudent() { 
        return idStudent;
    }

    public void setFull_Name(String Full_Name) {
        this.Full_Name = Full_Name;
    }

    public void setSection(String Section) {
        this.Section = Section;
    }

    public void setYear_Lvl(String Year_Lvl) {
        this.Year_Lvl = Year_Lvl;
    }

    public void setStudent_ID(String Student_ID) {
        this.Student_ID = Student_ID;
    }
    
    public void setIdStudent(int idStudent) { 
        this.idStudent = idStudent;
    }

    
    @Override
    public String toString() {
        return "Student{" +
                "Fullname='" + Full_Name + '\'' +
                ", Section='" + Section + '\'' +
                ", Title='" + Year_Lvl + '\'' +
                ", Studentid='" + Student_ID + '\'' +
                '}';
    }
    
}
