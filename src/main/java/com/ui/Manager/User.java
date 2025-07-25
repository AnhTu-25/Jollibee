/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ui.Manager;

import static java.nio.file.Files.list;
import static java.util.Collections.list;
import java.util.Vector;

/**
 *
 * @author LENOVO
 */
public class User {
    private String ma;
    private String ten;
    private String email;
    private String ngaysinh;
    private String sdt;
    private String matkhau;
    private String ghichu;
    private String gioitinh;
    private String vaitro;
    private String trangthai;

    public User() {
    }

    public String getMa() {
        return ma;
    }

    public String getTen() {
        return ten;
    }

    public String getEmail() {
        return email;
    }

    public String getNgaysinh() {
        return ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public String getGhichu() {
        return ghichu;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public String getVaitro() {
        return vaitro;
    }

    public String getTrangthai() {
        return trangthai;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNgaysinh(String ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public void setVaitro(String vaitro) {
        this.vaitro = vaitro;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public User(String ma, String ten, String email, String ngaysinh, String sdt,
                String matkhau, String ghichu, String gioitinh, String vaitro, String trangthai) {
        this.ma = ma;
        this.ten = ten;
        this.email = email;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
        this.matkhau = matkhau;
        this.ghichu = ghichu;
        this.gioitinh = gioitinh;
        this.vaitro = vaitro;
        this.trangthai = trangthai;
    }

    public Vector<String> toTable() {
        Vector<String> v = new Vector<>();
        v.add(this.ma);
        v.add(this.ten);
        v.add(this.email);
        v.add(this.ngaysinh);
        v.add(this.sdt);
        v.add(this.gioitinh);
        v.add(this.vaitro);
        v.add(this.trangthai);
        v.add(this.ghichu);
        return v;
    }

    // Getter & Setter (tạo đầy đủ cho tất cả fields ở trên)
    // ... Tự NetBeans Generate nhanh hoặc mình viết giúp nếu bạn muốn


}
