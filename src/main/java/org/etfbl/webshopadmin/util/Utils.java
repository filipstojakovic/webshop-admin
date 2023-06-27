package org.etfbl.webshopadmin.util;

import jakarta.servlet.http.HttpSession;
import org.etfbl.webshopadmin.controller.MainController;

public class Utils {

    public static final String ERROR = "error";

    public static boolean isError(HttpSession session) {
        String notification = (String) session.getAttribute(MainController.NOTIFICATION);
        if (isBlank(notification)) return false;
        return notification.toLowerCase().contains(ERROR);
    }

    public static boolean isBlank(String str) {
        return str == null || str.isBlank();
    }

    public static String getErrorClass(HttpSession session) {
        return isError(session) ? "is-error" : "not-error";
    }
}
