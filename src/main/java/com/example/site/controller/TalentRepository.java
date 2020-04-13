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
public interface TalentRepository extends JpaRepository<TalentEntity, Integer>{
	// 목록 조회 - 최신순
    @Query(value = "SELECT " +
            "* " +
            "FROM TALENT " +
            "WHERE 1=1 " +
            "AND TA_DEL='Y'", nativeQuery = true)
    Page<TalentEntity> findByTalentRecent(Pageable pageable);

//    // 삭제 처리
//    @Modifying
//    @Transactional
//    @Query(value = "UPDATE NOTICE SET " +
//            "NT_DEL = 'N' " +
//            "WHERE 1=1 " +
//            "AND NT_CODE=:ntCode", nativeQuery = true)
//    int updateByStateDelete(@Param("ntCode") int ntCode);
//
    //업데이트
    @Modifying
    @Transactional
    @Query(value = "UPDATE TALENT SET " +
            "TA_SUBJECT =:taSubject, " +
            "TA_MESSAGE =:taMessage, " +
            "TA_STATE =:taState, " +
            "TA_DATE =:taDate, " +
            "TA_DEL =:taDel, " +
            "TA_ROUTE =:taRoute, " +
            "TA_FILENAME =:taFilename " +
            "WHERE 1=1 " +
            "AND TA_CODE=:taCode", nativeQuery = true)
	void updateTalent(
			@Param("taCode")int taCode, 
			@Param("taSubject")String taSubject, 
			@Param("taMessage")String taMessage, 
			@Param("taState")String taState, 
			@Param("taDate")String taDate, 
			@Param("taDel")String taDel,
			@Param("taRoute")String taRoute, 
			@Param("taFilename")String taFilename
			);
//
//    // Detail 조회
    
}
