function verification_code(canvas) {
    var context = canvas.getContext("2d");
    var canvas_x = canvas.width;
    var canvas_y = canvas.height;
    context.fillStyle = "gray";
    context.fillRect(0, 0, canvas_x, canvas_y);
    context.fill();

    context.fillStyle = "#00f";
    for (var i = 0; i < 100; i++) {
        context.beginPath();
        var x = Math.ceil(Math.random() * 100) - 2;
        var y = Math.ceil(Math.random() * 50) - 2;
        context.arc(x, y, 1, 0, 2 * Math.PI);
        //context.fillRect(x, y, 2, 2);
        context.fill();
    }

    var chars = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'];
    var verification_code = "";
    for (var i = 0; i < 4; i++) {
        var num = Math.ceil(Math.random() * 51);
        verification_code += chars[num];
    }

    context.font = "italic 30px arial";
    context.strokeStyle = "#f00";
    context.lineWidth = 2;
    context.strokeText(verification_code.toString(), 10, canvas_y * 4 / 5);

    return verification_code.toString();
}

//click to change content
function changeContent(str, fun) {
    $(str).click(function () {
        var text;
        var retext;
        var p = $(this); //为后面文本框变成文本铺垫
        text = $(this).text().trim();
        var input = $('<input style="width: 300px" type="text" class="edit" value="' + text + '">');
        $(this).html(input);

        //阻止表单默认点击行为
        $('input').click(function () {
            return false;
        });


        $('input').select();

        //表单失去焦点文本框变成文本
        $('input').blur(function () {
            retext = $(this).val();
            p.html(retext);
            //if content change,return content as function parameter to do otherthing.
            if (retext != text) {
                fun(retext);
            }
        });

    });
}

//mytoggle(fun1,fun2);
var flag = true;

function mytoggle(fun1, fun2) {
    if (flag) {
        fun1();
        flag = false;
    } else {
        fun2();
        flag = true;
    }
}



