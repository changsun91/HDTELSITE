package com.example.site.controller;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="NOTICE")
public class NoticeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "NT_CODE")
	private Integer ntCode;
	
    @Column(name = "NT_SUBJECT", nullable = false)
    private String ntSubject;
    @Column(name = "NT_MESSAGE", nullable = false)
    private String ntMessage;
    @Column(name = "NT_STATE", nullable = false)
    private String ntState;
    @Column(name = "NT_DATE", nullable = false, updatable = false)
    private String ntDate;
    @Column(name = "NT_DEL", nullable = false)
    private String ntDel;
    @Column(name = "NT_ROUTE", nullable = true)
    private String ntRoute;
    @Column(name = "NT_FILENAME", nullable = true)
    private String ntFilename;


	public NoticeEntity(){}
}
