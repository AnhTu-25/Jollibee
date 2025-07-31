/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ui.Manager;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class Sale extends JFrame {
    DefaultTableModel model;
    
private DefaultTableModel historyModel;
    public Sale() {
        initComponents(); // Auto-generated UI
        initCustom();     // Custom logic
        
    }

    private void initCustom() {
    // Gán model cho bảng
    model = new DefaultTableModel(new Object[]{"Product Code", "Product Name", "Quantity", "Unit Price", "Promotion(%)", "Total Unit Price"}, 0);
    tblProduct.setModel(model);

    // Thiết lập spinner: mặc định 0, min 1, max 100, bước nhảy 1
    spnQuantity.setModel(new SpinnerNumberModel(0, 0, 100, 1));

    // Sự kiện chọn dòng bảng: click chọn dòng để đổ dữ liệu lên form
    tblProduct.getSelectionModel().addListSelectionListener(e -> {
        if (!e.getValueIsAdjusting()) {
            fillToForm();
        }
    });

    // Cập nhật tổng tiền ban đầu
    updateTotalPrice();

    //  Thêm xử lý checkbox Promotion 
    ckxPromotion.setText("Promotion");
    txtPromotion.setEnabled(false);
    ckxPromotion.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            boolean selected = ckxPromotion.isSelected();
            txtPromotion.setEnabled(selected);
            if (!selected) {
                txtPromotion.setText("");
            }
        }
    });
}


    private void fillToForm() {
    int row = tblProduct.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần sửa.");
        return;
    }

    // Lấy dữ liệu từ bảng
    String code = model.getValueAt(row, 0).toString();
    String name = model.getValueAt(row, 1).toString();
    int quantity = (int) model.getValueAt(row, 2);
    double unitPrice = (double) model.getValueAt(row, 3);
    double promotionDecimal = (double) model.getValueAt(row, 4); // ví dụ: 0.1
    
    
    ckxPromotion.setSelected(promotionDecimal > 0); // nếu có KM thì check box
    ckxPromotion.setText(String.valueOf((int) (promotionDecimal * 100))); // gán số %


    // Hiển thị lên form
    txtProductcode.setText(code);
    txtProductname.setText(name);
    spnQuantity.setValue(quantity);
    txtUnitprice.setText(String.valueOf(unitPrice));

    // Chuyển đổi khuyến mãi sang phần trăm
    int promotionPercent = (int) (promotionDecimal * 100);
    ckxPromotion.setText(String.valueOf(promotionPercent));
}

    private void loadData() {
    DefaultTableModel model = (DefaultTableModel) tblProduct.getModel();
    model.setRowCount(0); // Xóa dữ liệu cũ

    List<SanPham> list = SanPhamDao.getAll(); // Lấy danh sách sản phẩm từ DAO
    for (SanPham sp : list) {
        model.addRow(new Object[]{
            sp.getMaSP(),
            sp.getTenSP(),
            sp.getDongia(),
            sp.getSoluong(),
            sp.getKhuyenmai()
        });
    }

    updateTotalPrice(); // Nếu bạn có tính tổng tiền thì cập nhật lại luôn
}

    private void clearForm() {
    txtProductcode.setText("");
    txtProductname.setText("");
    spnQuantity.setValue(1);
    txtUnitprice.setText("");
    ckxPromotion.setSelected(false);
    tblProduct.clearSelection();
}



    private void MoreToTable() {
    String code = txtProductcode.getText();
    String name = txtProductname.getText();
    int quantity = (int) spnQuantity.getValue();
    double price = parseDoubleSafe(txtUnitprice.getText());

    double promotionPercent = 0.0;
    if (ckxPromotion.isSelected()) {
        promotionPercent = parseDoubleSafe(txtPromotion.getText());
    }

    if (quantity < 1) {
        JOptionPane.showMessageDialog(this, "Số lượng phải >= 1");
        return;
    }

    double promotion = promotionPercent / 100.0;
    double total = quantity * price * (1 - promotion);

    // ✅ KHÔNG gán lại setText() cho ckxPromotion ở đây — giữ nguyên nhãn "Promotion"
    model.addRow(new Object[]{code, name, quantity, price, promotionPercent, total});
    updateTotalPrice();
}





    private void UpdateToTable() {
    int row = tblProduct.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng cần cập nhật");
        return;
    }

    int quantity = (int) spnQuantity.getValue();
    if (quantity < 1) {
        JOptionPane.showMessageDialog(this, "Số lượng phải >= 1");
        return;
    }

    double price = parseDoubleSafe(txtUnitprice.getText());

    // ✅ Xử lý promotion đúng cách
    double promotionPercent = 0.0;
    if (ckxPromotion.isSelected()) {
        promotionPercent = parseDoubleSafe(txtPromotion.getText());
    }

    double promotion = promotionPercent / 100.0;
    double total = quantity * price * (1 - promotion);

    // ✅ Cập nhật dữ liệu vào bảng
    model.setValueAt(txtProductcode.getText(), row, 0);
    model.setValueAt(txtProductname.getText(), row, 1);
    model.setValueAt(quantity, row, 2);
    model.setValueAt(price, row, 3);
    model.setValueAt(promotionPercent, row, 4); // cột Promotion (%)
    model.setValueAt(total, row, 5);            // cột Tổng

    updateTotalPrice();
}




    private void DeleteToTable() {
    int row = tblProduct.getSelectedRow();
    if (row == -1) {
        JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng để xóa");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa dòng đã chọn?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) return;

    model.removeRow(row);     // Xoá dòng đã chọn
    clearForm();              // Xoá dữ liệu trong form
    updateTotalPrice();       // Tính lại tổng tiền
}



    private void RefreshToTable() {
    // Xoá toàn bộ dữ liệu trong bảng
    model.setRowCount(0);

    // Làm mới form
    clearForm();

    // Reset tổng tiền
    updateTotalPrice();
}




    private void updateTotalPrice() {
        double total = 0;
        for (int i = 0; i < model.getRowCount(); i++) {
            double itemTotal = (double) model.getValueAt(i, 5);
            if (itemTotal < 0) {
                JOptionPane.showMessageDialog(this, "Có sản phẩm có số tiền âm!");
                return;
            }
            total += itemTotal;
        }
        txtTotalunitprice.setText(String.valueOf(total));
    }
    
    private void paymentOrder() {
    int rowCount = model.getRowCount();
    if (rowCount == 0) {
        JOptionPane.showMessageDialog(this, "Không có sản phẩm nào để thanh toán!");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn thanh toán?", "Xác nhận", JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) return;

    // Tạo đơn hàng (lưu vào lịch sử)
    for (int i = 0; i < rowCount; i++) {
        Object[] rowData = new Object[model.getColumnCount()];
        for (int j = 0; j < model.getColumnCount(); j++) {
            rowData[j] = model.getValueAt(i, j);
        }

        // Thêm vào bảng lịch sử (nếu có)
        if (historyModel != null) {
            historyModel.addRow(rowData);
        }

        // Bạn cũng có thể lưu vào CSDL tại đây nếu cần
    }

    // Xóa bảng hiện tại (đã thanh toán xong)
    model.setRowCount(0);
    updateTotalPrice();

    JOptionPane.showMessageDialog(this, "Thanh toán thành công!");

    // ✅ MỞ GIAO DIỆN BillManagerUI SAU KHI THANH TOÁN
    new BillManagerUi().setVisible(true);
    this.dispose(); // đóng giao diện hiện tại (Sale)
}

    
    
    // === Hàm parse an toàn ===
    private double parseDoubleSafe(String text) {
        try {
            return Double.parseDouble(text.trim());
        } catch (Exception e) {
            return 0;
        }
    }






    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtDutyofficer = new javax.swing.JTextField();
        btnHome = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        spnQuantity = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProduct = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtUnitprice = new javax.swing.JTextField();
        ckxPromotion = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        txtTotalunitprice = new javax.swing.JTextField();
        btnMore = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnPayment = new javax.swing.JButton();
        btnEscape = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtProductname = new javax.swing.JTextField();
        txtPromotion = new javax.swing.JTextField();
        txtProductcode = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(239, 239, 239));

        jPanel2.setBackground(new java.awt.Color(221, 0, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtDutyofficer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDutyofficerActionPerformed(evt);
            }
        });

        btnHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon Application/Home.png"))); // NOI18N
        btnHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHomeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Duty officer");

        btnLogout.setText("Log out");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 408, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txtDutyofficer, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnLogout)
                .addGap(12, 12, 12))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDutyofficer)
                    .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3)
                    .addComponent(btnHome, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(221, 0, 49));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel2.setText("Product name");

        jLabel4.setText("Quantity");

        jLabel5.setText("Product code");

        spnQuantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spnQuantityMouseClicked(evt);
            }
        });

        tblProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Product code ", "Product name", "Quantity", "Unit price", "Total unit price", "Promotion"
            }
        ));
        tblProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProduct);

        jLabel1.setText("Unit price");

        txtUnitprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUnitpriceActionPerformed(evt);
            }
        });

        ckxPromotion.setText("Promotion");
        ckxPromotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckxPromotionActionPerformed(evt);
            }
        });

        jLabel6.setText("Total unit price");

        txtTotalunitprice.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalunitpriceActionPerformed(evt);
            }
        });

        btnMore.setText("More");
        btnMore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoreActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnPayment.setText("Payment");
        btnPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPaymentActionPerformed(evt);
            }
        });

        btnEscape.setText("Escape");
        btnEscape.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEscapeActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon Application/OIP (2).jpg"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 48)); // NOI18N
        jLabel9.setText("Payment");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalunitprice, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ckxPromotion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPromotion))
                            .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtUnitprice, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                            .addComponent(txtProductname)
                            .addComponent(txtProductcode))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(71, 71, 71))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(103, 103, 103)
                                .addComponent(btnMore)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addGap(18, 18, 18)
                                .addComponent(btnDelete)
                                .addGap(18, 18, 18)
                                .addComponent(btnRefresh))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(258, 258, 258)
                                .addComponent(jLabel9)))
                        .addGap(0, 335, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEscape)
                        .addGap(18, 18, 18)
                        .addComponent(btnPayment))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(29, 29, 29)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtProductcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtProductname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(spnQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtUnitprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 41, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPromotion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ckxPromotion)))))
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMore)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnRefresh))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotalunitprice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnPayment)
                    .addComponent(btnEscape))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ckxPromotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckxPromotionActionPerformed
        // TODO add your handling code here:
    // Đảm bảo txtPromotion được bật hoặc tắt tùy thuộc vào việc ckxPromotion có được tick hay không
    txtPromotion.setEnabled(ckxPromotion.isSelected());

    // Nếu không tick, xóa nội dung trong txtPromotion
    if (!ckxPromotion.isSelected()) {
        txtPromotion.setText("");
    }


    }//GEN-LAST:event_ckxPromotionActionPerformed

    private void txtTotalunitpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalunitpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalunitpriceActionPerformed

    private void btnHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHomeActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_btnHomeActionPerformed

    private void btnPaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPaymentActionPerformed
        // TODO add your handling code here:
        paymentOrder();
    }//GEN-LAST:event_btnPaymentActionPerformed

    private void txtDutyofficerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDutyofficerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDutyofficerActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txtUnitpriceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUnitpriceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUnitpriceActionPerformed

    private void spnQuantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spnQuantityMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_spnQuantityMouseClicked

    private void btnMoreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoreActionPerformed

        double promotion = 0.0;

if (ckxPromotion.isSelected()) {
    try {
        double promoInput = Double.parseDouble(txtPromotion.getText());
        if (promoInput < 0 || promoInput > 100) {
            JOptionPane.showMessageDialog(this, "Khuyến mãi phải từ 0 đến 100%");
            return;
        }
        promotion = promoInput / 100.0;
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Khuyến mãi không hợp lệ! Vui lòng nhập số từ 0 đến 100.");
        return;
    }
}

        MoreToTable();
    }//GEN-LAST:event_btnMoreActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
UpdateToTable();
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
DeleteToTable();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
RefreshToTable();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void tblProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tblProductMouseClicked

    private void btnEscapeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscapeActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_btnEscapeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        SwingUtilities.invokeLater(() ->{ 
            new Sale().setVisible(true);
    });
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sale.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                /*                new Sale(this, rootPaneCheckingEnabled).setVisible(true);*/
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEscape;
    private javax.swing.JButton btnHome;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnMore;
    private javax.swing.JButton btnPayment;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JCheckBox ckxPromotion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner spnQuantity;
    private javax.swing.JTable tblProduct;
    private javax.swing.JTextField txtDutyofficer;
    private javax.swing.JTextField txtProductcode;
    private javax.swing.JTextField txtProductname;
    private javax.swing.JTextField txtPromotion;
    private javax.swing.JTextField txtTotalunitprice;
    private javax.swing.JTextField txtUnitprice;
    // End of variables declaration//GEN-END:variables
}

