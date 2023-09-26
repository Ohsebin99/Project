package com.tjoeun.vo;

import lombok.Data;

@Data
public class ScoreVO {
	
	private String scoreId;	// PK
	private int promise;	// 약속점수
	private int manner;		// 매너점수
	private int clean;		// 위생점수
	private int scoreTotal;	// 총점
	private int scoreCount;	// 평가개수
}
