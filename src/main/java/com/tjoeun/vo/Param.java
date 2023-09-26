package com.tjoeun.vo;

import lombok.Data;

@Data
public class Param {
	
	private int startNo;
	private int endNo;
	private int minLimitAge = 19;
	private int maxLimitAge = 80;
	private String condition;
	private String item;
	private String location;
	private String acholchk;
	private String partyGender;
	private String category;
//	신고용 
	private int originIdx;
	private String reportId;
	
}
