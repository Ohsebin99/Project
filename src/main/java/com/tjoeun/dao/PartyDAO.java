package com.tjoeun.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.tjoeun.vo.MemberVO;
import com.tjoeun.vo.Param;
import com.tjoeun.vo.PartyVO;

public interface PartyDAO {

	int selectCount();
	int selectCountMulti(Param param);

	ArrayList<PartyVO> selectList(HashMap<String, Integer> hmap);
	ArrayList<PartyVO> selectListMulti(Param param);

	ArrayList<PartyVO> selectSlider();
	PartyVO selectByIdx(int idx);
	void insert(PartyVO partyVO);
	ArrayList<PartyVO> createMyList(MemberVO memberVO);
	PartyVO joinMyList(int idx);
	void partyReport(PartyVO partyVO);
	void partyUpdate(PartyVO partyVO);
	void partyDelete(PartyVO partyVO);



}
