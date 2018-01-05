<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <%--<style type="text/css">
         /*@import url("/css/chatting_online.css");*/

     </style>--%>
    <link rel="stylesheet" type="text/css" href="/css/chatting_online.css">
    <script type="text/javascript" src="/script/jquery-3.2.1.js"></script>
    <script>
        $(function () {
            sessionStorage.setItem("src", "/jsp/chatting_online.jsp");

            var userId = $("#contacts").find("input[name=userid]").val();
            var toId;
            var msg;
            var websocket;
            var url = "ws://localhost:8088/websockettest"
            if (websocket == null) {
                if ('WebSocket' in window) {
                    websocket = new WebSocket(url);
                } else {
                    alert("Don't support WebSocket");
                    return;
                }
            }
            websocket.onopen = function () {
                alert("opened");
            }

            websocket.onmessage = function (msg) {
                var allmessage = msg.data.toString().split("@");
                var message = allmessage[0].toString();
                var fr = allmessage[1].toString();
                var to = allmessage[2].toString();
                var sendtime = allmessage[3];
                if (to == toId) {
                    if (fr == userId.toString()) {
                        var $node = $("<div class='out_content'>" + "<div class='self_content'>" + message + ":" + sendtime + "</div>" + "</div>");
                        $node.appendTo($("#content_field"));
                        $("#content_field").scrollTop( $("#content_field")[0].scrollHeight);
                    } else {
                        var $node = $("<div class='out_content'>" + "<div class='opposite_content'>" + message + ":" + sendtime + "</div>" + "</div>");
                        $node.appendTo($("#content_field"));
                        $("#content_field").scrollTop( $("#content_field")[0].scrollHeight);
                    }
                }else{
                    alert("you have a message from "+fr);
                }
            }

            websocket.onerror = function () {
                alert("error");
            }

            websocket.onclose = function () {
                alert("Close");
            }

            /* $("#websocketConnection").click(function () {
                 if (websocket == null) {
                     if ('WebSocket' in window) {
                         websocket = new WebSocket(url);
                     } else {
                         alert("Don't support WebSocket");
                         return;
                     }
                 }
                 websocket.onopen = function () {
                     alert("opened");
                 }

                 websocket.onmessage = function (msg) {
                     alert(msg.data);
                var $node = $("<div class='out_content'>"+"<div class='self_content'>"+msg.data+"</div>"+ "</div>");
                     $node.appendTo($("#content_field"));
                 }

                 websocket.onerror = function () {
                     alert("error")
                 }

                 websocket.onclose = function () {
                     alert("Close");
                     websocket = null;
                 }

             });*/

            $("#websocketClose").click(function () {
                websocket.close();
            });

            $(".friend").each(function () {
                $(this).hover(function () {
                    $(this).css("background", "lightblue");
                }, function () {
                    $(this).css("background", "");
                });
                $(this).click(function () {
                    toId = $(this).find("input[name=contactid]").val();
                    $("#status").find("strong").text($(this).find("strong").text());
                    $("#content_field").empty();
                    $.get("/do/getRecord", {"userid": userId, "contactid": toId}, function (data, textStatus) {
                        $(data).find("message").each(function () {
                            var f = $(this).find("fromid").text();
                            var t = $(this).find("toid").text();
                            var msg = $(this).find("msg").text();
                            var st = $(this).find("sendtime").text();
                            if (f == userId.toString()) {
                                var $node = $("<div class='out_content'>" + "<div class='self_content'>" + msg + ":" + st + "</div>" + "</div>");
                                $node.appendTo($("#content_field"));
                                $("#content_field").scrollTop( $("#content_field")[0].scrollHeight);
                            } else {
                                var $node = $("<div class='out_content'>" + "<div class='opposite_content'>" + msg + ":" + st + "</div>" + "</div>");
                                $node.appendTo($("#content_field"));
                                $("#content_field").scrollTop( $("#content_field")[0].scrollHeight);
                            }
                        });
                        alert(textStatus);
                    }, "xml");
                });
            });

            $("#send").click(function () {
                msg = $("#input_text textarea").val();
                if (msg != "" && typeof(toId) != "undefined") {
                    websocket.send(toId + "@" + msg);
                }
            });

        });
    </script>
</head>
<body>
<div id="contacts">
    <input type="hidden" name="userid" value="${user.getId()}"/>
    <div class="out_content">
        <c:forEach var="contact" items="${user.getContactsList()}">
            <div class="friend">
                <input type="hidden" name="contactid" value="${contact.getId()}"/>
                <img src="/res/images/232.jpg">
                <strong><c:out value="${contact.getContactName()}"></c:out></strong>
            </div>
        </c:forEach>
    </div>
</div>

<div id="chatting_interface">
    <div id="status">
        <strong></strong>
        <input id="websocketConnection" type="button" value="Connecton"/>
        <input id="websocketClose" type="button" value="Close"/>
    </div>
    <hr/>

    <div id="content_field">
        <%-- <div class="out_content">
             <div class="opposite_content">asdsdasdasasfsadasdasdasdasdasdasdasdasdasddasasfsadasdasdasdasdasdasdasdasd
             </div>
         </div>
         <div class="out_content">
             <div class="self_content">dsdasdwwqeqweqwewqeqwrgbvbcxcxzczcsafsf</div>
         </div>
         <div class="out_content">
             <div class="opposite_content">asdsdasdasasfssdasdasdasdasd</div>
         </div>
         <div class="out_content">
             <div class="self_content">dsdasdwwqeqweqwewqesf</div>
         </div>
         <div class="out_content">
             <div class="opposite_content">asdsdasdasasfsaddasdasdasd</div>
         </div>--%>
    </div>
    <hr/>

    <div id="input_field">
        <div id="input_btn">
            <input id="reset" type="button" value="reset"/>
            <input id="send" type="button" value="send"/>
        </div>
        <div id="input_text">
            <textarea name="input_field" autofocus="autofocus"></textarea>
        </div>
    </div>
</div>
</body>
</html>

