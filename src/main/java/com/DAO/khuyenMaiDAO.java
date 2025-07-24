/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.DAO;
import com.entity.KhuyenMai;
import java.util.List;
/**
 *
 * @author hạ
 */
public interface khuyenMaiDAO {
boolean insert(KhuyenMai km);
    void update(KhuyenMai km);
    KhuyenMai findById(String maKM);
    List<KhuyenMai> getAll();
}

