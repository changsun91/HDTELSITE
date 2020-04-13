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

import com.example.site.service.ContactService;
import com.example.site.service.TalentService;
@Controller
public class AdminContactController {
    @Value("${default.image.path}")
    private String defaultImagePath;
	@Autowired
	ContactService contactService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/contact")
	public String contact(Model model) {
		return "contact";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/admincontact/{page}")
	public String data(@PathVariable Integer page, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
        // 페이지 설정
        Pageable pageable = null;
        // 목록
        Page<ContactEntity> contactEntityList = null;
        
        // 페이지 설정
        pageable = PageRequest.of(page-1, 10, Sort.Direction.DESC, "CT_DATE");
        // 목록
        contactEntityList = contactService.listContactRecent(pageable);
        if(!ObjectUtils.isEmpty(contactEntityList)) {
            HashMap<String, Object> contactResult = new HashMap<>();
            // 게시판 정보
            contactResult.put("info", contactEntityList);
            // 전체 수
            contactResult.put("total", contactEntityList.getTotalElements());
            // 현재 페이지
            contactResult.put("page", page);


            model.addAttribute("contact", contactResult);
        }else{
            model.addAttribute("contact", null);
        }
        
        
		return "admin/admin_contact";
	}
//	
	@RequestMapping(method = RequestMethod.GET, value = "/contact/adminview/{Code}")
	public String dataDetail(@PathVariable Integer Code, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Optional<ContactEntity> contactDetail = contactService.detail(Code);
		if(contactDetail.isPresent()) {
			model.addAttribute("contactDetail", contactDetail.get());	
		} else {
			model.addAttribute("contactDetail", null);	
		}
		
		return "admin/admin_contact_detail";
	}
//    
//	@RequestMapping(method = RequestMethod.GET, value = "/talent/write")
//	public String talentWrite(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		return "admin/admin_company_talent_write";
//	}
////
	@RequestMapping(method = RequestMethod.POST, value = "/contact/insert")
	public String adminNoticeInsert(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, ContactEntity contactEntity) {
		
		
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = dateFormat.format(new Date());
        contactEntity.setCtDate(utcTime);
        contactEntity.setCtDel("Y");
        
        System.out.println(contactEntity);
        contactService.insert(contactEntity);
//		NoticeEntity noticeEntity = dataService.insert(request);
		
		return "redirect:/";
	}
//	
//	@RequestMapping(method = RequestMethod.POST, value = "/talent/update")
//	public String adminNoticeUpdate(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, TalentEntity talentEntity) {
//		//새로운 파일이 아닐경우
//		if(request.getParameter("newFileChk").equals("N")) {
//			Optional<TalentEntity> talentDetail = talentService.detail(talentEntity.getTaCode());
//			if(talentDetail.isPresent()) {
//				talentEntity.setTaRoute(talentDetail.get().getTaRoute());
//				talentEntity.setTaFilename(talentDetail.get().getTaFilename());
//			}	
//		} else {//새로운파일
//			
//		}
//		
//		
//		System.out.println(talentEntity);
//        
//		talentService.update(talentEntity);
////		NoticeEntity noticeEntity = dataService.insert(request);
//		
//		return "redirect:/admintalent/1";
//	}
////	
//	@RequestMapping(method = RequestMethod.GET, value = "talent/delete/{Code}")
//	public String noticeDataDelete(@PathVariable Integer Code, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
//		talentService.delete(Code);
//		return "redirect:/admintalent/1";
//	}
}
