package org.etfbl.webshopadmin.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.etfbl.webshopadmin.bean.CategoryBean;
import org.etfbl.webshopadmin.bean.UserAdminBean;
import org.etfbl.webshopadmin.dao.CategoryDao;
import org.etfbl.webshopadmin.dao.UserDao;
import org.etfbl.webshopadmin.dto.Category;
import org.etfbl.webshopadmin.dto.User;
import org.etfbl.webshopadmin.service.CategoryService;
import org.etfbl.webshopadmin.service.LogService;
import org.etfbl.webshopadmin.service.UserAdminService;
import org.etfbl.webshopadmin.service.UserService;
import org.etfbl.webshopadmin.util.Utils;

import java.io.IOException;

@WebServlet("/")
public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    public static final String NOTIFICATION = "notification";

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String address = "/WEB-INF/pages/index.jsp";
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        session.setAttribute(NOTIFICATION, "");

        if (action == null || "".equals(action)) {
            session.setAttribute("userAdminBean", new UserAdminBean()); //TODO: mozda ce smetati
            address = "/WEB-INF/pages/index.jsp";
        } else if ("logout".equals(action)) {
            session.invalidate();
            address = "/WEB-INF/pages/index.jsp";
        } else if (action.equals("login")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserAdminService userAdminService = new UserAdminService();
            UserAdminBean userAdminBean = userAdminService.loginUser(username, password);
            if (userAdminBean != null) {
                session.setAttribute("userAdminBean", userAdminBean);
                session.setAttribute("categoryService", new CategoryService());
                address = "/WEB-INF/pages/categories.jsp";
                session.getAttribute("");
            } else {
                session.setAttribute(NOTIFICATION, "Wrong username or password");
            }
        } else {
            UserAdminBean userBean = (UserAdminBean) session.getAttribute("userAdminBean");
            UserService userService = new UserService();
            session.setAttribute("userService", userService);
            CategoryService categoryService = new CategoryService();
            session.setAttribute("categoryService", categoryService);
            LogService logService = new LogService();
            session.setAttribute("logService", logService);

            if (userBean == null || !userBean.isLoggedIn()) {
                address = "/WEB-INF/pages/index.jsp";
            } else {
                switch (action) {
                    case "categories" -> {
                        address = "/WEB-INF/pages/categories.jsp";
                    }
                    case "createCategory" -> {
                        address = "/WEB-INF/pages/create_category.jsp";
                    }
                    case "insertCategory" -> {
                        String name = request.getParameter("name");
                        String parentCategoryId = request.getParameter("parentCategoryId");
                        if (Utils.isBlank(name)) {
                            session.setAttribute(NOTIFICATION, "Error! Missing field(s)");
                            address = "/WEB-INF/pages/create_category.jsp";
                        } else {
                            Long parentId = Utils.isBlank(parentCategoryId) ? null : Long.parseLong(parentCategoryId);
                            try {
                                CategoryDao.insertCategory(name, parentId);
                                session.setAttribute(NOTIFICATION, "Category created");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                session.setAttribute(NOTIFICATION, "Error creating category");
                            }
                            address = "/WEB-INF/pages/create_category.jsp";
                        }
                    }
                    case "editCategory" -> {
                        Long categoryId = Long.valueOf(request.getParameter("id"));
                        Category category = CategoryDao.findById(categoryId);
                        if (category == null) {
                            session.setAttribute(NOTIFICATION, "Error finding category");
                            address = "/WEB-INF/pages/error.jsp";
                        } else {
                            CategoryBean categoryBean = new CategoryBean(categoryId, category.getName(),
                                    category.getParentCategoryId());
                            session.setAttribute("categoryBean", categoryBean);
                            address = "/WEB-INF/pages/edit_category.jsp";
                        }
                    }
                    case "saveEditCategory" -> {
                        Long categoryId = Long.valueOf(request.getParameter("id"));
                        String name = request.getParameter("name");
                        String parentId = request.getParameter("categoryParentId");
                        if (Utils.isBlank(name)) {
                            session.setAttribute(NOTIFICATION, "Error! Missing field(s)");
                            address = "/WEB-INF/pages/edit_category.jsp";
                        } else {
                            Long categoryParentId = Utils.isBlank(parentId) ? null : Long.parseLong(parentId);
                            try {
                                Category category = CategoryDao.updateCategory(categoryId, name, categoryParentId);
                                session.setAttribute("categoryBean", new CategoryBean(categoryId, category.getName(),
                                        category.getParentCategoryId()));
                                session.setAttribute(NOTIFICATION, "Category updated");
                            } catch (Exception ex) {
                                session.setAttribute(NOTIFICATION, "Error updating");
                                ex.printStackTrace();
                            }

                            address = "/WEB-INF/pages/edit_category.jsp";
                        }
                    }
                    case "deleteCategory" -> {
                        address = "/WEB-INF/pages/categories.jsp";
                        Long categoryId = Long.valueOf(request.getParameter("id"));
                        try {
                            CategoryDao.delete(categoryId);
                            session.setAttribute(NOTIFICATION, "Category deleted");

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            session.setAttribute(NOTIFICATION, "Error deleting category");
                            address = "/WEB-INF/pages/create_category.jsp?action=error";
                        }
                    }
                    case "users" -> {
                        address = "/WEB-INF/pages/users.jsp";
                    }
                    case "createUser" -> {
                        session.setAttribute("userBean", new User());
                        address = "/WEB-INF/pages/create_user.jsp";
                    }
                    case "insertUser" -> {
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String firstname = request.getParameter("firstname");
                        String lastname = request.getParameter("lastname");
                        String email = request.getParameter("email");
                        String city = request.getParameter("city");
                        if (Utils.isBlank(username) || Utils.isBlank(password) || Utils.isBlank(firstname) || Utils.isBlank(lastname) || Utils.isBlank(email) || Utils.isBlank(city)) {
                            session.setAttribute(NOTIFICATION, "Error! Missing field(s)");
                            address = "/WEB-INF/pages/create_user.jsp";
                        } else {
                            User newUser = new User(null, username, password, firstname, lastname, email, city);
                            try {
                                UserDao.insertUser(newUser);
                                session.setAttribute(NOTIFICATION, "User created");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                session.setAttribute(NOTIFICATION, "Error creating user");
                            }
                            address = "/WEB-INF/pages/create_user.jsp";
                        }
                    }
                    case "editUser" -> {
                        Long userId = Long.valueOf(request.getParameter("id"));
                        User user = UserDao.findById(userId);
                        if (user == null) {
                            session.setAttribute(NOTIFICATION, "Error finding user");
                            address = "/WEB-INF/pages/error.jsp";
                        } else {
                            session.setAttribute("user", user);
                            address = "/WEB-INF/pages/edit_user.jsp";
                        }
                    }
                    case "saveEditUser" -> {
                        Long userId = Long.valueOf(request.getParameter("id"));
                        String username = request.getParameter("username");
                        String firstname = request.getParameter("firstname");
                        String lastname = request.getParameter("lastname");
                        String email = request.getParameter("email");
                        String city = request.getParameter("city");
                        if (Utils.isBlank(username) || Utils.isBlank(firstname) || Utils.isBlank(lastname) || Utils.isBlank(email) || Utils.isBlank(city)) {
                            session.setAttribute(NOTIFICATION, "Error! Missing field(s)");
                            address = "/WEB-INF/pages/edit_user.jsp";
                        } else {
                            try {
                                User user = UserDao.updateUser(userId, username, firstname, lastname, email, city);
                                session.setAttribute("user", user);
                                session.setAttribute(NOTIFICATION, "User updated");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                session.setAttribute(NOTIFICATION, "Error while updating user!");
                            }
                            address = "/WEB-INF/pages/edit_user.jsp";
                        }
                    }
                    case "deleteUser" -> {
                        Long userId = Long.valueOf(request.getParameter("id"));
                        UserDao.delete(userId);
                        address = "/WEB-INF/pages/users.jsp";
                    }
                    case "logs" -> address = "/WEB-INF/pages/logs.jsp";
                    default -> address = "/WEB-INF/pages/error.jsp";
                }
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(address);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}
