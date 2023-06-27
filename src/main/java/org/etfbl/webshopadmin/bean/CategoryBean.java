package org.etfbl.webshopadmin.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable {

    private static final long serialVersionUID = 1839861293452678486L;
    private Long id;
    private String name;
    private Long parentCategoryId;

    public CategoryBean() {
    }

    public CategoryBean(Long id, String name, Long parentCategoryId) {
        this.id = id;
        this.name = name;
        this.parentCategoryId = parentCategoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
