package com.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       // Tự sinh getter, setter, toString, equals, hashCode
@NoArgsConstructor          // Sinh constructor không tham số
@AllArgsConstructor         // Sinh constructor đầy đủ tham số
@Builder                   // Sinh builder pattern
public class User {

    private String username;
    private String password;
    private boolean enabled;
    private String fullname;
    @Builder.Default
    private String photo = "photo.png";
    private boolean manager;
}
