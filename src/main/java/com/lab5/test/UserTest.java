package com.lab5.test;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import com.lab5.entity.User;
import com.lab5.manager.UserManager;

public class UserTest {
    public static void main(String[] args) {
        // Đảm bảo console xuất được UTF-8 (hỗ trợ tiếng Việt)
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.err.println("Không thể set encoding UTF-8 cho console: " + e.getMessage());
        }

        UserManager um = new UserManager();
        try {
            // Test findAll
            System.out.println("=== findAll ===");
            um.findAll();

            // Test findById
            System.out.println("\n=== findById U02 ===");
            User u = um.findById("U02");
            if (u != null) {
                System.out.println(u.getFullname() + " - " + (u.getAdmin() ? "Admin" : "Không admin"));
            }

            // Test create (hợp lệ)
            System.out.println("\n=== Tạo user mới (hợp lệ) ===");
            User newUser = new User("U05", "0987654321", "newuser@fpt.edu.vn", "Nguyễn Văn E", false);
            um.create(newUser);

            // Test create (email sai)
            System.out.println("\n=== Tạo user với email sai ===");
            User badEmail = new User("U06", "0909123456", "bademail", "Sai Email", false);
            um.create(badEmail);

            // Test update
            System.out.println("\n=== Cập nhật user U05 ===");
            User updateUser = um.findById("U05");
            if (updateUser != null) {
                updateUser.setFullname("Nguyễn Văn E (đã sửa)");
                updateUser.setEmail("e@yahoo.com");
                um.update(updateUser);
            }

            // Bài 3
            System.out.println("\n=== Bài 3: Tìm user email @fpt.edu.vn và không admin ===");
            um.findNonAdminWithFptEmail();

            // Test delete
            System.out.println("\n=== Xóa user U05 ===");
            um.deleteById("U05");

        } finally {
            um.close();
        }
    }
}