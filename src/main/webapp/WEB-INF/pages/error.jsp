<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userAdminBean" type="org.etfbl.webshopadmin.bean.UserAdminBean" scope="session"/>
<html>
<head>
    <title>Error Page</title>
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
            <h2>This is an error page!</h2>
            <a href="?action=categories">
                <button type="submit" name="submit"
                        class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent">
                    Go back Home
                </button>
            </a>
        </div>
    </main>
</div>
</body>
</html>
