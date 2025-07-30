/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO.impl;

import com.DAO.UserDAO;
import com.entity.Userr;
import com.util.XJdbc;
import com.util.XQuery;
import java.util.List;

/**
 *
 * @author Anh Tu
 */
public class UserDAOImpl implements UserDAO {

    String createSql = "INSERT INTO Users (username, password, fullname, photo, Manager, Enabled) VALUES (?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Users SET password = ?, fullname = ?, photo = ?, Manager = ?, Enabled = ? WHERE username = ?";
    String deleteSql = "DELETE FROM Users WHERE username = ?";
    String findAllSql = "SELECT username, password, fullname, photo, Manager, Enabled FROM Users";
    String findByIdSql = "SELECT username, password, fullname, photo, Manager, Enabled FROM Users WHERE username = ?";

    @Override
    public Userr create(Userr entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getPassword(),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(Userr entity) {
        Object[] values = {
            entity.getPassword(), // password
            entity.getFullname(), // fullname ✅ đúng vị trí
            entity.getPhoto(), // photo ✅
            entity.isManager(), // Manager (bit) ✅
            entity.isEnabled(), // Enabled (bit) ✅
            entity.getUsername() // WHERE username = ? ✅
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    public void deleteRelatedBills(String username) {
        String deleteBillsSql = "DELETE FROM Bills WHERE Username = ?";
        XJdbc.executeUpdate(deleteBillsSql, username);
    }

    public boolean hasRelatedBills(String username) {
        String checkSql = "SELECT COUNT(*) FROM Bills WHERE Username = ?";
        Integer count = XQuery.getSingleBean(Integer.class, checkSql, username);
        return count > 0;
    }

    @Override
    public void deleteById(String id) {
        deleteRelatedBills(id);
        XJdbc.executeUpdate(deleteSql, id);
    }

    @Override
    public List<Userr> findAll() {
        return XQuery.getBeanList(Userr.class, findAllSql);
    }

    @Override
    public Userr findById(String id) {
        return XQuery.getSingleBean(Userr.class, findByIdSql, id);
    }

}
