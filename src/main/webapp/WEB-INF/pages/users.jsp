<%@ page import="org.etfbl.webshopadmin.dto.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userAdminBean" type="org.etfbl.webshopadmin.bean.UserAdminBean" scope="session"/>
<jsp:useBean id="userService" type="org.etfbl.webshopadmin.service.UserService" scope="session"/>

<html>
<head>
    <title>Users</title>
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

            <a href="?action=createUser" style="margin: 10px">
                <button type="submit" name="submit"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
                    Create User
                </button>
            </a>
            <table class="mdl-data-table mdl-js-data-table mdl-shadow--2dp" style="width: 100%">
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">Username</th>
                    <th class="mdl-data-table__cell--non-numeric">Full Name</th>
                    <th class="mdl-data-table__cell--non-numeric">Email</th>
                    <th class="mdl-data-table__cell--non-numeric">City</th>
                    <th class="td-cell-fit"></th>
                    <th class="td-cell-fit"></th>
                </tr>
                </thead>
                <tbody>
                <% for (User user : userService.findAll()) {%>
                <%--    TODO: on click--%>
                <tr onclick="location.href='send_message.jsp?id=<%=user.getId()%>'">
                    <td class="mdl-data-table__cell--non-numeric"><%=user.getUsername()%>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric"><%=user.getFullName()%>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric"><%=user.getEmail()%>
                    </td>
                    <td class="mdl-data-table__cell--non-numeric"><%=user.getCity()%>
                    </td>
                    <td class=" mdl-data-table__cell--non-numeric td-cell-fit">
                        <a href="?action=editUser&id=<%=user.getId()%>">
                            <button type="submit" name="submit"
                                    class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">
                                Edit
                            </button>
                        </a>
                    </td>
                    <td class=" mdl-data-table__cell--non-numeric td-cell-fit">
                        <a href="?action=deleteUser&id=<%=user.getId()%>">
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
