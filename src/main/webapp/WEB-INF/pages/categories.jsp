<%@ page import="org.etfbl.webshopadmin.dto.Category" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userAdminBean" type="org.etfbl.webshopadmin.bean.UserAdminBean" scope="session"/>
<jsp:useBean id="categoryService" type="org.etfbl.webshopadmin.service.CategoryService" scope="session"/>

<html>
<head>
    <title>Categories</title>
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

            <a href="?action=createCategory" style="margin: 10px">
                <button type="submit" name="submit"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
                    Create Category
                </button>
            </a>
            <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">Category name</th>
                    <th class="mdl-data-table__cell--non-numeric"></th>
                    <th class="mdl-data-table__cell--non-numeric"></th>
                </tr>
                </thead>
                <tbody>
                <% for (Category category : categoryService.findAll()) {%>
                <tr>
                    <td class="mdl-data-table__cell--non-numeric" style="margin: 0 10px 0 10px">
                        <%=category.getName()%>
                    </td>
                    <td class=" mdl-data-table__cell--non-numeric">
                        <a href="?action=editCategory&id=<%=category.getId()%>">
                            <button type="submit" name="submit"
                                    class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
                                Edit
                            </button>
                        </a>
                    </td>
                    <td class=" mdl-data-table__cell--non-numeric">
                        <a href="?action=deleteCategory&id=<%=category.getId()%>">
                            <button type="submit" name="submit"
                                    class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent">
                                Delete
                            </button>
                        </a>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
</html>
