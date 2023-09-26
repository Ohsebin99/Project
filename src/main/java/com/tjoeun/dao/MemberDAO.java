package com.tjoeun.dao;

import java.util.ArrayList;

import com.tjoeun.vo.MemberVO;

public interface MemberDAO {

	void memberInsert(MemberVO memberVO);

	MemberVO selectByID(MemberVO memberVO);

	MemberVO selectByName(MemberVO mo);

	MemberVO passwordSerch(MemberVO memberVO);

	int IDCheck(String id);

	void myProfileUpdate(MemberVO memberVO);

	void passwordChange(MemberVO memberVO);

	String selectEmail(String id);

	void insertNaverUser(MemberVO memberVO);

	MemberVO memberList(String repleId);

	ArrayList<MemberVO> selectMembers();
}
