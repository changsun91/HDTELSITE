package com.example.site.controller;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="TALENT")
public class TalentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TA_CODE")
	private Integer taCode;
	
    @Column(name = "TA_SUBJECT", nullable = false)
    private String taSubject;
    @Column(name = "TA_MESSAGE", nullable = false)
    private String taMessage;
    @Column(name = "TA_STATE", nullable = false)
    private String taState;
    @Column(name = "TA_DATE", nullable = false, updatable = false)
    private String taDate;
    @Column(name = "TA_DEL", nullable = false)
    private String taDel;
    @Column(name = "TA_ROUTE", nullable = true)
    private String taRoute;
    @Column(name = "TA_FILENAME", nullable = true)
    private String taFilename;


	public TalentEntity(){}
}
