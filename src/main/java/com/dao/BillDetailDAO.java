/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.BillDetail;
import java.util.List;

public interface BillDetailDAO {
    List<BillDetail> findByBillId(Long billId);
    void update(BillDetail entity);
    void deleteById(Long id);
}

