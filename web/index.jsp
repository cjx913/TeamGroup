<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <%--<style type="text/css">
        @import url("/css/index.css");
    </style>--%>
    <link rel="stylesheet" type="text/css" href="/css/index.css"/>
    <script type="text/javascript" src="/script/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/script/js/index.js"></script>
    <title>TeamGroup</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>

<div id="navbar">
    <div id="personal">
        <img id="avatar" src="res/images/232.jpg">
        <div id="name">
            <strong><c:out value="${user.getUserName()}"></c:out></strong>
            <p style="word-break: break-all">#################</p>
        </div>
    </div>

    <nav id="action">
        <ul>
            <a href="/jsp/personal_center.jsp" target="mainbody">
                <li>Personal Center</li>
            </a>
            <a href="/jsp/schedule_management.jsp" target="mainbody">
                <li>Schedule Management</li>
            </a>
            <a href="/jsp/chatting_online.jsp" target="mainbody">
                <li>Chatting Online</li>
            </a>
            <a>
                <li id="groupConference">Group Conference</li>
            </a>
        </ul>
        <div id="groups" style="display: none;">
            <ul>
                <c:forEach var="group" items="${user.getGroupsList()}">
                    <li><c:out value="${group.getGroupName()}"></c:out></li>
                </c:forEach>
            </ul>
        </div>
    </nav>
</div>

<div id="mainbody">
    <iframe name="mainbody" src="/jsp/personal_center.jsp" frameborder="0" scrolling="no">
    </iframe>
</div>

</body>
</html>
