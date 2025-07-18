package com.ui.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {

    private final String url = "jdbc:sqlserver://LAPTOP-0EI90H20;database=sampham;encrypt=true;trustServerCertificate=true;";
    private final String userName = "root";   // Ki?m tra l?i n?u b?n dùng SQL Server Auth
    private final String password = "Anhtu1211";

    String INSERT_SQL = "INSERT INTO SanPham (MaSanPham, TenSP, DonGia, SoLuong, MaLH) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE SanPham SET TenSP = ?, DonGia = ?, SoLuong = ?, MaLH = ? WHERE MaSanPham = ?";
    String DELETE_SQL = "DELETE FROM SanPham WHERE MaSanPham = ?";
    String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE MaSanPham = ?";
    String SELECT_LOAISANPHAM_BY_ID_SQL = "SELECT * FROM loai_sanpham WHERE MaLH = ?";
    String SELECT_ALL_LOAISANPHAM_SQL = "SELECT * FROM loai_sanpham";

    private Connection getConnection() throws Exception {
        // ? ??i sang driver SQL Server
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userName, password);
    }

    public int insert(SanPham sp) {
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT_SQL)) {

            st.setString(1, sp.getMaSP());
            st.setString(2, sp.getTenSP());
            st.setDouble(3, sp.getDongia());
            st.setInt(4, sp.getSoluong());
            st.setInt(5, sp.getLoaiSanPham());

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
                sp.setMaSP(rs.getString("MaSanPham"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDongia(rs.getDouble("DonGia"));
                sp.setSoluong(rs.getInt("SoLuong"));
                sp.setLoaiSanPham(rs.getInt("MaLH"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public SanPham getSanPhamByMaSanPham(String maSP) {
        SanPham sp = new SanPham();
        try (Connection conn = getConnection();
             PreparedStatement stm = conn.prepareStatement(SELECT_BY_ID_SQL)) {

            stm.setString(1, maSP);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sp.setMaSP(rs.getString("MaSanPham"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setDongia(rs.getDouble("DonGia"));
                sp.setSoluong(rs.getInt("SoLuong"));
                sp.setLoaiSanPham(rs.getInt("MaLH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sp;
    }

    public List<LoaiSanPham> getAllLoaiSanPham() {
        List<LoaiSanPham> list = new ArrayList<>();
        try (Connection conn = getConnection();
             Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(SELECT_ALL_LOAISANPHAM_SQL)) {

            while (rs.next()) {
                LoaiSanPham lsp = new LoaiSanPham();
                lsp.setMaLoai(rs.getInt("MaLH"));
                lsp.setTenLoai(rs.getString("TenLH"));
                list.add(lsp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public LoaiSanPham getLoaiSanPhamByMaLoai(String maLoai) {
        LoaiSanPham lsp = new LoaiSanPham();
        try (Connection conn = getConnection();
             PreparedStatement stm = conn.prepareStatement(SELECT_LOAISANPHAM_BY_ID_SQL)) {

            stm.setString(1, maLoai);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                lsp.setMaLoai(rs.getInt("MaLH"));
                lsp.setTenLoai(rs.getString("TenLH"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lsp;
    }
}
