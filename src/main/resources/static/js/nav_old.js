var dep1;
var dep2;

jQuery(function($){
    /* *********************** PC NAV ************************ */
    var $openMenu = $(".cm-top-menu");
    // PC
    var $gnb = $("#gnb");
    var $gnbList = $("#gnb > ul");
    var $gnb_dep1 = $("#gnb > ul > li");
    var $gnb_dep2 = $("#gnb > ul > li .gnb-2dep");
    var $gnbBg = $('.gnb-overlay-bg');
    var $snb = $(".snb");

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

    gnb_each_on();

    // gnb 각각메뉴
    function gnb_each_on () {
        $gnbList.children("li").children("a").on("mouseenter focus",function  () {
            $gnbList.children("li").removeClass("on").children(".gnb-2dep").removeClass("open");
            $(this).parent("li").addClass("on").children(".gnb-2dep").addClass("open");
        })

        $gnbList.on("mouseleave",gnb_return);
        $gnbList.find("a").last().on("focusout",gnb_return);

        function gnb_return () {
            if (!$gnb.find('*').is(':animated')) {
                $gnbList.children("li").removeClass("on").children(".gnb-2dep").removeClass("open");
            }
            if ( dep1 > 0 && dep2 ) {
                $gnbList.children("li").eq(dep1-1).addClass("active");
            }
        }
    }

    // gnb 2차 메뉴에 마우스 올렸을때 대메뉴 on
    $gnb_dep2.hover(function(){
        $(this).parent("li").addClass("on");
    },function  () {
        $gnb_dep1.removeClass("on");
    });

    // 서브메뉴에서 해당메뉴 on
    if ( dep1 > 0 && dep2 > 0) {
        $gnbList.children("li").eq(dep1-1).addClass("active");
        $gnbMList.eq(dep1-1).addClass("on");
        $snb.each(function  () {
            $(this).find("li").eq(dep2-1).addClass("on");
        });
    }

    /*  서브메뉴 셋팅  */
    $("#topMenuConPC li.on").each(function  () {
        var first_navPosition = $(this).children("a").position().left;
        var first_navPadding = parseInt($(this).children("a").css("padding-left"))/2;

        $(".nav-on-icon").css({
            left:first_navPosition + first_navPadding,
            width:$(this).children("a").outerWidth()
        });
    });
    $(window).resize(function  () {
        $("#topMenuConPC li.on").each(function  () {
            var first_navPosition = $(this).children("a").position().left;
            var first_navPadding = parseInt($(this).children("a").css("padding-left"))/2;

            $(".nav-on-icon").css({
                left:first_navPosition + first_navPadding,
                width:$(this).children("a").outerWidth()
            });
        });
    });
    /*  서브메뉴 오버시  */
    $("#topMenuConPC li").on("mouseenter focusin",function(){
        var navPosition = $(this).children("a").position().left;	/* bar의 시작점은 li의 left 좌표 */
        var navPadding = parseInt($(this).children("a").css("padding-left"))/2;

        var navWidth = $(this).children("a").outerWidth();	/* bar 길이 li > a의 길이와 같게 */
        $(".nav-on-icon").stop().animate({
            left:navPosition+navPadding,
            width:navWidth
        },400,"easeOutCubic");
    });
    /*  서브메뉴 오버 이후  */
    $("#topMenuConPC").on("mouseleave",function(){
        var last_navPosition = $("#topMenuConPC li.on").children("a").position().left;
        var last_navPadding = parseInt($("#topMenuConPC li.on a").css("padding-left"))/2;

        $(".nav-on-icon").stop().animate({
            left:last_navPosition+last_navPadding,
            width:$("#topMenuConPC li.on a").outerWidth()
        },400,"easeOutCubic");
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

    /* GNB MOBILE 2DEPTH 클래스 붙이기  */
    $("#gnbM > ul > li:has('.gnb-2dep')").addClass("has-2dep");
    $("#gnbM > ul > li:has('.gnb-2dep')").each(function  () {
        $(this).children("a").append("<span class='gnb-icon close-icon'><i class='material-icons'>&#xE145;</i></span><span class='gnb-icon open-icon' style='display:none;'><i class='material-icons'>&#xE15B;</i></span>");
    });

    /* GNB MOBILE 2DEPTH 오픈 */
    $("#gnbM > ul > li:has('.gnb-2dep')").children("a").click(function(event){
        /* 2dep가 열려있을때 */
        if ( $(this).parent("li").hasClass("active") ){
            $(this).parent("li").removeClass("active");
            $(this).children(".open-icon").hide();
            $(this).children(".close-icon").show();
            $(this).siblings(".gnb-2dep").slideUp(400);
        }
        /* 2dep가 닫혀있을때 */
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

    /* 해당페이지의 GNB 모바일 2depth 열기 & ON  */
    if ( dep1> 0 && dep2> 0 ) {
        $gnbM.children("ul").children("li").eq(dep1-1).addClass("active").children(".gnb-2dep").show().children("li").eq(dep2-1).addClass("on");
        $gnbM.children("ul").children("li").eq(dep1-1).find(".close-icon").hide();
        $gnbM.children("ul").children("li").eq(dep1-1).find(".open-icon").show(); // 모바일 네비 on
    }

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







/* *******************************************************
 * filename : nav.js
 * description : �ㅻ퉬寃뚯씠�� 諛� �ъ씠�쒕컮 active �� 硫붾돱�� 愿��⑤맂 JS
 * date : 2017-05-30
******************************************************** */


var dep1;
var dep2;

jQuery(function($){
    $gnb = $("#gnb");
    $gnbList = $("#gnb > ul");
    $gnb2dep = $("#gnb > ul > li .gnb-2dep");

    // �믪씠媛�
    var closeHeight = $gnb.height();
    var openHeight = 380;

    gnb_on_total();

    /* ======== GNB �꾩껜 ( 而⑦뀗痢좊뜮湲� )  ========== */
    function gnb_on_total () {
        $gnbList.mouseenter(function(){
            if (!($gnb2dep.is(".open"))) {
                $("#gnbPcBg").addClass("open");
                $("#gnb > ul > li .gnb-2dep").addClass("open");
                $("body").prepend("<div class='gnb-bg'></div>");
            }
        });
        $gnbList.mouseleave(function(){
            if ($gnb2dep.is(".open")) {
                $("#gnbPcBg").removeClass("open");
                $("#gnb > ul > li .gnb-2dep").removeClass("open");
                $(".gnb-bg").remove();
            }
        });
    }

    /*  �ㅻ쾭�섍굅�� �ъ빱�ㅺ� �덉쓣寃쎌슦 �쒖꽦�� */
    $gnbList.children("li").on("mouseenter focusin",function(){
        $(this).addClass("on");
    }).on("mouseleave focusout", function(){
        $(this).removeClass("on");
    });

    if ( dep1> 0 && dep2> 0 ) {
        $gnbList.children("li").eq(dep1-1).addClass("on");
        $(".snb").children("li").eq(dep2-1).addClass("on");
        if ($gnbList.children("li").hasClass("on")) {
            $gnbList.children("li:not(.on)").on('mouseenter focusin',function  () {
                $gnbList.children("li").removeClass("on");
            }).on('mouseleave focusout',function  () {
                $gnbList.children("li").eq(dep1-1).addClass("on");
            })
        }
        // hover濡� li 諛깃렇�쇱슫�� 蹂�寃�
        if ($(".snb").children("li").hasClass("on")) {
            $(".snb").children("li:not(.on)").on('mouseenter focusin',function  () {
                $(".snb").children("li").removeClass("on");
            }).on('mouseleave focusout',function  () {
                $(".snb").children("li").eq(dep2-1).addClass("on");
            })
        }
    };

    /* ===================
        ### 怨듯넻 �쒖씠荑쇰━  ###
    ====================*/
    /* �ㅻⅨ履� GNB �ㅽ뵂 */
    var menu = "close";
    /* GNB Menu Animation Delay */
    $("#navigation > li").each(function( index ) {
        $( this ).css({'animation-delay': (index*80)+'ms'});
    });

    $(".nav-open-btn").click(function  () {
        if ( menu == "open" ) {
            menuClose();
            menu = "close";
        }else {
            menuOpen();
            menu = "open";
        }
        return false;
    });

    $("#gnbBg, .close-box").click(function  () {
        menuClose();
        menu = "close";
    });
    function menuOpen () {
        $(".nav-open-btn").addClass("on");
        $('#m-gnb').show().addClass("open");
        $('#gnbBg').fadeIn();
        //$("body").css({'height':$(window).height(), 'overflow':'hidden'});
    }

    function menuClose () {
        $(".nav-open-btn").removeClass("on");
        $('#m-gnb').stop().removeClass("open");
        $('#gnbBg').hide();
        //$("body").css({'height':'auto', 'overflow':'auto'});
    }

    /* GNB 2DEPTH �ㅽ뵂�섍린 */
    $("#navigation > li:has('ul')").children("a").click(function(event){
        /* 2dep媛� �대젮�덉쓣�� */
        if ( $(this).parent("li").hasClass("active") ){
            $(this).parent("li").removeClass("active");
            $(this).siblings("ul").slideUp(400);
        }
        /* 2dep媛� �ロ��덉쓣�� */
        else{
            $("#navigation > li").has("ul").each(function() {
                if ( $(this).hasClass("active") ){
                    $(this).removeClass("active");
                    $(this).children("ul").slideUp(400);
                }
            });
            $(this).parent("li").addClass("active");
            $(this).siblings("ul").slideDown(400);
        }
        return false;
    });

    /* ------ HEADER FIXED------ */
    var w_width = $(window).innerWidth();

    if ( $("#headerInner").css("display") == "block" ) {
        var startTop = $("#headerInner").height();

        $(window).scroll(function  () {
            var s_top = $(window).scrollTop();
            if ( s_top < startTop ) {
                $("#headerInner").removeClass("fixed");
                $("#gnb").removeClass("fixed");
                //$("#m-gnb #navigation").css('height',w_height - 97);
            }else {
                $("#headerInner").addClass("fixed");
                $("#gnb").addClass("fixed");
                //$("#m-gnb #navigation").css('height',w_height - 73);
            }
        });
    }
    $(window).resize(function  () {
        w_width = $(window).innerWidth();
        if ( $("#headerInner").css("display") == "block" ) {
            $(window).scroll(function  () {
                var s_top = $(window).scrollTop();
                if ( s_top < startTop ) {
                    $("#headerInner").removeClass("fixed");
                    $("#gnb").removeClass("fixed");
                }else {
                    $("#headerInner").addClass("fixed");
                    $("#gnb").addClass("fixed");
                }
            });
        }
    });

    //var menu_lang = $gnbList.children("li").eq(dep1-1).find("li").length;
    var menu_lang = $gnbList.children("li").length;
    $(".sub-page-loc.page-prev > span").html($gnbList.children("li").eq(dep1-2).children("a").text().toUpperCase());
    $(".sub-page-loc.page-next > span").html($gnbList.children("li").eq(dep1).children("a").text().toUpperCase());
    $(".sub-page-loc.page-prev").attr("href",$gnbList.children("li").eq(dep1-2).children("a").attr("href"));
    $(".sub-page-loc.page-next").attr("href",$gnbList.children("li").eq(dep1).children("a").attr("href"));

    if ( dep1 == menu_lang ) {
        $(".sub-page-loc.page-next > span").html($gnbList.children("li").eq(0).children("a").text().toUpperCase());
        $(".sub-page-loc.page-next").attr("href",$gnbList.children("li").eq(0).children("a").attr("href"));
    }else if ( dep1 == 1 ) {
        $(".sub-page-loc.page-prev > span").html($gnbList.children("li").eq(menu_lang-1).children("a").text().toUpperCase());
        $(".sub-page-loc.page-prev").attr("href",$gnbList.children("li").eq(menu_lang-1).children("a").attr("href"));
    };

    /* ------ �곷떒 移댄뀒怨좊━ ------ */
    var $cateBox = $("#sidebar");
    var $cateBtn = $(".open-cate");
    var $cateList = $(".top-menu-list");
    var $cateBtn_2 = $(".tit-open-cate");
    var $cateList_2 = $(".depth1-tit");

    // �곷떒移댄뀒怨좊━ Dep1
    $cateBtn_2.click(function  () {
        if ( $(this).children("ul").css("display") == "none") {
            $(this).children("ul").stop().slideDown();
        }
        //return false;
    });
    $cateBtn_2.on("mouseleave",function  () {
        $(this).children("ul").stop().slideUp();
    });

    // �곷떒移댄뀒怨좊━ Dep2
    $cateBtn.click(function  () {
        if ( $(this).children("ul").css("display") == "none") {
            $(this).children("ul").stop().slideDown();
        }
        //return false;
    });
    $cateBtn.on("mouseleave",function  () {
        $(this).children("ul").stop().slideUp();
    });
});



