<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>--%>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="/css/personal_center.css">
    <script type="application/javascript" src="/script/jquery-3.2.1.js"></script>
    <script type="application/javascript" src="/script/js/public.js"></script>
    <script type="text/javascript">
        sessionStorage.setItem("src", "/jsp/personal_center.jsp");
        $(function () {
            changeContent("#corporation", function (rc) {
                $.get("/do/corporation", {"corporation": rc}, function (data, textStatus) {
                    alert(textStatus);
                });
            });
            changeContent("#duty", function (rc) {
                $.get("/do/duty", {"duty": rc}, function (data, textStatus) {
                    alert(textStatus);
                });
            });

        })
    </script>
</head>
<body>
<div id="basic_information">
    <table cellspacing="0" cellpadding="0" border="0">
        <tr>
            <td>
                <img src="/res/images/232.jpg">
            </td>
            <td>
                <table cellspacing="0" cellpadding="0" border="0" style="margin:10px;margin-right: 20px">
                    <tr>
                        <td><strong>name:</strong></td>
                        <td><c:out value="${user.getUserName()}"></c:out></td>
                    </tr>
                    <tr>
                        <td><strong>corporation:</strong></td>
                        <td><p id="corporation"><c:out value="${user.getCorporation().trim()}"></c:out></p></td></td>
                    </tr>
                    <tr>
                        <td><strong>duty:</strong></td>
                        <td><p id="duty"><c:out value="${user.getDuty().trim()}"></c:out></p></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<div id="schedule_information">
    <table cellspacing="0" cellpadding="0">
        <c:forEach var="schedule" items="${user.getSchedulesList()}">
            <tr>
                <td class="time">${schedule.getDate()}</td>
                <td class="event">${schedule.getEvent()}</td>
            </tr>
        </c:forEach>
    </table>
</div>
<div id="contacts_manage">
    <table cellspacing="0px" cellpadding="0px" border="0">
        <c:forEach var="contact" items="${user.getContactsList()}" varStatus="statu">
            <c:if test="${statu.first}">
                <tr>
            </c:if>
            <c:if test="${statu.index%3==0}">
                </tr>
                <tr>
            </c:if>
            <td>
                <img src="/res/images/232.jpg"/>
                <label><c:out value="${contact.getContactName()}"></c:out></label>
            </td>
            <c:if test="${statu.last}">
                </tr>
            </c:if>
        </c:forEach>
    </table>
</div>
<div id="teamgroup_manage">
    <table>
        <c:forEach var="group" items="${user.getGroupsList()}" varStatus="statu">
        <c:if test="${statu.first}">
        <tr>
            </c:if>
            <c:if test="${statu.index%3==0}">
        </tr>
        <tr>
            </c:if>
            <td><label><c:out value="${group.getGroupName()}"></c:out></label></td>
            <c:if test="${statu.last}">
        </tr>
            </c:if>
            </c:forEach>
    </table>
</div>
<div id="modify_information">

</div>
</body>
</html>
