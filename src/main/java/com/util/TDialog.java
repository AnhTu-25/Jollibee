package com.util;

import javax.swing.JOptionPane;

public class TDialog {
    public static void alert(String message){
        TDialog.alert(message, "Thông báo!");
    }
    public static void alert(String message, String title){
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }
    
    public static boolean confirm(String message){
        return TDialog.confirm(message, "Xác nhận!");
    }
    public static boolean confirm(String message, String title){
        int result = JOptionPane.showConfirmDialog(null, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return (result == JOptionPane.YES_OPTION);
    }
    
    public static String prompt(String message){
        return TDialog.prompt(message, "Nhập vào!");
    }
    public static String prompt(String message, String title){
        return JOptionPane.showInputDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}