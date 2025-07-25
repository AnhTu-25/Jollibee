/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.entity.Bill;
import com.util.XJdbc;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillDAOImpl implements BillDAO {

    private final String SELECT_BY_ID = "SELECT * FROM Bill WHERE Id=?";
    private final String UPDATE = "UPDATE Bill SET CardId=?, Username=?, Checkin=?, Checkout=?, Status=? WHERE Id=?";
    private final String DELETE = "DELETE FROM Bill WHERE Id=?";

    @Override
    public Bill findById(Long id) {
        List<Bill> list = selectBySql(SELECT_BY_ID, id);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public void update(Bill entity) {
        XJdbc.update(UPDATE,
                entity.getCardId(),
                entity.getUsername(),
                entity.getCheckin(),
                entity.getCheckout(),
                entity.getStatus(),
                entity.getId());
    }

    @Override
    public void deleteById(Long id) {
        XJdbc.update(DELETE, id);
    }

    private List<Bill> selectBySql(String sql, Object... args) {
        List<Bill> list = new ArrayList<>();
        try {
            ResultSet rs = XJdbc.query(sql, args);
            while (rs.next()) {
                Bill entity = new Bill();
                entity.setId(rs.getLong("Id"));
                entity.setCardId(rs.getLong("CardId"));
                entity.setUsername(rs.getString("Username"));
                entity.setCheckin(rs.getTimestamp("Checkin"));
                entity.setCheckout(rs.getTimestamp("Checkout"));
                entity.setStatus(rs.getInt("Status"));
                list.add(entity);
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
}

