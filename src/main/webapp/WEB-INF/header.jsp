<header class="mdl-layout__header">
    <div class="mdl-layout__header-row">
        <span class="mdl-layout-title">Admin Panel</span>
        <% if (userAdminBean.isLoggedIn()) { %>
        <div class="header-button-container">
            <button onclick="location.href='?action=categories'"
                    style="margin-left: 10px"
                    class="mdl-button mdl-js-button mdl-button--raised
                    <%="categories".equals(request.getParameter("action"))
                    || "login".equals(request.getParameter("action")) || "createCategory".equals(request.getParameter("action")) || "deleteCategory".equals(request.getParameter("action"))?"mdl-button--accent":"mdl-button--colored"%>">
                Categories
            </button>
            <button onclick="location.href='?action=users'"
                    style="margin-left: 10px"
                    class="mdl-button mdl-js-button mdl-button--raised
                    <%="users".equals(request.getParameter("action")) || "deleteUser".equals(request.getParameter("action"))?"mdl-button--accent":"mdl-button--colored"%>">
                Users
            </button>
            <button onclick="location.href='?action=logs'"
                    style="margin-left: 10px"
                    class="mdl-button mdl-js-button mdl-button--raised
                    <%="logs".equals(request.getParameter("action"))?"mdl-button--accent":"mdl-button--colored"%>">
                Logs
            </button>
        </div>
        <%}%>
        <div class="mdl-layout-spacer"></div>
        <nav class="mdl-navigation">
            <% if (userAdminBean.isLoggedIn()) { %>
            <span class="mdl-layout-title"
                  style="padding-right: 10px"><%=userAdminBean.getFullName()%></span>
            <button onclick="location.href='?action=logout'"
                    class="mdl-button mdl-js-button mdl-button--raised mdl-button--accent">
                Logout
            </button>
            <%}%>
        </nav>
    </div>
</header>
