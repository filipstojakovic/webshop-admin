<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Category</title>
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.indigo-pink.min.css">
    <link href="styles/style.css" type="text/css" rel="stylesheet">
    <script defer src="https://code.getmdl.io/1.3.0/material.min.js"></script>
</head>
<body>
<div class="mdl-textfield mdl-js-textfield getmdl-select">
    <input type="text" value="" class="mdl-textfield__input" id="sample2" readonly>
    <input type="hidden" value="" name="sample2">
    <i class="mdl-icon-toggle__label material-icons">keyboard_arrow_down</i>
    <label for="sample2" class="mdl-textfield__label">Country</label>
    <ul for="sample2" class="mdl-menu mdl-menu--bottom-left mdl-js-menu">
        <li class="mdl-menu__item" data-val="DEU">Germany</li>
        <li class="mdl-menu__item" data-val="BLR">Belarus</li>
        <li class="mdl-menu__item" data-val="RUS">Russia</li>
    </ul>
</div>
</body>
</html>
