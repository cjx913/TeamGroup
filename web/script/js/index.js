$address = sessionStorage.getItem("src");

$(function () {
    $("#mainbody iframe").attr("src", $address);
    $("#action ul li").each(function () {
        $(this).hover(function () {
            $(this).css("background", "indianred");
        }, function () {
            $(this).css("background", "");
        });
    });
    $("#groupConference").click(function () {
        $("#groups").toggle();
    });
})