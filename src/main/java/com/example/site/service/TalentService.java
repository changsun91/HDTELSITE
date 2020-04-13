package com.example.site.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.site.controller.TalentEntity;

public interface TalentService {
	// 목록 조회 - 최신순
    Page<TalentEntity> listTalentRecent(Pageable pageable);

	Optional<TalentEntity> detail(Integer Code);
	
	void insert(TalentEntity talentEntity);
//
	void delete(Integer noticeCode);
//
	void update(TalentEntity talentEntity);

	

}
