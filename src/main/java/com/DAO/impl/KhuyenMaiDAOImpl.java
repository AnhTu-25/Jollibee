/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO.impl;
import com.DAO.khuyenMaiDAO;
import com.entity.KhuyenMai;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author hạ
 */
public class KhuyenMaiDAOImpl {
      private final List<KhuyenMai> list = new ArrayList<>();

    public KhuyenMaiDAOImpl() {
    }

   
    public void insert(KhuyenMai km) {
        list.add(km);
    }

  
    public void update(KhuyenMai km) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaKM().equalsIgnoreCase(km.getMaKM())) {
                list.set(i, km);
                return;
            }
        }
    }

    
    public KhuyenMai findById(String maKM) {
        for (KhuyenMai km : list) {
            if (km.getMaKM().equalsIgnoreCase(maKM)) {
                return km;
            }
        }
        return null;
    }

    
    public List<KhuyenMai> getAll() {
        return new ArrayList<>(list);
    }
}
