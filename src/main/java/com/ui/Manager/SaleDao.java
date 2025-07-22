/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ui.Manager;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class SaleDao {
 private static final List<SanPham> list = new ArrayList<>();

    public static void insert(SanPham sp) {
        list.add(sp);
    }

    public static void update(int index, SanPham sp) {
        list.set(index, sp);
    }

    public static void delete(int index) {
        list.remove(index);
    }

    public static List<SanPham> getAll() {
        return list;
    }

    public static void clear() {
        list.clear();
    }   
}
