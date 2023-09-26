package com.tjoeun.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.tjoeun.vo.RepleVO;

public interface RepleDAO {


	void repleInsert(RepleVO replevo);

	ArrayList<RepleVO> selectRepleList(int idx);

	ArrayList<RepleVO> selectByRepleID(String id);

	void updateJoin(int repleIdx);

	void deleteJoin(int repleIdx);

	int IDCheck(String repleId);
	
	RepleVO selectRepleId(int repleIdx);

	int selectById_Idx(RepleVO repleVO);


}
