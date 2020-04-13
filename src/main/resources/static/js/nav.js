var dep1;
var dep2;

jQuery(function($){
	$gnb = $("#gnb");
	$gnbList = $("#gnb > ul");
	$gnb2dep = $("#gnb > ul > li .gnb-2dep");

	// 높이값
	var closeHeight = $gnb.height();
	var openHeight = 380;

	gnb_on_total();

	/* ======== GNB 전체 ( 컨텐츠덮기 )  ========== */
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

	/*  오버하거나 포커스가 있을경우 활성화 */
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
		// hover로 li 백그라운드 변경
		if ($(".snb").children("li").hasClass("on")) {
			$(".snb").children("li:not(.on)").on('mouseenter focusin',function  () {
				$(".snb").children("li").removeClass("on");
			}).on('mouseleave focusout',function  () {
				$(".snb").children("li").eq(dep2-1).addClass("on");
			})
		}
	};



	/* ===================
		### 공통 제이쿼리  ### 
	====================*/
	/* 오른쪽 GNB 오픈 */
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

	/* GNB 2DEPTH 오픈하기 */
	$("#navigation > li:has('ul')").children("a").click(function(event){
		/* 2dep가 열려있을때 */
		if ( $(this).parent("li").hasClass("active") ){
			$(this).parent("li").removeClass("active");
			$(this).siblings("ul").slideUp(400);
		}
		/* 2dep가 닫혀있을때 */
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

	/* ------ 상단 카테고리 ------ */
	var $cateBox = $("#sidebar");
	var $cateBtn = $(".open-cate");
	var $cateList = $(".top-menu-list");
	var $cateBtn_2 = $(".tit-open-cate");
	var $cateList_2 = $(".depth1-tit");

	// 상단카테고리 Dep1
	$cateBtn_2.click(function  () {
		if ( $(this).children("ul").css("display") == "none") {
			$(this).children("ul").stop().slideDown();
		}
		//return false;
	});
	$cateBtn_2.on("mouseleave",function  () {
		$(this).children("ul").stop().slideUp();
	});

	// 상단카테고리 Dep2
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
