/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.entity;

public class BillDetail {
    private Long id;               // Mã chi tiết hóa đơn
    private Long maHoaDon;        // Mã hóa đơn
    private String tenMonAn;      // Tên món ăn
    private double donGia;        // Đơn giá món ăn
    private double chietKhau;     // Giảm giá (ví dụ 0.1 = 10%)
    private int soLuong;          // Số lượng
    private double thanhTien;     // Thành tiền = đơn giá * số lượng

    public BillDetail() {
    }

    public BillDetail(Long id, Long maHoaDon, String tenMonAn, double donGia, double chietKhau, int soLuong) {
        this.id = id;
        this.maHoaDon = maHoaDon;
        this.tenMonAn = tenMonAn;
        this.donGia = donGia;
        this.chietKhau = chietKhau;
        this.soLuong = soLuong;
        this.thanhTien = donGia * soLuong * (1 - chietKhau); // Tính thành tiền sau giảm giá
    }

    // Getter & Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(Long maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getChietKhau() {
        return chietKhau;
    }

    public void setChietKhau(double chietKhau) {
        this.chietKhau = chietKhau;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    // Các hàm tương thích với hàm gọi cũ trong BillManagerUI.java
    public void setMonAn(String monAn) {
        this.tenMonAn = monAn;
    }

    public void setSoLuongMon(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setGia(double gia) {
        this.donGia = gia;
    }
}