package com.example.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

	@RequestMapping(method = RequestMethod.GET, value = "/pro_ott_box")
	public String pro_ott_box(Model model) {
		return "product/pro_ott_box";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pro_720homecctv")
	public String pro_720homecctv(Model model) {
		return "product/pro_720homecctv";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/pro_disital_board")
	public String company_org(Model model) {
		return "product/pro_disital_board";
	}
	
	
}
