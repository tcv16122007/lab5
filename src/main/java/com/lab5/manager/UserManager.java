package com.lab5.manager;

import java.util.List;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import com.lab5.entity.User;

public class UserManager {
    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("PolyOE");
    private final EntityManager em = factory.createEntityManager();

    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");
    private static final Pattern PHONE_PATTERN = 
        Pattern.compile("^(0|\\+84)([0-9]{9})$");

    public static boolean isValidEmail(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isValidVietnamPhone(String phone) {
        return phone != null && PHONE_PATTERN.matcher(phone).matches();
    }

    public void findAll() {
        String jpql = "SELECT o FROM User o";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        List<User> list = query.getResultList();
        list.forEach(user -> System.out.println(user.getFullname() + ": " + user.getAdmin()));
    }

    public User findById(String id) {
        return em.find(User.class, id);
    }

    public void create(User user) {
        if (!isValidEmail(user.getEmail())) {
            System.err.println("Email không hợp lệ: " + user.getEmail());
            return;
        }
        if (!isValidVietnamPhone(user.getPassword())) {
            System.err.println("Số điện thoại không hợp lệ (phải bắt đầu bằng 0 hoặc +84): " + user.getPassword());
            return;
        }
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
            System.out.println("Thêm user thành công!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Lỗi khi thêm user: " + e.getMessage());
        }
    }

    public void update(User user) {
        if (!isValidEmail(user.getEmail())) {
            System.err.println("Email không hợp lệ: " + user.getEmail());
            return;
        }
        if (!isValidVietnamPhone(user.getPassword())) {
            System.err.println("Số điện thoại không hợp lệ: " + user.getPassword());
            return;
        }
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
            System.out.println("Cập nhật thành công!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Lỗi khi cập nhật user: " + e.getMessage());
        }
    }

    public void deleteById(String id) {
        User user = findById(id);
        if (user != null) {
            try {
                em.getTransaction().begin();
                em.remove(user);
                em.getTransaction().commit();
                System.out.println("Xóa thành công!");
            } catch (Exception e) {
                em.getTransaction().rollback();
                System.err.println("Lỗi khi xóa user: " + e.getMessage());
            }
        } else {
            System.out.println("Không tìm thấy user với id = " + id);
        }
    }

    public void findNonAdminWithFptEmail() {
        String jpql = "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin = :role";
        TypedQuery<User> query = em.createQuery(jpql, User.class);
        query.setParameter("search", "%@fpt.edu.vn");
        query.setParameter("role", false);
        List<User> list = query.getResultList();
        if (list.isEmpty()) {
            System.out.println("Không có user nào thỏa mãn.");
        } else {
            System.out.println("Danh sách user (không admin, email @fpt.edu.vn):");
            list.forEach(u -> System.out.println("Họ tên: " + u.getFullname() + " - Email: " + u.getEmail()));
        }
    }

    public List<User> getAllUsers() {
        return em.createQuery("SELECT o FROM User o", User.class).getResultList();
    }

    public List<User> findNonAdminWithFptEmailList() {
        TypedQuery<User> query = em.createQuery(
            "SELECT o FROM User o WHERE o.email LIKE :search AND o.admin = :role", User.class);
        query.setParameter("search", "%@fpt.edu.vn");
        query.setParameter("role", false);
        return query.getResultList();
    }

    public void close() {
        em.close();
        factory.close();
    }
}