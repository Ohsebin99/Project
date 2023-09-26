package com.tjoeun.vo;

import java.util.Date;

import lombok.Data;

@Data
public class PartyVO {
	
	private int idx; 				// PK
	private String id;		 	  	// 만든 사람 id
	private String nickName;  	  	// 닉네임
	private String category;  	 	// 카테고리
	private String location; 	   	// 지역
	private String subject;   		// 글제목
	private String content;   	 	// 글내용
	private String place;      	 	// 장소
	private Date mealDate;    		// 식사일
	private Date writeDate;   		// 작성일
	private String thumbnail;   	// 썸네일 사진
	private String acholchk;		// 술 여부
	private String ip;          	// IP 주소
	private String partyGender; 	// 성별 제한
	private int minLimitAge = 18;	// 최소 나이 제한
	private int maxLimitAge = 100;	// 최대 나이 제한
	private int limitNum = 2;		// 인원수 제한
	private String show = "Y";		// 삭제 후 'N'으로 변경
	private int deleteReport = 0; 	// 신고 수
	private String masterPhoto;     // 모임장사진
}
