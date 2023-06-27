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

            <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">id</th>
                    <th class="mdl-data-table__cell--non-numeric">name</th>
                    <th class="mdl-data-table__cell--non-numeric">parent id</th>
                    <th class="mdl-data-table__cell--non-numeric">Action</th>
                </tr>
                </thead>
                <tbody>
                <% for (Category category : categoryService.findAll()) {%>
                <%--    TODO: on click--%>
                <tr onclick="location.href='send_message.jsp?id=<%=category.getId()%>'">
                    <td class="mdl-data-table__cell--non-numeric"><%=category.getId()%>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric"><%=category.getName()%>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric"><%=category.getParentCategoryId()%>
                    </td>
                    <%--    TODO: href--%>
                    <td class="mdl-data-table__cell--non-numeric"><a href="send_message.jsp?id=<%=category.getId()%>">Send
                        message
                        &gt&gt</a></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </main>
</div>
</body>
</html>
