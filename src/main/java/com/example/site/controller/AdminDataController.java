package com.example.site.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.site.util.MultipartFileSender;
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
import java.util.*;

import com.example.site.service.DataService;
@Controller
public class AdminDataController {
    @Value("${default.image.path}")
    private String defaultFilePath;
	@Autowired
	DataService dataService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/admindata/{page}")
	public String data(@PathVariable Integer page, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		
        // ������ ����
        Pageable pageable = null;
        // ���
        Page<NoticeEntity> noticeEntityList = null;
        
        // ������ ����
        pageable = PageRequest.of(page-1, 10, Sort.Direction.DESC, "NT_DATE");
        // ���
        noticeEntityList = dataService.listNoticeRecent(pageable);
        if(!ObjectUtils.isEmpty(noticeEntityList)) {
            HashMap<String, Object> noticeResult = new HashMap<>();
            // �Խ��� ����
            noticeResult.put("info", noticeEntityList);
            // ��ü ��
            noticeResult.put("total", noticeEntityList.getTotalElements());
            // ���� ������
            noticeResult.put("page", page);


            model.addAttribute("notice", noticeResult);
        }else{
            model.addAttribute("notice", null);
        }
        
        
		return "admin/admin_data";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/notice/adminview/{noticeCode}")
	public String dataDetail(@PathVariable Integer noticeCode, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Optional<NoticeEntity> noticeDetail = dataService.NoticeDetail(noticeCode);
		if(noticeDetail.isPresent()) {
			model.addAttribute("noticeDetail", noticeDetail.get());	
		} else {
			model.addAttribute("noticeDetail", null);	
		}
		
		return "admin/admin_data_detail";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/data/write")
	public String dataWrite(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		return "admin/admin_data_write";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/data/insert")
	public String adminNoticeInsert(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, NoticeEntity noticeEntity) {
		
		
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String utcTime = dateFormat.format(new Date());
        noticeEntity.setNtDate(utcTime);
        noticeEntity.setNtDel("Y");
        
        System.out.println(noticeEntity);
		dataService.insert(noticeEntity);
//		NoticeEntity noticeEntity = dataService.insert(request);
		
		return "redirect:/admindata/1";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/data/update")
	public String adminNoticeUpdate(Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session, NoticeEntity noticeEntity) {
		//���ο� ������ �ƴҰ��
		if(request.getParameter("newFileChk").equals("N")) {
			Optional<NoticeEntity> noticeDetail = dataService.NoticeDetail(noticeEntity.getNtCode());
			if(noticeDetail.isPresent()) {
				noticeEntity.setNtRoute(noticeDetail.get().getNtRoute());
				noticeEntity.setNtFilename(noticeDetail.get().getNtFilename());
			}	
		} else {//���ο�����
			
		}
		
		
		System.out.println(noticeEntity);
        
		dataService.update(noticeEntity);
//		NoticeEntity noticeEntity = dataService.insert(request);
		
		return "redirect:/admindata/1";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "notice/delete/{noticeCode}")
	public String noticeDataDelete(@PathVariable Integer noticeCode, Model model, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		dataService.noticeDelete(noticeCode);
		return "redirect:/admindata/1";
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/upload")
    public @ResponseBody HashMap<String, Object> uploadFileStreaming(MultipartHttpServletRequest request) throws Exception {
		// JSON ��� ����
        HashMap<String, Object> result = new HashMap<>();
        ArrayList<HashMap<String, Object>> infoArray = new ArrayList<>();

        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {
                // ���������� ��û�� ���
                result.put("result", "false");
                result.put("message", "Not a multipart request.");
                return result;
            }

            String path = "";
            String url = "";
            
            path = defaultFilePath;
//            path = "C:/Project/backup/testFile";
			System.out.println(path);
            url = "/attachments/display/";
            	
          
           Iterator<String> iter = request.getFileNames();
           
           while(iter.hasNext()) {
        	  HashMap<String,Object> infoResult = new HashMap<>();
              String uploadFileName = iter.next();
                 // ���� ����
              MultipartFile file = request.getFile(uploadFileName);
              
              if(file != null){
            	  
            	 InputStream stream =   file.getInputStream();
//            	 String filename = file.getName(); 
            	 String filename = file.getOriginalFilename(); 
            	 
//            	 UUID uuid = UUID.randomUUID(); 
//            	 String savedName = uuid.toString() + "_" + filename;
            	 String savedName = filename;
              
                 OutputStream out = new FileOutputStream(path + "/" + savedName);
                 IOUtils.copy(stream, out); 
				 stream.close(); out.close();
				  
			     infoResult.put("name", savedName); 
			     infoResult.put("url", url + savedName); }
				  
				 infoArray.add(infoResult); 
                 
              }

            result.put("result", "true");
            result.put("info", infoArray);
        } catch (Exception e) {
            // Internal server IO error
            result.put("result", "false");
            result.put("message", e.toString());
        }

        return result;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/attachments/display/{fileName}")
	public void displayVideoFile(@PathVariable String fileName,
								 HttpServletRequest request,
								 HttpServletResponse response) throws Exception{
		// 화일 경로
		String filePath = defaultFilePath + "/" + fileName;
		File file = new File(filePath);
		try{
			MultipartFileSender.fromFile(file).with(request).with(response).serveResource();
		}catch(Exception e){

		}
	}
}
