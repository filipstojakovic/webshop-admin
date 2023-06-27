package org.etfbl.webshopadmin.service;

import org.etfbl.webshopadmin.dao.CategoryDao;
import org.etfbl.webshopadmin.dto.Category;

import java.util.List;

public class CategoryService {

    public List<Category> findAll(){
        return CategoryDao.findAll();
    }
}
