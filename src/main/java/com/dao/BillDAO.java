    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Dao;

import com.entity.Bill;

public interface BillDAO {
    Bill findById(Long id);
    void update(Bill entity);
    void deleteById(Long id);
}

