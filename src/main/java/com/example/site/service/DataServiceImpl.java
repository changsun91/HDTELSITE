package com.example.site.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Optional;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.site.controller.NoticeEntity;
import com.example.site.controller.NoticeRepository;

@Service
public class DataServiceImpl implements DataService{
	
	@Autowired
	private NoticeRepository noticeRepository;
	
	@Override
	public Page<NoticeEntity> listNoticeRecent(Pageable pageable){
		Page<NoticeEntity> noticeEntityList = noticeRepository.findByNoticeRecent(pageable);
		return noticeEntityList;
	}

	@Override
	public Optional<NoticeEntity> NoticeDetail(Integer noticeCode) {
		// TODO Auto-generated method stub
		Optional<NoticeEntity> noticeDetail = noticeRepository.findById(noticeCode);
		return noticeDetail;
	}

	@Transactional
	public void delete(HashMap<String, String> noticeCode) {
		// TODO Auto-generated method stub
		int ntCode = Integer.parseInt(noticeCode.get("ntCode"));
		noticeRepository.updateByStateDelete(ntCode);
	}

	@Override
	public void insert(NoticeEntity noticeEntity) {
		// TODO Auto-generated method stub
		noticeRepository.save(noticeEntity);
	}

	@Override
	public void noticeDelete(Integer noticeCode) {
		// TODO Auto-generated method stub
		noticeRepository.deleteById(noticeCode);
	}

	@Override
	public void update(NoticeEntity noticeEntity) {
		// TODO Auto-generated method stub
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        
        String utcTime = dateFormat.format(new Date());
		int ntCode = noticeEntity.getNtCode();
		String ntRoute = noticeEntity.getNtRoute();
		String ntFilename = noticeEntity.getNtFilename();
		String ntSubject = noticeEntity.getNtSubject();
		String ntMessage = noticeEntity.getNtMessage();
		String ntState = noticeEntity.getNtState();
		String ntDate = utcTime;
		String ntDel = "Y";
		
		noticeRepository.updateNotice(ntCode, ntSubject, ntMessage, ntState, ntDate, ntDel, ntRoute, ntFilename);
	}
	

}
