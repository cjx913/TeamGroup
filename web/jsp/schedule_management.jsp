<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }

        html, body {
            width: 100%;
            height: 100%;
            overflow: auto;
        }

        #control {
            overflow: hidden;
            margin: 30px 5%;
            height: auto;
            padding: 5px 15px;
            background-color: beige;
            border-radius: 10px;
        }

        #search {
            float: left;
            margin-top: 5px;
            width: 100%;
        }

        #append {
            float: left;
            margin-top: 5px;
            width: 100%;
        }

        #schedule {
            margin: 30px 5%;
            height: auto;
            background-color: beige;
            border-radius: 10px;
        }

        .time {
            margin-right: 50px;
            text-align: right;
            width: 120px;
            height: 40px;
            line-height: 40px;
        }

        .event {
            padding-left: 15px;
            text-align: left;
            width: auto;
            height: auto;
            line-height: 40px;
        }

        .btnstyle {
            float: left;
            margin: 10px 5%;
            overflow: hidden;
            width: 20%;
            height: 30px;
            border-radius: 10px;
            border: 2px solid bisque;
            background-color: beige;
            box-shadow: 1px 1px 2px #000;
            text-align: center;
            font-size: 20px;
            outline: none;
        }

        .btnstyle:hover {
            border: #fff;
            box-shadow: 0px 0px 5px #fff;
        }

        hr {
            margin: 3px;
            border: 1px dotted;
            width: 100%;
        }
    </style>

    <script type="text/javascript" src="/script/jquery-3.2.1.js"></script>
    <script type="text/javascript" src="/script/js/public.js"></script>
    <script type="text/javascript">
        $(function () {
            sessionStorage.setItem("src", "/jsp/schedule_management.jsp");
            $("#control button:eq(0)").click(function () {
                $("#append").css("display", "none");
                $("#schedule :checkbox").css("display", "none");
                $("#deleteOparte").css("display", "none");
                $("#search").toggle();
            });
            $("#control button:eq(1)").click(function () {
                $("#search").css("display", "none");
                $("#result").html("");
                $("#schedule :checkbox").css("display", "none");
                $("#deleteOparte").css("display", "none");
                $("#append").toggle();
            });
            $("#control button:eq(2)").click(function () {
                $("#search").css("display", "none");
                $("#result").html("");
                $("#append").css("display", "none");
                $("#schedule :checkbox").toggle();
                $("#deleteOparte").toggle();
            });

            /*var flag = true;
            $("#allOrNone").click(function () {
                if (flag) {
                    $("#schedule input:checkbox[name='event_checkbox']").prop("checked", true);
                    flag = false;
                } else {
                    $("#schedule input:checkbox[name='event_checkbox']").prop("checked", false);
                    flag = true;
                }
            });*/
            $("#allOrNone").click(function () {
                mytoggle(function () {
                    $("#schedule input:checkbox[name='event_checkbox']").prop("checked", true);
                }, function () {
                    $("#schedule input:checkbox[name='event_checkbox']").prop("checked", false);
                });
            });

            $("#invertSelect").click(function () {
                $("#schedule input:checkbox[name='event_checkbox']").each(function () {
                    $(this).prop("checked", !$(this).prop("checked"));
                });
            });

        });

    </script>
</head>
<body>
<div id="control">
    <div style="width: 100%;float: left">
        <button class="btnstyle">Search</button>
        <button class="btnstyle">Add</button>
        <button class="btnstyle">Delete</button>
    </div>
    <div id="search" style="display: none">
        <hr/>
        <form action="/do/searchSchedules" method="get">
            Date:<input type="date" name="search_date"/>
            Time:<input type="time" name="search_time"/>
            keyword:<input type="text" name="search_keyword"/>
            <input type="submit" value="Search"/>
        </form>
    </div>
    <div id="append" style="display: none">
        <hr/>
        <form action="/do/addSchedules" method="post">
            Date:<input type="date" name="add_date">
            Time:<input type="time" name="add_time">
            <input type="submit" value="Add"/>
            ${msg}
            <br/>
            event:<textarea name="add_event" style="vertical-align: top;min-width: 90%;max-width: 90%;"></textarea>
        </form>
    </div>
    <div id="result">
        <table cellspacing="0" cellpadding="0">
            <c:forEach var="schedule" items="${schedulesSearchList}">
                <tr>
                    <td class="time">${schedule.getDate()}</td>
                    <td class="event">${schedule.getEvent()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</div>
<div id="schedule">
    <form method="get" action="/do/deleteSchedules">
    <table cellspacing="0" cellpadding="0">
        <tr id="deleteOparte" style="display: none">
            <td>
                <button type="button" id="allOrNone">Select All/Select None</button>
            </td>
            <td>
                <button type="button" id="invertSelect">Invert Select</button>
            </td>
            <td style="text-align: right"><input name="sureDelete" type="submit" value="SureDelete"/></td>
        </tr>
        <c:forEach var="schedule" items="${user.getSchedulesList()}">
            <tr>
                <td style="padding-left: 20px"><input type="checkbox" name="event_checkbox"
                                                      value="${schedule.getSchedulesID()}" style="display: none"></td>
                <td class="time">${schedule.getDate()}</td>
                <td class="event">${schedule.getEvent()}</td>
            </tr>
        </c:forEach>
    </table>
    </form>
</div>
</body>
</html>
