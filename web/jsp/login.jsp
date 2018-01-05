<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Login</title>
    <style type="text/css">
        * {
            margin: 0px;
            padding: 0px;
        }

        html, body {
            width: 100%;
            height: 100%;
            overflow: hidden;
        }

        .left {
            margin-right: 40%;
            width: 40%;
            height: 90%;
        }

        .right {
            float: right;
            width: 60%;
            height: 90%;
        }

        #login {
            margin-top: 10%;
            margin-bottom: 10%;
            width: 100%;
            height: 80%;
        }

        #login table {
            margin-right: 5%;
            width: 95%;
        }

        #login input {
            margin-top: 10px;
            padding-left: 15px;
            height: 40px;
            width: 200px;
            line-height: 40px;
            border: 0px;
            border-radius: 15px;
            outline: none;
            background-color: beige;
        }

        footer {
            width: 100%;
            height: 10%;
        }
    </style>
    <script type="application/javascript" src="/script/jquery-3.2.1.js"></script>
    <script type="application/javascript" src="/script/js/public.js"></script>
    <script type="application/javascript">
        $(function () {
            //#register input
            $("#login input").each(function () {
                $(this).focus(function () {
                    $(this).css("box-shadow", "0px 0px 4px #000");
                });
                $(this).blur(function () {
                    $(this).css("box-shadow", "");
                });
            });

            //verification_code
            var canvas = document.getElementById("verification_code");
            var verification = verification_code(canvas);
            $("#verification_code").click(function () {
                verification = verification_code(canvas);
            });

            $("#login input:submit").click(function () {
                var $verVal = $("#register input[name='verification_code']").val();
                if ($verVal != "") {
                    if (verification.toLowerCase() != $verVal.toLowerCase()) {
                        alert("verification code is false!");
                        return false;
                    }
                }else {
                    alert("please enter verification code.");
                }
            });
        });
    </script>
</head>
<body>

<div class="right">
    <form id="login" action="/do/login" method="post">
        <table align="center" cellpadding="0" cellspacing="0">
            <thead><h1 style="text-align: center">Login</h1></thead>
            <tbody>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" required placeholder="Please enter your name."
                           pattern="[A-z&0-9&_&]+" title="包含大小写英文字母、数字和下划线"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password" required placeholder="Please enter your password."
                           pattern="[A-z&0-9&_&]{8,}" title="包含至少8位的大小写英文字母、数字和下划线"/></td>
            </tr>
            <tr>
                <td> Verification Code:</td>
                <td><input type="text" name="verification_code" required placeholder="Verification code"/></td>
                <td>
                    <canvas id="verification_code" height="50px" width="100px"
                    <%--onclick="verification_code(this)--%>"></canvas>
                </td>
            </tr>
            <tr>
                <td><a href="/jsp/register.jsp" style="text-decoration: none"><input type="button" value="Register"/></a></td>
                <td><input type="reset" value="Reset"/></td>
                <td><input type="submit" value="Submit"></td>
            </tr>
            <tr>
                <td>${msg}</td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div class="left">
    left
</div>

<footer>

</footer>
</body>
</html>
