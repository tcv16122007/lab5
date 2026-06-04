package com.lab5.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lab5.entity.User;
import com.lab5.manager.UserManager;

public class UserServlet extends HttpServlet {
    private UserManager userManager;

    @Override
    public void init() {
        userManager = new UserManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Đảm bảo encoding UTF-8 cho request và response
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");
        if (action == null)
            action = "list";

        switch (action) {
            case "create":
                req.getRequestDispatcher("/WEB-INF/jsp/create.jsp").forward(req, resp);
                break;
            case "edit":
                String id = req.getParameter("id");
                User user = userManager.findById(id);
                req.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(req, resp);
                break;
            case "delete":
                deleteUser(req, resp);
                break;
            case "searchFpt":
                searchFptEmail(req, resp);
                break;
            default:
                listUsers(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Đảm bảo encoding UTF-8 cho request và response
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String action = req.getParameter("action");
        switch (action) {
            case "create":
                createUser(req, resp);
                break;
            case "update":
                updateUser(req, resp);
                break;
            default:
                listUsers(req, resp);
                break;
        }
    }

    private void listUsers(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> list = userManager.getAllUsers();
        req.setAttribute("users", list);
        req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp);
    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String id = req.getParameter("id");
        String password = req.getParameter("password");
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        boolean admin = req.getParameter("admin") != null;

        User user = new User(id, password, email, fullname, admin);
        userManager.create(user);
        resp.sendRedirect("user?action=list");
    }

    private void updateUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String id = req.getParameter("id");
        User user = userManager.findById(id);
        if (user != null) {
            user.setPassword(req.getParameter("password"));
            user.setFullname(req.getParameter("fullname"));
            user.setEmail(req.getParameter("email"));
            user.setAdmin(req.getParameter("admin") != null);
            userManager.update(user);
        }
        resp.sendRedirect("user?action=list");
    }

    private void deleteUser(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String id = req.getParameter("id");
        userManager.deleteById(id);
        resp.sendRedirect("user?action=list");
    }

    private void searchFptEmail(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> list = userManager.findNonAdminWithFptEmailList();
        req.setAttribute("resultList", list);
        req.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        if (userManager != null)
            userManager.close();
    }
}