/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ui.Manager;


public class SanPham {
    String maSP;
    String tenSP;
    double dongia;
    int soluong;
    int loaiSanPham;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, double dongia, int soluong, int loaiSanPham) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.dongia = dongia;
        this.soluong = soluong;
        this.loaiSanPham = loaiSanPham;
    }

   

    

    SanPham(String masp, String tensp, int soluong, double dongia, boolean khuyenmai) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getLoaiSanPham() {
        return loaiSanPham;
    }

    public void setLoaiSanPham(int loaiSanPham) {
        this.loaiSanPham = loaiSanPham;
    }
    
    



    public Object[] toRow() {
      return new Object[]{
          maSP,
          tenSP,
          loaiSanPham,
          dongia,      // Giá trước
          soluong      // Số lượng sau
      };
  }

   
}