<%@ page import="org.etfbl.webshopadmin.util.Utils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="user" type="org.etfbl.webshopadmin.dto.User" scope="session"/>
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
            <h4>Edit User</h4>
            <p style="color:<%=Utils.isError(session)? "red" : "green"%>"><%=session.getAttribute("notification")%>
            </p>
            <form action="?action=saveEditUser&id=<%=user.getId()%>" method="post" class="form-container">
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label" for="username">Username</label>
                    <input class="mdl-textfield__input" type="text" id="username" name="username" value="<%=user.getUsername()%>" required>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label" for="firstname">First name</label>
                    <input class="mdl-textfield__input" type="text" id="firstname" name="firstname" value="<%=user.getFirstName()%>" required>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label" for="lastname">Last name</label>
                    <input class="mdl-textfield__input" type="text" id="lastname" name="lastname" value="<%=user.getLastName()%>" required>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label" for="email">Email</label>
                    <input class="mdl-textfield__input" type="text" id="email" name="email" value="<%=user.getEmail()%>" required>
                </div>
                <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
                    <label class="mdl-textfield__label" for="city">City</label>
                    <input class="mdl-textfield__input" type="text" id="city" name="city" value="<%=user.getCity()%>" required>
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
