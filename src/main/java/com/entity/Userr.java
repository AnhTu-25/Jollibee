package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // Tự sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor          // Sinh constructor không tham số
@AllArgsConstructor         // Sinh constructor đầy đủ tham số
@Builder                   // Sinh builder pattern
public class Userr {

    private String username;
    private String password;
    private boolean enabled;
    private String fullname;
    @Builder.Default
    private String photo = "photo.png";
    private boolean manager;

    public User(String admin, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getUsername() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Object getPassword() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setPassword(String newpass) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
