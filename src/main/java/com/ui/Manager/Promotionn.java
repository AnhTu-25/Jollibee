/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ui.Manager;

import java.util.Vector;

/**
 *
 * @author hạ
 */
public class Promotionn {
   
    private String maKM;
    private String tenKM;
    private String loaiKM;
    private double giaTri;
    private String trangThai;

    Promotionn(String maKM, String tenKM, String loaiKM) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    Promotionn(String ma, String ten, String loai, double giaTri, String trangThai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setMaKM(String maKM) {
        this.maKM = maKM;
    }

    public void setTenKM(String tenKM) {
        this.tenKM = tenKM;
    }

    public void setLoaiKM(String loaiKM) {
        this.loaiKM = loaiKM;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }

    // constructor, getter, setter
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaKM() {
        return maKM;
    }

    public String getTenKM() {
        return tenKM;
    }

    public String getLoaiKM() {
        return loaiKM;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public Object[] toTable(){
        return new Object[]{
            maKM, tenKM, loaiKM, giaTri, trangThai
        };
    } 
 
}
