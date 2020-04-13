package com.example.site.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SolutionController {

	@RequestMapping(method = RequestMethod.GET, value = "/sol_ai")
	public String sol_ai(Model model) {
		return "solution/sol_ai";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/sol_appstore")
	public String sol_appstore(Model model) {
		return "solution/sol_appstore";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/sol_iptv")
	public String sol_iptv(Model model) {
		return "solution/sol_iptv";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/sol_karaoke")
	public String sol_karaoke(Model model) {
		return "solution/sol_karaoke";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/sol_rtc")
	public String sol_rtc(Model model) {
		return "solution/sol_rtc";
	}
	
	
}
