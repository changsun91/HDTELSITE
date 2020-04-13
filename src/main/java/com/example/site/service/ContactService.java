package com.example.site.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.site.controller.ContactEntity;
import com.example.site.controller.TalentEntity;

public interface ContactService {
	// 목록 조회 - 최신순
    Page<ContactEntity> listContactRecent(Pageable pageable);

	Optional<ContactEntity> detail(Integer Code);
//	
	void insert(ContactEntity contactEntity);
////
//	void delete(Integer noticeCode);
////
//	void update(TalentEntity talentEntity);

	

}
