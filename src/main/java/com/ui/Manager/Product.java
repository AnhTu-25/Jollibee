/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ui.Manager;

import java.util.List;

/**
 *
 * @author Admin
 */
public class Product {
    private String code;
    private String name;
    private int quantity;
    private double unitPrice;
    private double promotion;

    public Product(String code, String name, int quantity, double unitPrice, double promotion) {
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.promotion = promotion;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getPromotion() {
        return promotion;
    }

    public double getTotalUnitPrice() {
        return quantity * unitPrice - promotion;
    }
    
    
    
    

    List<SanPham> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
 
