/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.util;
import java.sql.*;
import javax.swing.JOptionPane;
import java.awt.Component;
/**
 *
 * @author ha
 */
public class XJdbc {
      private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyKhuyenMai;encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "123";

    static {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void update(String sql, Object... args) {
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) ps.setObject(i + 1, args[i]);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet query(String sql, Object... args) throws SQLException {
        Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) ps.setObject(i + 1, args[i]);
        return ps.executeQuery();
    }
    public class MsgBox {
    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message);
    }

    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Xac nhan", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message);
    }
}
}
