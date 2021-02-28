function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i].trim();
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

$(function () {
    var nickname = getCookie('nickname');
    if (nickname !== '') {
        //查询用户信息
        $(".span_username").html(nickname);
        $(".user_offline").hide();
        $(".user_online").show();
        $.get("user/loginByCookie", {'nickname': nickname}, function (data) {
            $(".user_img_url").attr("src", "../static" + data.img_url);
        });
    } else {
        $(".user_offline").show();
        $(".user_online").hide();
        $(".tipmessage-box").hide();
        $(".ksd-logindrop").hide();
    }
});

$("#exit").click(function () {
    $.get("exit");
    document.cookie = 'nickname=';
    location.href = "http://localhost:8081/login.html";
})


$(function () {
    var pathname = window.location.pathname;
    if (pathname.indexOf('forum') !== -1) {
        $(".forum").addClass("active");
        $(".index").removeClass("active");
        $(".navitem").fadeOut();
    } else {
        $(".index").addClass("active");
        $(".forum").removeClass("active");
        $(".navitem").fadeIn();
    }
})
