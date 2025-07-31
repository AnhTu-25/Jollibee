/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.DAO;

/**
 *
 * @author Admin
 */
import com.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAOImpl implements UserDAO {

    // Danh sách giả lập người dùng
    private final List<User> userList;
    private final User currentUser;

    public UserDAOImpl() {
        userList = new ArrayList<>();

        // Giả lập dữ liệu người dùng
        userList.add(new User("admin", "123"));
        userList.add(new User("user1", "abc"));
        userList.add(new User("user2", "xyz"));

        // Giả lập người đang đăng nhập là "admin"
        currentUser = userList.get(0);
    }

    @Override
    public void update(User user) {
        for (User u : userList) {
            if (u.getUsername().equals(user.getUsername())) {
                u.setPassword((String) user.getPassword());
                if (Objects.equals(user.getUsername(), currentUser.getUsername())) {
                    currentUser.setPassword((String) user.getPassword());
                }
                System.out.println("Cập nhật mật khẩu thành công cho: " + user.getUsername());
                return;
            }
        }
        System.out.println("Không tìm thấy người dùng để cập nhật.");
    }

    public User getUser() {
        return currentUser;
    }

    @Override
    public User create(User entity) {
        userList.add(entity);
        return entity;
    }

    @Override
    public void deleteById(String id) {
        userList.removeIf(u -> u.getUsername().equals(id));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(userList);
    }

    @Override
    public User findById(String id) {
        for (User u : userList) {
            if (u.getUsername().equals(id)) {
                return u;
            }
        }
        return null;
    }
}


