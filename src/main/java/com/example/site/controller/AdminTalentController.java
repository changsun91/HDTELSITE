package com.example.site.controller;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.IOUtils;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.example.site.service.TalentService;
@Controller
public class AdminTalentController {
    @Value("${default.image.path}")
    private String defaultImagePath;
	@Autowired
	TalentService talentService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/admintalent/{page}")
	public String data(@PathVariable Integer page, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
        // 페이지 설정
        Pageable pageable = null;
        // 목록
        Page<TalentEntity> talentEntityList = null;
        
        // 페이지 설정
        pageable = PageRequest.of(page-1, 10, Sort.Direction.DESC, "TA_DATE");
        // 목록
        talentEntityList = talentService.listTalentRecent(pageable);
        if(!ObjectUtils.isEmpty(talentEntityList)) {
            HashMap<String, Object> talentResult = new HashMap<>();
            // 게시판 정보
            talentResult.put("info", talentEntityList);
            // 전체 수
            talentResult.put("total", talentEntityList.getTotalElements());
            // 현재 페이지
            talentResult.put("page", page);


            model.addAttribute("talent", talentResult);
        }else{
            model.addAttribute("talent", null);
        }
        
        
		return "admin/admin_company_talent";
	}
//	
	@RequestMapping(method = RequestMethod.GET, value = "/talent/adminview/{Code}")
	public String dataDetail(@PathVariable Integer Code, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Optional<TalentEntity> talentDetail = talentService.detail(Code);
		if(talentDetail.isPresent()) {
			model.addAttribute("talentDetail", talentDetail.get());	
		} else {
			model.addAttribute("talentDetail", null);	
		}
		
		return "admin/admin_company_talent_detail";
	}
//    
	@RequestMapping(method = RequestMethod.GET, value = "/talent/write")
	public String talentWrite(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "admin/admin_company_talent_write";
	}
//
	@RequestMapping(method = RequestMethod.POST, value = "/talent/insert")
	public String adminNoticeInsert(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, TalentEntity talentEntity) {
		
		
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = dateFormat.format(new Date());
        talentEntity.setTaDate(utcTime);
        talentEntity.setTaDel("Y");
        
        System.out.println(talentEntity);
		talentService.insert(talentEntity);
//		NoticeEntity noticeEntity = dataService.insert(request);
		
		return "redirect:/admintalent/1";
	}
//	
	@RequestMapping(method = RequestMethod.POST, value = "/talent/update")
	public String adminNoticeUpdate(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, TalentEntity talentEntity) {
		//새로운 파일이 아닐경우
		if(request.getParameter("newFileChk").equals("N")) {
			Optional<TalentEntity> talentDetail = talentService.detail(talentEntity.getTaCode());
			if(talentDetail.isPresent()) {
				talentEntity.setTaRoute(talentDetail.get().getTaRoute());
				talentEntity.setTaFilename(talentDetail.get().getTaFilename());
			}	
		} else {//새로운파일
			
		}
		
		
		System.out.println(talentEntity);
        
		talentService.update(talentEntity);
//		NoticeEntity noticeEntity = dataService.insert(request);
		
		return "redirect:/admintalent/1";
	}
//	
	@RequestMapping(method = RequestMethod.GET, value = "talent/delete/{Code}")
	public String noticeDataDelete(@PathVariable Integer Code, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		talentService.delete(Code);
		return "redirect:/admintalent/1";
	}
}
