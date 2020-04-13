
jQuery(function($){
    /* *********************** PC NAV ************************ */
    var $openMenu = $(".cm-top-menu");

    // 모바일
    var $menuBtn = $("#header .nav-open-btn");
    var $gnbM = $("#gnbM");
    var $gnbMList = $gnbM.children("#navigation").children("li");
    var $gnbMBg = $('.gnb-overlay-bg-m');
    var menuState = false;

    // 모바일 gnb열린 후 창 크게했을때 스크롤바 생성
    $(window).resize(function  () {
        var win_width = $(window).outerWidth();
        if ( menuState  ) {
            if ( win_width > 1200 ) {
                $("body").css({'height':'auto', 'overflow':'auto'});
            }
        }
    });


    /* *********************** MOBILE NAV ************************ */
    $menuBtn.click(function  () {
        if ( menuState ) {
            menuClose();
            menuState = false;
            $(this).removeClass("active");
        }else {
            menuOpen();
            menuState = true;
            $(this).addClass("active");
        }
        return false;
    });

    $gnbMBg.click(function  () {
        menuClose();
        menuState = false;
        $menuBtn.removeClass("active");
    });

    /* 메뉴열기 */
    function menuOpen () {
        $gnbM.addClass("open");
        $gnbMBg.fadeIn();
        $("body").css({'height':$(window).height(), 'overflow':'hidden'});
    }
    /* 메뉴닫기 */
    function menuClose () {
        $gnbM.removeClass("open");
        $gnbMBg.hide();
        $("body").css({'height':'auto', 'overflow':'auto'});
    }

    /*/!* GNB MOBILE 2DEPTH 클래스 붙이기  *!/
    $("#gnbM > ul > li:has('.gnb-2dep')").addClass("has-2dep");
    $("#gnbM > ul > li:has('.gnb-2dep')").each(function  () {
        $(this).children("a").append("<span class='gnb-icon close-icon'><i class='material-icons'>&#xE145;</i></span><span class='gnb-icon open-icon' style='display:none;'><i class='material-icons'>&#xE15B;</i></span>");
    });

    /!* GNB MOBILE 2DEPTH 오픈 *!/
    $("#gnbM > ul > li:has('.gnb-2dep')").children("a").click(function(event){
        /!* 2dep가 열려있을때 *!/
        if ( $(this).parent("li").hasClass("active") ){
            $(this).parent("li").removeClass("active");
            $(this).children(".open-icon").hide();
            $(this).children(".close-icon").show();
            $(this).siblings(".gnb-2dep").slideUp(400);
        }
        /!* 2dep가 닫혀있을때 *!/
        else{
            $("#gnbM > ul > li").has(".gnb-2dep").each(function() {
                if ( $(this).hasClass("active") ){
                    $(this).removeClass("active");
                    $(this).find(".open-icon").hide();
                    $(this).find(".close-icon").show();
                    $(this).children(".gnb-2dep").slideUp(400);
                }
            });
            $(this).parent("li").addClass("active");
            $(this).children(".close-icon").hide();
            $(this).children(".open-icon").show();
            $(this).siblings(".gnb-2dep").slideDown(400);
        }
        return false;
    });

    /!* 해당페이지의 GNB 모바일 2depth 열기 & ON  *!/
    if ( dep1> 0 && dep2> 0 ) {
        $gnbM.children("ul").children("li").eq(dep1-1).addClass("active").children(".gnb-2dep").show().children("li").eq(dep2-1).addClass("on");
        $gnbM.children("ul").children("li").eq(dep1-1).find(".close-icon").hide();
        $gnbM.children("ul").children("li").eq(dep1-1).find(".open-icon").show(); // 모바일 네비 on
    }*/

    /* *********************** PC, 모바일 공통 ************************ */
    /* ------------------------
    *** 서브 상단 location (1차, 2차) 하위메뉴 ON & 열기 ***
    ------------------------ */
    $openMenu.find(".menu-location").each(function  () {
        // 클릭할때 펼치기
        $(this).find(".cur-location").click(function  () {
            $(this).toggleClass("open");
            $(this).siblings(".location-menu-con").toggle();

            return false;
        });
        // 2depth ON
        if ( $(this).is(".location1") ) {
            $(this).find(".location-menu-con").find("li").eq(dep1-1).addClass("on");
        }else {
            $(this).find(".location-menu-con").find("li").eq(dep2-1).addClass("on");
        }
    });

    $(".menu-location").mouseleave(function  () {
        if ( $(this).find(".location-menu-con").css("display") == "block" ) {
            $(this).find(".cur-location").removeClass("open");
            $(this).find(".location-menu-con").hide();
        }
    });

    /* ------------------------
    *** 이전페이지,다음페이지 링크걸기 ***
    ------------------------ */
    /*  (무조건 페이지의 dep1, dep2의 값을 가져와야함) */
    // 2depth 이동
    var $sub_prev_page_btn = $(".sub-prev-page-btn");
    var $sub_next_page_btn = $(".sub-next-page-btn");
    var $dep1_menu = $("#gnb > ul > li");
    var dep1_menu_lang = $dep1_menu.length;

    $sub_prev_page_btn.attr("href",$dep1_menu.eq(dep1-2).children("a").attr("href"));
    $sub_next_page_btn.attr("href",$dep1_menu.eq(dep1).children("a").attr("href"));

    $sub_prev_page_btn.find(".sub-page-name").text($dep1_menu.eq(dep1-2).children("a").text());
    $sub_next_page_btn.find(".sub-page-name").text($dep1_menu.eq(dep1).children("a").text());



    if ( dep1 == dep1_menu_lang ) {
        $sub_next_page_btn.attr("href",$dep1_menu.eq(0).children("a").attr("href"));
        $sub_next_page_btn.find(".sub-page-name").text($dep1_menu.eq(0).children("a").text());
    }else if ( dep1 == 1 ) {
        $sub_prev_page_btn.attr("href",$dep1_menu.eq(dep1_menu_lang-1).children("a").attr("href"));
        $sub_prev_page_btn.find(".sub-page-name").text($dep1_menu.eq(dep1_menu_lang-1).children("a").text());
    }

});





$(function(){

    $('.dropbtn').on('click', function(e){
        if(Modernizr.mq('screen and (max-width:1220px)')) {
            e.preventDefault();
            $(this).next($('.dropdown-content')).slideToggle();
        }
    });

    $(window).resize(function() {
        $('.dropdown-content').attr("style", "");
    });

});