// header

var zoomx = 100;
$(document).ready(function() {
    $(".gnb li a").hover(
        function() {
            $(".snb").stop().slideDown(400);
        },
        function() {
            $(".snb").stop().slideUp(400);
        }
    );

    $(".snb").hover(
        function() {
            $(".snb").stop().slideDown(400);
        },
        function() {
            $(".snb").stop().slideUp(600);
        }
    );
});







(function($) { "use strict";

    $(function() {
        var header = $(".start-style");
        $(window).scroll(function() {
            var scroll = $(window).scrollTop();

            if (scroll >= 10) {
                header.removeClass('start-style').addClass("scroll-on");
            } else {
                header.removeClass("scroll-on").addClass('start-style');
            }
        });
    });

    //Animation

    $(document).ready(function() {
        $('body.hero-anime').removeClass('hero-anime');
    });

    //Menu On Hover

    $('body').on('mouseenter mouseleave','.nav-item',function(e){
        if ($(window).width() > 750) {
            var _d=$(e.target).closest('.nav-item');_d.addClass('show');
            setTimeout(function(){
                _d[_d.is(':hover')?'addClass':'removeClass']('show');
            },1);
        }
    });

    //Switch light/dark

    $("#switch").on('click', function () {
        if ($("body").hasClass("dark")) {
            $("body").removeClass("dark");
            $("#switch").removeClass("switched");
        }
        else {
            $("body").addClass("dark");
            $("#switch").addClass("switched");
        }
    });

})(jQuery);


// sticky header
$(window).scroll(function() {
    if ($(this).scrollTop() > 100){
        $('.fixed-header').addClass("sticky");
    }
    else{
        $('.fixed-header').removeClass("sticky");
    }
});


// scroll top butto
$(function() {
    $(window).scroll(function() {
        if ($(this).scrollTop() > 100) {
            $('#scroll-top-btn').fadeIn();
        } else {
            $('#scroll-top-btn').fadeOut();
        }
    });

    $("#scroll-top-btn").click(function() {
        $('html, body').animate({
            scrollTop : 0
        }, 400);
        return false;
    });


});




$(function(){
    setTimeout(function  () {
        $(".banner").addClass("active");
    });
});





// mobile nav script
$(document).ready(function(){
    $('.menu-tab').click(function(){
        $('.menu-hide').toggleClass('show');
        $('.menu-tab').toggleClass('active');
    });
    $('.card .collapse a').click(function(){
        $('.menu-hide').removeClass('show');
        $('.menu-tab').removeClass('active');
    });
});

(function () {
    'use strict'

    if (navigator.userAgent.match(/IEMobile\/10\.0/)) {
        var msViewportStyle = document.createElement('style')
        msViewportStyle.appendChild(
            document.createTextNode(
                '@-ms-viewport{width:auto!important}'
            )
        )
        document.head.appendChild(msViewportStyle)
    }

}())


function compressImage($source, $destination, $quality) {
    $info = getimagesize($source);
    switch ($info['mime']) {
        case "image/jpeg":
            $image = imagecreatefromjpeg($source);
            imagejpeg($image, $destination, $quality);
            break;
        case "image/gif":
            $image = imagecreatefromgif($source);
            imagegif($image, $destination, $quality);
            break;
        case "image/png":
            $image = imagecreatefrompng($source);
            imagepng($image, $destination, $quality);
            break;
    }
}

compressImage('source.png', 'destination.png', 85);












