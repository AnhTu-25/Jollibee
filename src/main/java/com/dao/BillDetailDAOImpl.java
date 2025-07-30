/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Dao;

import com.entity.BillDetail;
import com.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDetailDAOImpl implements BillDetailDAO {

    private final String SELECT_BY_BILL_ID = "SELECT * FROM BillDetail WHERE BillId=?";
    private final String UPDATE = "UPDATE BillDetail SET Quantity=? WHERE Id=?";
    private final String DELETE = "DELETE FROM BillDetail WHERE Id=?";

    @Override
public void insert(BillDetail detail) {
    // Code để lưu BillDetail vào danh sách hoặc database
}
    
    @Override
    public List<BillDetail> findByBillId(Long billId) {
        return selectBySql(SELECT_BY_BILL_ID, billId);
    }

    @Override
    public void update(BillDetail entity) {
        XJdbc.update(UPDATE,
                entity.getSoLuong(),
                entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.update(DELETE, id);
    }

    private List<BillDetail> selectBySql(String sql, Object... args) {
        List<BillDetail> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                BillDetail entity = new BillDetail();
                entity.setId(rs.getLong("Id"));
                entity.setId(rs.getLong("BillId"));
                entity.setTenMonAn(rs.getString("DrinkName"));
                entity.setDonGia(rs.getDouble("UnitPrice"));
                entity.setChietKhau(rs.getDouble("Discount"));
                entity.setSoLuong(rs.getInt("Quantity"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}

