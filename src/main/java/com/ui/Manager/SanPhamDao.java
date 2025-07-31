package com.ui.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {

    static List<SanPham> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=jollibee;encrypt=true;trustServerCertificate=true;";

    private final String userName = "sa";           // chỉnh lại nếu cần
    private final String password = "Anhtu1211"; // sửa thành mật khẩu thật

    private final String INSERT_SQL = "INSERT INTO sanpham (ten_sp, don_gia, so_luong, ma_loai) VALUES (?, ?, ?, ?)";
    private final String UPDATE_SQL = "UPDATE sanpham SET ten_sp = ?, don_gia = ?, so_luong = ?, ma_loai = ? WHERE ma_sp = ?";
    private final String DELETE_SQL = "DELETE FROM sanpham WHERE ma_sp = ?";
    private final String SELECT_ALL_SQL = "SELECT * FROM sanpham";
    private final String SELECT_BY_ID_SQL = "SELECT * FROM sanpham WHERE ma_sp = ?";
    private final String SELECT_ALL_LOAISANPHAM_SQL = "SELECT * FROM loai_sanpham";
    private final String SELECT_LOAISANPHAM_BY_ID_SQL = "SELECT * FROM loai_sanpham WHERE ma_loai = ?";

    private Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userName, password);
    }

    public int insert(SanPham sp) {
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT_SQL)) {

            st.setString(1, sp.getTenSP());
            st.setDouble(2, sp.getDongia());
            st.setInt(3, sp.getSoluong());
            st.setInt(4, sp.getLoaiSanPham());
            return st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int update(SanPham sp) {
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(UPDATE_SQL)) {

            st.setString(1, sp.getTenSP());
            st.setDouble(2, sp.getDongia());
            st.setInt(3, sp.getSoluong());
            st.setInt(4, sp.getLoaiSanPham());
            st.setString(5, sp.getMaSP());
            return st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(String maSP) {
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(DELETE_SQL)) {

            st.setString(1, maSP);
            return st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<SanPham> getAllSanPham() {
        List<SanPham> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(SELECT_ALL_SQL)) {

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("ma_sp"));
                sp.setTenSP(rs.getString("ten_sp"));
                sp.setDongia(rs.getDouble("don_gia"));
                sp.setSoluong(rs.getInt("so_luong"));
                sp.setLoaiSanPham(rs.getInt("ma_loai"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public SanPham getSanPhamById(String maSP) {
        try (Connection conn = getConnection();
             PreparedStatement stm = conn.prepareStatement(SELECT_BY_ID_SQL)) {

            stm.setString(1, maSP);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    SanPham sp = new SanPham();
                    sp.setMaSP(rs.getString("ma_sp"));
                    sp.setTenSP(rs.getString("ten_sp"));
                    sp.setDongia(rs.getDouble("don_gia"));
                    sp.setSoluong(rs.getInt("so_luong"));
                    sp.setLoaiSanPham(rs.getInt("ma_loai"));
                    return sp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<LoaiSanPham> getAllLoaiSanPham() {
        List<LoaiSanPham> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(SELECT_ALL_LOAISANPHAM_SQL)) {

            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setMaLoai(rs.getInt("ma_loai"));
                lsp.setTenLoai(rs.getString("ten_loai"));
                list.add(lsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public LoaiSanPham getLoaiSanPhamById(int loai) {
        try (Connection conn = getConnection();
             PreparedStatement stm = conn.prepareStatement(SELECT_LOAISANPHAM_BY_ID_SQL)) {

            stm.setInt(1, loai);
            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    LoaiSanPham lsp = new LoaiSanPham();
                    lsp.setMaLoai(rs.getInt("ma_loai"));
                    lsp.setTenLoai(rs.getString("ten_loai"));
                    return lsp;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
