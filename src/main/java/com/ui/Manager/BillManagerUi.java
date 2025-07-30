/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ui.Manager;

import com.Dao.BillDAO;
import com.Dao.BillDAOImpl;
import com.Dao.BillDetailDAO;
import com.Dao.BillDetailDAOImpl;
import com.entity.BillDetail;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import com.entity.Bill;

public class BillManagerUi extends javax.swing.JFrame {

    private BillDAO billDao = new BillDAOImpl();
    private BillDetailDAO billDetailDao = new BillDetailDAOImpl();
    private Bill bill;
    private List<BillDetail> billDetails = new ArrayList<>();
    private DefaultTableModel tableModel;

    public BillManagerUi() {
        initComponents();
        loadBillData();
        addEventHandlers();
    }



    private void loadBillData() {
        // Giả sử lấy hóa đơn đầu tiên từ cơ sở dữ liệu
      //  bill = billDao.findById(1002L); // Ví dụ với billId = 1002
        if (bill != null) {
            txtId.setText(String.valueOf(bill.getId()));
            txtCardId.setText(String.valueOf(bill.getCardId()));
            txtCheckin.setText(new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(bill.getCheckin()));
            txtUsername.setText(bill.getUsername());
            txtStatus.setText(bill.getStatus()== 1 ? "Đang sử dụng" : "Trống");
            if (bill.getCheckout()!= null) {
                txtCheckout.setText(new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").format(bill.getCheckout()));
            }
            fillBillDetails();
        }
    }

    private void fillBillDetails() {
        tableModel.setRowCount(0); // Xóa tất cả hàng hiện tại
        billDetails = billDetailDao.findByBillId(bill.getId());
        for (BillDetail detail : billDetails) {
            tableModel.addRow(new Object[]{
                detail.getId(),
                detail.getTenMonAn(),
                detail.getDonGia(),
                detail.getSoLuong(),
                detail.getThanhTien()
            });
        }
    }

    private void addEventHandlers() {
        btnAdd.addActionListener(e -> themMon());
        btnRemove.addActionListener(e -> xoaMon());
        btnCheckout.addActionListener(e -> thanhToan());
        btnCancel.addActionListener(e -> huyPhieu());
    }

    private void themMon() {
        String monAn = JOptionPane.showInputDialog(this, "Nhập món ăn:");
        if (monAn != null && !monAn.isEmpty()) {
            double donGia = Double.parseDouble(JOptionPane.showInputDialog(this, "Nhập đơn giá:"));
            int soLuong = Integer.parseInt(JOptionPane.showInputDialog(this, "Nhập số lượng:"));
            BillDetail detail = new BillDetail();
            detail.setId(bill.getId());
            detail.setMonAn(monAn);
            detail.setDonGia(donGia);
            detail.setSoLuong(soLuong);
            detail.setThanhTien(donGia * soLuong);
            billDetailDao.insert(detail);
            fillBillDetails();
        }
    }

    private void xoaMon() {
        int selectedRow = tblBillDetails.getSelectedRow();
        if (selectedRow >= 0) {
            Long billId = (Long) tableModel.getValueAt(selectedRow, 0);
            billDetailDao.deleteById(billId);
            fillBillDetails();
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một món để xóa!");
        }
    }

    private void thanhToan() {
        bill.setCheckout(new java.util.Date());
        bill.setStatus(0); // Trạng thái trống sau khi thanh toán
        billDao.update(bill);
        loadBillData();
        JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
    }

    private void huyPhieu() {
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn hủy phiếu?", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            billDao.deleteById(bill.getId());
            billDetails.clear();
            tableModel.setRowCount(0);
            txtId.setText("");
            btnCancel.setText("");
            txtCheckin.setText("");
            txtUsername.setText("");
            txtStatus.setText("");
            txtCheckout.setText("");
            JOptionPane.showMessageDialog(this, "Hủy phiếu thành công!");
        }
    }
    private void updateQuantity() {
    // TODO: Viết code cập nhật số lượng ở đây
    System.out.println("updateQuantity called");
}

private void removeDrinks() {
    // TODO: Viết code xóa thức uống khỏi hóa đơn
    System.out.println("removeDrinks called");
}

private void showDrinkDialog() {
    // TODO: Viết code hiển thị form chọn món
    System.out.println("showDrinkDialog called");
}

private void checkout() {
    // TODO: Viết code thanh toán hóa đơn
    System.out.println("checkout called");
}

private void cancel() {
    // TODO: Viết code hủy thao tác hiện tại
    System.out.println("cancel called");
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtCardId = new javax.swing.JTextField();
        txtCheckin = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        txtStatus = new javax.swing.JTextField();
        txtCheckout = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblBillDetails = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnRemove = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnCheckout = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new java.awt.GridLayout(0, 3, 5, 5));

        jLabel2.setText("Mã phiếu");
        jLabel2.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel2);

        jLabel1.setText("Thẻ số");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel1);

        jLabel4.setText("Thời điểm đặt hàng");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel4);

        txtId.setEditable(false);
        jPanel1.add(txtId);

        txtCardId.setEditable(false);
        jPanel1.add(txtCardId);

        txtCheckin.setEditable(false);
        jPanel1.add(txtCheckin);

        jLabel3.setText("Nhân viên");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel1.add(jLabel3);

        jLabel5.setText("Trạng thái");
        jPanel1.add(jLabel5);

        jLabel6.setText("Thời điểm thanh toán");
        jPanel1.add(jLabel6);

        txtUsername.setEditable(false);
        jPanel1.add(txtUsername);

        txtStatus.setEditable(false);
        jPanel1.add(txtStatus);

        txtCheckout.setEditable(false);
        jPanel1.add(txtCheckout);

        tblBillDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã phiếu", "Món Ăn", "Đơn giá", "Giảm giá", "Số lượng", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblBillDetails.setRowHeight(25);
        tblBillDetails.setRowMargin(1);
        tblBillDetails.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBillDetails.setShowGrid(true);
        tblBillDetails.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBillDetailsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBillDetails);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel2.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        btnRemove.setText("Xóa Món");
        btnRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActionPerformed(evt);
            }
        });
        jPanel2.add(btnRemove);

        btnAdd.setText("Thêm Món");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnAdd);

        jPanel3.add(jPanel2, java.awt.BorderLayout.LINE_START);

        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        btnCheckout.setText("Thanh toán");
        btnCheckout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckoutActionPerformed(evt);
            }
        });
        jPanel4.add(btnCheckout);

        btnCancel.setText("Hủy phiếu");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancel);

        jPanel3.add(jPanel4, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblBillDetailsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBillDetailsMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            this.updateQuantity();
        }
    }//GEN-LAST:event_tblBillDetailsMouseClicked

    private void btnRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActionPerformed
        // TODO add your handling code here:
        this.removeDrinks();
    }//GEN-LAST:event_btnRemoveActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
        this.showDrinkDialog();
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCheckoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckoutActionPerformed
        // TODO add your handling code here:
        this.checkout();
    }//GEN-LAST:event_btnCheckoutActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        // TODO add your handling code here:
        this.cancel();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillManagerUi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnRemove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBillDetails;
    private javax.swing.JTextField txtCardId;
    private javax.swing.JTextField txtCheckin;
    private javax.swing.JTextField txtCheckout;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
