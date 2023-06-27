package org.etfbl.webshopadmin.service;

import org.etfbl.webshopadmin.bean.UserAdminBean;
import org.etfbl.webshopadmin.dao.UserAdminDao;
import org.etfbl.webshopadmin.dto.UserAdmin;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserAdminService {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserAdminBean loginUser(String username, String password) {
        if (username == null || password == null) {
            return null;
        }

        UserAdmin user = UserAdminDao.selectByUsername(username);
        if (user == null) return null;

        if (passwordEncoder.matches(password, user.getPassword())) {
            return new UserAdminBean(user.getId(), user.getUsername(), user.getFirstName(), user.getLastName(), true);
        }
        return null;
    }

}
