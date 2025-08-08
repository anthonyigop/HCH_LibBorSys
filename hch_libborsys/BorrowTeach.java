/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hch_libborsys;

/**
 *
 * @author ACER
 */
public class BorrowTeach {
    private int idborrow_teach;
    private String Full_Name;
    private String Designation;
    private String Acc_No;
    private String Title;
    private String Author;
    private String Category;
    private String Date;
    
     public BorrowTeach(int idborrow_teach, String Full_Name, String Designation, String Acc_No, 
                       String Title, String Author, String Category, String Date) {
        this.idborrow_teach = idborrow_teach;
        this.Full_Name = Full_Name;
        this.Designation = Designation;
        this.Acc_No = Acc_No;
        this.Title = Title;
        this.Author = Author;
        this.Category = Category;
        this.Date = Date;
    }

    public int getIdTeacher() {
        return idborrow_teach;
    }
    
    public String getFull_Name() {
        return Full_Name;
    }

    public String getDesignation() {
        return Designation;
    }

    public String getAcc_No() {
        return Acc_No;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getCategory() {
        return Category;
    }

    public String getDate() {
        return Date;
    }

    public void setIdTeacher(int idTeacher) {
        this.idborrow_teach = idborrow_teach;
    }

    public void setFull_Name(String Full_Name) {
        this.Full_Name = Full_Name;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public void setAcc_No(String Acc_No) {
        this.Acc_No = Acc_No;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    @Override
    public String toString() {
        return "BorrowTeach{" +
                "Full_Name='" + Full_Name + '\'' +
                ", Designation='" + Designation + '\'' +
                ", Acc_No='" + Acc_No + '\'' +
                ", Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", Category='" + Category + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
    
}
