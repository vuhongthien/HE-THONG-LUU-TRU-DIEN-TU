$(document).ready(function () {

    var cateid = $('#hi').val();
    if (!isNaN(cateid) && cateid > 0) {
        $("ul#ul-list-course-home li").removeClass("active");
        $("ul#ul-list-course-home li.click-" + cateid).addClass("active");
    } else {
        $("li.click-home").addClass("active");
    }
    // change button plus to advise at left menu
    $("#nav-ct .nav li.active").parent().css("display", "block");
    $("#nav-ct .nav li.active").parent("ul").parent("li").parent("ul").css("display", "block");
    $("#nav-ct .nav li.active").parent().parent().parent().css("display", "block");
    $("#nav-ct .nav li.active").parent().prev().find("i").removeClass("fa-plus-square-o").addClass("fa-minus-square-o");
    $("#nav-ct .nav li.active").parent().parent().parent().prev().find("i").removeClass("fa-plus-square-o").addClass("fa-minus-square-o");
    $("#nav-ct .nav li.active").children().addClass("active");
});
}