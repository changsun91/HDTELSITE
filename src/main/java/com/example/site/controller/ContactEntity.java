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
@Table(name="CONTACT")
public class ContactEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CT_CODE")
	private Integer ctCode;
	
	@Column(name = "CT_NAME", nullable = false)
    private String ctName;
	
	@Column(name = "CT_EMAIL", nullable = false)
    private String ctEmail;
	
	@Column(name = "CT_PHONE", nullable = false)
    private String ctPhone;
	
	@Column(name = "CT_ROUTE", nullable = false)
    private String ctRoute;
	
	@Column(name = "CT_FILENAME", nullable = false)
    private String ctFilename;
	
	@Column(name = "CT_STATE", nullable = false)
    private String ctState;
	
	@Column(name = "CT_SUBJECT", nullable = false)
    private String ctSubject;
	
	@Column(name = "CT_DEL", nullable = false)
    private String ctDel;

	@Column(name = "CT_MESSAGE", nullable = false)
    private String ctMessage;
	
    @Column(name = "CT_DATE", nullable = false, updatable = false)
    private String ctDate;


	public ContactEntity(){}
}
