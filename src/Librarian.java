/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Dell
 */
public class Librarian {
    public String mathuthu;
    public String tenthuthu;
    public String calamviec;
    
    public Librarian (String mathuthu, String tenthuthu, String calamviec){
        this.mathuthu=mathuthu;
        this.tenthuthu= tenthuthu;
        this.calamviec = calamviec;
    }

    public String getMathuthu() {
        return mathuthu;
    }

    public void setMathuthu(String mathuthu) {
        this.mathuthu = mathuthu;
    }

    public String getTenthuthu() {
        return tenthuthu;
    }

    public void setTenthuthu(String tenthuthu) {
        this.tenthuthu = tenthuthu;
    }

    public String getCalamviec() {
        return calamviec;
    }

    public void setCalamviec(String calamviec) {
        this.calamviec = calamviec;
    }
    



    public String toString() {
        return "Mathuthu:"+ mathuthu+",Tenthuthu: "+tenthuthu+",Calamviec: "+calamviec;
    }
    
   
 
}
