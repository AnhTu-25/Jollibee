package com.ui.Manager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDao {

    // ✅ Cấu hình SQL Server
    private final String url = "jdbc:sqlserver://localhost:1433;databaseName=QLSanPham;encrypt=true;trustServerCertificate=true";
    private final String userName = "sa"; // hoặc tên user bạn dùng trong SQL Server
    private final String password = "Anhtu1211"; // thay bằng mật khẩu của bạn

    String INSERT_SQL = "INSERT INTO SanPham (MaSanPham, TenSP, DonGia, SoLuong, MaLH) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE SanPham SET TenSP = ?, DonGia = ?, SoLuong = ?, MaLH = ? WHERE MaSanPham = ?";
    String DELETE_SQL = "DELETE FROM SanPham WHERE MaSanPham = ?";
    String SELECT_ALL_SQL = "SELECT * FROM SanPham";
    String SELECT_BY_ID_SQL = "SELECT * FROM SanPham WHERE MaSanPham = ?";
    String SELECT_LOAISANPHAM_BY_ID_SQL = "SELECT * FROM loai_sanpham WHERE MaLH = ?";
    String SELECT_ALL_LOAISANPHAM_SQL = "SELECT * FROM loai_sanpham";

    private Connection getConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userName, password);
    }

    public int insert(SanPham sp) {
        int rs = 0;
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT_SQL)) {

            st.setString(1, sp.getMaSP());
            st.setString(2, sp.getTenSP());
            st.setDouble(3, sp.getDongia());
            st.setInt(4, sp.getSoluong());
            st.setInt(5, sp.getLoaiSanPham());

            rs = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(); // nên log lỗi
        }
        return rs;
    }

    public int update(SanPham sp) {
        int rs = 0;
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(UPDATE_SQL)) {

            st.setString(1, sp.getTenSP());
            st.setDouble(2, sp.getDongia());
            st.setInt(3, sp.getSoluong());
            st.setInt(4, sp.getLoaiSanPham());
            st.setString(5, sp.getMaSP());

            rs = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    public int delete(String maSP) {
        int rs = 0;
        try (Connection conn = getConnection();
             PreparedStatement st = conn.prepareStatement(DELETE_SQL)) {

            st.setString(1, maSP);
            rs = st.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
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
