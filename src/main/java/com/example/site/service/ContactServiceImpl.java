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

import com.example.site.controller.ContactEntity;
import com.example.site.controller.ContactRepository;
import com.example.site.controller.NoticeEntity;
import com.example.site.controller.NoticeRepository;
import com.example.site.controller.TalentEntity;
import com.example.site.controller.TalentRepository;

@Service
public class ContactServiceImpl implements ContactService{
	
	@Autowired
	private ContactRepository contactRepository;
	
	@Override
	public Page<ContactEntity> listContactRecent(Pageable pageable){
		Page<ContactEntity> contactEntityList = contactRepository.findByContactRecent(pageable);
		return contactEntityList;
	}

	@Override
	public Optional<ContactEntity> detail(Integer Code) {
		// TODO Auto-generated method stub
		Optional<ContactEntity> contactDetail = contactRepository.findById(Code);
		return contactDetail;
	}
	
	@Override
	public void insert(ContactEntity contactEntity) {
		// TODO Auto-generated method stub
		contactRepository.save(contactEntity);
	}
//	
//	@Override
//	public void delete(Integer Code) {
//		// TODO Auto-generated method stub
//		talentRepository.deleteById(Code);
//	}
////
//	@Override
//	public void update(TalentEntity talentEntity) {
//		// TODO Auto-generated method stub
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        
//        String utcTime = dateFormat.format(new Date());
//		int taCode = talentEntity.getTaCode();
//		String taRoute = talentEntity.getTaRoute();
//		String taFilename = talentEntity.getTaFilename();
//		String taSubject = talentEntity.getTaSubject();
//		String taMessage = talentEntity.getTaMessage();
//		String taState = talentEntity.getTaState();
//		String taDate = utcTime;
//		String taDel = "Y";
//		
//		talentRepository.updateTalent(taCode, taSubject, taMessage, taState, taDate, taDel, taRoute, taFilename);
//	}
//	

}
