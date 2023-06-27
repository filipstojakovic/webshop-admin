package org.etfbl.webshopadmin.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.etfbl.webshopadmin.bean.UserAdminBean;
import org.etfbl.webshopadmin.dao.UserDao;
import org.etfbl.webshopadmin.dto.User;
import org.etfbl.webshopadmin.service.CategoryService;
import org.etfbl.webshopadmin.service.LogService;
import org.etfbl.webshopadmin.service.UserAdminService;
import org.etfbl.webshopadmin.service.UserService;

import java.io.IOException;

@WebServlet("/")
public class MainController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void init() {
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();

        String address = "/WEB-INF/pages/index.jsp";
        String action = request.getParameter("action");
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
                    case "users" -> {
                        address = "/WEB-INF/pages/users.jsp";
                    }
                    case "createUser" -> {
                        session.setAttribute("userBean", new User());
                        address = "/WEB-INF/pages/create_user.jsp";
                    }
                    case "addNewUser" -> {
                        //TODO: do check
                        address = "/WEB-INF/pages/users.jsp";
                    }
                    case "editUser" -> {
                        Long userId = Long.valueOf(request.getParameter("id"));
                        address = "/WEB-INF/pages/edit_user.jsp";
                    }
                    case "deleteUser" -> {
                        Long userId = Long.valueOf(request.getParameter("id"));
                        UserDao.delete(userId);
                        address = "/WEB-INF/pages/users.jsp";
                    }
                    case "logs" -> {
                        address = "/WEB-INF/pages/logs.jsp";
                    }
                    default -> {
                        address = "/WEB-INF/pages/error.jsp";
                    }
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
