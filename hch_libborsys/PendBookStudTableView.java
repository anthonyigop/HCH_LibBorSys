/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hch_libborsys;

/**
 *
 * @author ACER
 */
public class PendBookStudTableView {
    
    private String Full_Name;
    private String Section;
    private String Year_Level;
    private String StudentID;
    private String Acc_No;
    private String Title;
    private String Author;
    private String Category;
    private String Date;
    private String Due_Date;
    private int idborrow_stud;
    
    public PendBookStudTableView(String Full_Name, String Section, String Year_Level, String StudentID, String Acc_No, String Title, String Author, String Category, String Date, String Due_Date, int idborrow_stud) {
        this.Full_Name = Full_Name;
        this.Section = Section;
        this.Year_Level = Year_Level;
        this.StudentID = StudentID;
        this.Acc_No = Acc_No;
        this.Title = Title;
        this.Author = Author;
        this.Category = Category;
        this.Date = Date;
        this.Due_Date = Due_Date;
        this.idborrow_stud = idborrow_stud;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public String getSection() {
        return Section;
    }

    public String getYear_Level() {
        return Year_Level;
    }

    public String getStudentID() {
        return StudentID;
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

    public String getDue_Date() {
        return Due_Date;
    }

    public int getIdborrow_stud() {
        return idborrow_stud;
    }

    public void setFull_Name(String Full_Name) {
        this.Full_Name = Full_Name;
    }

    public void setSection(String Section) {
        this.Section = Section;
    }

    public void setYear_Level(String Year_Level) {
        this.Year_Level = Year_Level;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
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

    public void setDue_Date(String Due_Date) {
        this.Due_Date = Due_Date;
    }

    public void setIdborrow_stud(int idborrow_stud) {
        this.idborrow_stud = idborrow_stud;
    }
    
}
