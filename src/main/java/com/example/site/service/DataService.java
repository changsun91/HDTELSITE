package com.example.site.service;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.site.controller.NoticeEntity;

public interface DataService {
	// 목록 조회 - 최신순
    Page<NoticeEntity> listNoticeRecent(Pageable pageable);

	Optional<NoticeEntity> NoticeDetail(Integer noticeCode);

	void delete(HashMap<String, String> noticeCode);

	void insert(NoticeEntity noticeEntity);

	void noticeDelete(Integer noticeCode);

	void update(NoticeEntity noticeEntity);

	

}
