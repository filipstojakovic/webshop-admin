<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="logService" type="org.etfbl.webshopadmin.service.LogService" scope="session"/>
<jsp:useBean id="userAdminBean" type="org.etfbl.webshopadmin.bean.UserAdminBean" scope="session"/>
<html>
<head>
    <title>Logs</title>
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
            <ul class="demo-list-item mdl-list">
                <% for (String line : logService.readLogs()) {%>
                <li class="mdl-list__item">
                <span class="mdl-list__item-primary-content">
                  <%=line%>>
                </span>
                </li>
                <% } %>
            </ul>
        </div>
    </main>
</div>
</body>
</html>
