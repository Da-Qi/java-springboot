$(function () {
    $.get("http://localhost:8081/header.html",function (data) {
        $("#header").html(data);
    });
    $.get("http://localhost:8081/footer.html",function (data) {
        $("#footer").html(data);
    });
});