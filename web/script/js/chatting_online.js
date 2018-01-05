$(function () {
    sessionStorage.setItem("src", "/jsp/chatting_online.jsp");

    var userId = $("#contacts").find("input[name=userid]").val();
    alert(userId);
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
        alert(msg.data);
        var allmessage = msg.data.toString().split("@");
        var message = allmessage[0].toString();
        var fr = allmessage[1].toString();
        var to = allmessage[2].toString();
        var sendtime = allmessage[3];
        alert(message + "\n" + fr+"\n"+to + "\n" + sendtime);
        if (fr==userId.toString()) {
            var $node = $("<div class='out_content'>" + "<div class='self_content'>" + message + "</div>" + "</div>");
            $node.appendTo($("#content_field"));
        } else {
            var $node = $("<div class='out_content'>" + "<div class='opposite_content'>" + message + "</div>" + "</div>");
            $node.appendTo($("#content_field"));
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

            $.get("", {"userid": userId,"contactid":toId}, function (data, textStatus) {
                alert(textStatus);
            });
        });
    });

    $("#send").click(function () {
        msg = $("#input_text textarea").val();
        alert(toId + "-" + msg);
        if(msg!=""&&typeof(toId)!="undefined" ){
            websocket.send(toId + "-" + msg);
        }
    });

});