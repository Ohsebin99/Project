package com.tjoeun.vo;

import java.util.Date;

import lombok.Data;

@Data
public class RepleVO {
	
	private int repleIdx;			// PK
	private int originIdx;			// 방 idx
	private String masterId;		// 방생성자
	private String reple;			// 글내용
	
	private String repleId;			// 모임원 아이디
	private String repleNickName;	// 모임원 닉네임
	private String repleGender;		// 모임원 성별
	private int repleAge;			// 모임원 나이
	private int repleLimitNum;		// 모임원 제한나이
	private String replePhoto;		// 모임원 사진

	private Date writeDate;			// 작성일
	private String repleIp;			// IP
	private String fix = "N";		// 고정 유무 
}
