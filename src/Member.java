
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class Member {
    private String mathanhvien;
    private String tenthanhvien;
    private String diachi;
    private List<String> sachdangmuon;
    
    public Member(String mathanhvien,String tenthanhvien, String diachi){
        this.mathanhvien= mathanhvien;
        this.tenthanhvien= tenthanhvien;
        this.diachi= diachi;
        this.sachdangmuon= new ArrayList<>();
    }

    public String getMathanhvien() {
        return mathanhvien;
    }
    public String getTen() {
        return tenthanhvien;
    }

    public String getDiachi() {
        return diachi;
    }

    public List<String> getSachdangmuon() {
        return sachdangmuon;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }
    public void setTen(String tenthanhvien) {
        this.tenthanhvien = tenthanhvien;
    }
    public void borrowBook(String masach){
        sachdangmuon.add(masach);
    }
    public void removeBook(String masach){
        sachdangmuon.remove(masach);
    }

    @Override
    public String toString() {
        return "Mathanhvien: "+mathanhvien+",Ten: "+tenthanhvien+",Diachi: "+diachi;
    }

    void trasach(String masach) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    

    
}
