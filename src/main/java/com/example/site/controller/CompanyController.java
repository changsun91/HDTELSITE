package com.example.site.controller;

import java.util.HashMap;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.site.service.DataService;
import com.example.site.service.TalentService;

@Controller
public class CompanyController {

	@Value("${default.image.path}")
	private String defaultImagePath;
	@Autowired
	TalentService talentService;

	@RequestMapping(method = RequestMethod.GET, value = "/company_intro")
	public String company_info(Model model) {
		return "company/company_intro";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/company_summary")
	public String company_summary(Model model) {
		return "company/company_summary";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/company_org")
	public String company_org(Model model) {
		return "company/company_org";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/company_ipr")
	public String company_ipr(Model model) {
		return "company/company_ipr";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/company_map")
	public String company_map(Model model) {
		return "company/company_map";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/company_talent/{page}")
	public String talent(@PathVariable Integer page, Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		// 페이지 설정
		Pageable pageable = null;
		// 목록
		Page<TalentEntity> talentEntityList = null;

		// 페이지 설정
		pageable = PageRequest.of(page - 1, 10, Sort.Direction.DESC, "TA_DATE");
		// 목록
		talentEntityList = talentService.listTalentRecent(pageable);
		if (!ObjectUtils.isEmpty(talentEntityList)) {
			HashMap<String, Object> talentResult = new HashMap<>();
			// 게시판 정보
			talentResult.put("info", talentEntityList);
			// 전체 수
			talentResult.put("total", talentEntityList.getTotalElements());
			// 현재 페이지
			talentResult.put("page", page);

			model.addAttribute("talent", talentResult);
		} else {
			model.addAttribute("talent", null);
		}

		return "company/company_talent";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/talent/view/{talentCode}")
	public String talentDetail(@PathVariable Integer talentCode, Model model, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Optional<TalentEntity> talentDetail = talentService.detail(talentCode);
		if (talentDetail.isPresent()) {
			model.addAttribute("talentDetail", talentDetail.get());
		} else {
			model.addAttribute("talentDetail", null);
		}

		return "company/company_talent_detail";
	}

}
