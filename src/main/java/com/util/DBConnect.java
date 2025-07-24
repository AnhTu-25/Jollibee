/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.util;
import java.sql.*;
/**
 *
 * @author ha
 */
public class DBConnect {
   public static Connection getConnection() throws SQLException {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=QLKhuyenMai;encrypt=false;";
        String user = "sa"; // hoặc jollibee_user
        String pass = "123"; // hoặc mật khẩu tương ứng
        return DriverManager.getConnection(url, user, pass);
    }
}
