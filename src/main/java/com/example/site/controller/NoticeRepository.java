package com.example.site.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer>{
	// 목록 조회 - 최신순
    @Query(value = "SELECT " +
            "* " +
            "FROM NOTICE " +
            "WHERE 1=1 " +
            "AND NT_DEL='Y'", nativeQuery = true)
    Page<NoticeEntity> findByNoticeRecent(Pageable pageable);

    // 삭제 처리
    @Modifying
    @Transactional
    @Query(value = "UPDATE NOTICE SET " +
            "NT_DEL = 'N' " +
            "WHERE 1=1 " +
            "AND NT_CODE=:ntCode", nativeQuery = true)
    int updateByStateDelete(@Param("ntCode") int ntCode);

    //업데이트
    @Modifying
    @Transactional
    @Query(value = "UPDATE NOTICE SET " +
            "NT_SUBJECT =:ntSubject, " +
            "NT_MESSAGE =:ntMessage, " +
            "NT_STATE =:ntState, " +
            "NT_DATE =:ntDate, " +
            "NT_DEL =:ntDel, " +
            "NT_ROUTE =:ntRoute, " +
            "NT_FILENAME =:ntFilename " +
            "WHERE 1=1 " +
            "AND NT_CODE=:ntCode", nativeQuery = true)
	void updateNotice(
			@Param("ntCode")int ntCode, 
			@Param("ntSubject")String ntSubject, 
			@Param("ntMessage")String ntMessage, 
			@Param("ntState")String ntState, 
			@Param("ntDate")String ntDate, 
			@Param("ntDel")String ntDel,
			@Param("ntRoute")String ntRoute, 
			@Param("ntFilename")String ntFilename
			);

    // Detail 조회
    
}
