/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entity;

/**
 *
 * @author hạ
 */
public class KhuyenMai {
    
     
    private String maKM;
    private String tenKM;
    private String loaiKM;
    private double giaTri;
    private String trangThai;

    public KhuyenMai(String maKM, String tenKM, String loaiKM, double giaTri, String trangThai) {
        this.maKM = maKM;
        this.tenKM = tenKM;
        this.loaiKM = loaiKM;
        this.giaTri = giaTri;
        this.trangThai = trangThai;
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
    public Object[] toTable() {
        return new Object[]{maKM, tenKM, loaiKM, giaTri, trangThai};
    }
}


