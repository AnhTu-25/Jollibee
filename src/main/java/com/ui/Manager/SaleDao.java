package com.ui.Manager;

import java.util.ArrayList;
import java.util.List;

/**
 * Lưu trữ danh sách sản phẩm đang được chọn mua (giỏ hàng tạm thời),
 * dùng cho tính tổng tiền và xử lý các thao tác CRUD.
 */
public class SaleDao {
    // Danh sách sản phẩm tạm thời
    private static final List<SanPham> list = new ArrayList<>();

    /**
     * Thêm sản phẩm mới vào danh sách
     */
    public static void insert(SanPham sp) {
        list.add(sp);
    }

    /**
     * Cập nhật thông tin sản phẩm tại vị trí index
     */
    public static void update(int index, SanPham sp) {
        if (index >= 0 && index < list.size()) {
            list.set(index, sp);
        }
    }

    /**
     * Xoá sản phẩm tại vị trí index
     */
    public static void delete(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
        }
    }

    /**
     * Lấy toàn bộ danh sách sản phẩm hiện tại
     */
    public static List<SanPham> getAll() {
        return new ArrayList<>(list); // Trả về bản sao để tránh sửa trực tiếp
    }

    /**
     * Xoá toàn bộ sản phẩm khỏi danh sách
     */
    public static void clear() {
        list.clear();
    }

    /**
     * Tính tổng tiền của tất cả sản phẩm trong danh sách
     */
    public static double getTotalPrice() {
        double total = 0;
        for (SanPham sp : list) {
            total += sp.getDongia() * sp.getSoluong();  // Sử dụng đúng getter từ class SanPham
        }
        return total;
    }
}
