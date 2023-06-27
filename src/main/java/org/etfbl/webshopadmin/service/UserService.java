package org.etfbl.webshopadmin.service;

import org.etfbl.webshopadmin.dao.UserDao;
import org.etfbl.webshopadmin.dto.User;

import java.util.List;

public class UserService {

    public List<User> findAll(){
        return UserDao.findAll();
    }

    public User findById(Long id){
        return UserDao.findById(id);
    }
}
