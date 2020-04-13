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
@Controller
public class DataController {
    @Value("${default.image.path}")
    private String defaultImagePath;
	@Autowired
	DataService dataService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/data/{page}")
	public String data(@PathVariable Integer page, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
        // 페이지 설정
        Pageable pageable = null;
        // 목록
        Page<NoticeEntity> noticeEntityList = null;
        
        // 페이지 설정
        pageable = PageRequest.of(page-1, 10, Sort.Direction.DESC, "NT_DATE");
        // 목록
        noticeEntityList = dataService.listNoticeRecent(pageable);
        if(!ObjectUtils.isEmpty(noticeEntityList)) {
            HashMap<String, Object> noticeResult = new HashMap<>();
            // 게시판 정보
            noticeResult.put("info", noticeEntityList);
            // 전체 수
            noticeResult.put("total", noticeEntityList.getTotalElements());
            // 현재 페이지
            noticeResult.put("page", page);


            model.addAttribute("notice", noticeResult);
        }else{
            model.addAttribute("notice", null);
        }
        
        
		return "data/data";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/notice/view/{noticeCode}")
	public String dataDetail(@PathVariable Integer noticeCode, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Optional<NoticeEntity> noticeDetail = dataService.NoticeDetail(noticeCode);
		if(noticeDetail.isPresent()) {
			model.addAttribute("noticeDetail", noticeDetail.get());	
		} else {
			model.addAttribute("noticeDetail", null);	
		}
		
		return "data/data_detail";
	}
	
}
