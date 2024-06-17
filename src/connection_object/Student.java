/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package connection_object;

import java.util.Date;

public class Student {
    private String maHS, hoTen;
    private boolean gioiTinh;
    private int diemToan, diemVan, diemAnh;


    public Student() {

    }

    public Student(String maHS) {
        this.maHS = maHS;
    }

    public Student(String maHS, String hoTen, boolean gioiTinh, int diemToan, int diemVan, int diemAnh) {
        this.maHS = maHS;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diemToan = diemToan;
        this.diemVan = diemVan;
        this.diemAnh = diemAnh;

    }


    public String getMaHS() {
        return maHS;
    }

    public void setMaHS(String maHS) {
        this.maHS = maHS;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getDiemToan() {
        return diemToan;
    }

    public void setDiemToan(int diemToan) {
        this.diemToan = diemToan;
    }

    public Boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public int getDiemVan() {
        return diemVan;
    }

    public void setDiemVan(int diemVan) {
        this.diemVan = diemVan;
    }

    public int getDiemAnh() {
        return diemAnh;
    }

    public void setDiemAnh(int diemAnh) {
        this.diemAnh = diemAnh;
    }
}
