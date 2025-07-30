/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.util;

import com.entity.User;

/**
 *
 * @author Anh Tu
 */
public class Tpass {

    public static User user = User.builder()
            .username("Nguyenanhtu")
            .password("1211")
            .enabled(true)
            .manager(true)
            .fullname("Nguyen anh tu")
            .photo("jlb1.jpg")
            .build(); // biến user này sẽ được thay thế sau khi đăng nhập

    public static class user {

        public user() {
        }

        public static class isManager {

            public isManager() {
            }
        }
    }
}
