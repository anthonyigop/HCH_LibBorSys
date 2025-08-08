/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hch_libborsys;

/**
 *
 * @author ACER
 */
public class ViewTable {
    
    private String Title;
    private String Acc_No;
    private String Author;
    private String Category;
    private String Status;
    private int idInventory;
    
     public ViewTable(String Title, String Acc_No, String Author, String Category, String Status, int idInventory) {
        this.Title = Title;
        this.Acc_No = Acc_No;
        this.Author = Author;
        this.Category = Category;
        this.Status = Status;
        this.idInventory = idInventory;
     }

    public String getTitle() {
        return Title;
    }

    public String getAcc_No() {
        return Acc_No;
    }

    public String getAuthor() {
        return Author;
    }

    public String getCategory() {
        return Category;
    }

    public String getStatus() {
        return Status;
    }

    public int getIdInventory() {
        return idInventory;
    }
    
    public void setTitle(String Title) {
        this.Title = Title;
    }

    public void setAcc_No(String Acc_No) {
        this.Acc_No = Acc_No;
    }

    public void setAuthor(String Author) {
        this.Author = Author;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setIdInventory(int idInventory) {
        this.idInventory = idInventory;
    }
    
    @Override
    public String toString() {
        return "ViewTable{" +
                "Title='" + Title + '\'' +
                ", Acc_No='" + Acc_No + '\'' +
                ", Author='" + Author + '\'' +
                ", Author='" + Category + '\'' +
                ", Author='" + Status + '\'' +
                '}';
    }
    
    
}
