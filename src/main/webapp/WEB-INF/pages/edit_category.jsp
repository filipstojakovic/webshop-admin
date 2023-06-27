<%@ page import="org.etfbl.webshopadmin.util.Utils" %>
<%@ page import="org.etfbl.webshopadmin.dao.CategoryDao" %>
<%@ page import="org.etfbl.webshopadmin.dto.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="categoryBean" type="org.etfbl.webshopadmin.bean.CategoryBean" scope="session"/>
<jsp:useBean id="userAdminBean" type="org.etfbl.webshopadmin.bean.UserAdminBean" scope="session"/>

<html>
<head>
    <title>Edit User</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <link href="styles/style.css" type="text/css" rel="stylesheet">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</head>
<body>
<div class="mdl-layout mdl-layout--fixed-header">
    <%@include file="../header.jsp" %>
    <main class="mdl-layout__content">
        <div class="page-content">
            <h4>Edit Category</h4>
            <p style="color:<%=Utils.isError(session)? "red" : "green"%>"><%=session.getAttribute("notification")%>
            </p>
            <form action="?action=saveEditCategory&id=<%=categoryBean.getId()%>" method="post" class="form-container">
                <form action="?action=insertCategory" method="post" class="form-container">
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <select class="mdl-textfield__input" id="categoryParentId" name="categoryParentId">
                            <option></option>
                            <% for (Category category : CategoryDao.findAll()) {
                                if (!category.getId().equals(categoryBean.getId())){
                            %>
                            <option value="<%=category.getId()%>"
                                    <% if (categoryBean.getParentCategoryId().equals(category.getId())) { %>
                                        selected
                                    <% } %>>
                                <%=category.getName()%>
                            </option>
                            <%}
                            }%>
                        </select>
                        <label class="mdl-textfield__label" for="categoryParentId">Parent Category</label>
                    </div>
                    <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                        <label class="mdl-textfield__label" for="name">Category name</label>
                        <input class="mdl-textfield__input" type="text" id="name" name="name"
                               value="<%=categoryBean.getName()%>" required>
                    </div>
                    <br>
                    <button type="submit" name="submit"
                            class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
                        Edit
                    </button>

                </form>
        </div>
    </main>
</div>
</body>
</html>
