package com.tjoeun.vo;

import java.util.ArrayList;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PartyList {
	
ArrayList<PartyVO> list = new ArrayList<PartyVO>();
	
	private	int pageSize = 12;		// 1 �������� ǥ���Ϸ��� ���� ����
	private	int totalCount = 0; 	// ���̺� ����� ��ü ���� ����
	private	int totalPage = 0; 		// ��ü ������ ����
	private	int currentPage = 1; 	// ���� �������� ǥ�õǴ� ������ ��ȣ
	private	int startNo = 0; 		// ���� �������� ǥ�õǴ� ���� ���� �ε��� ��ȣ
	private	int endNo = 0; 			// ���� �������� ǥ�õǴ� ���� ������ �ε��� ��ȣ
	private	int startPage = 0; 		// ������ �̵� �����۸�ũ �Ǵ� ��ư�� ǥ�õ� ���� ������ ��ȣ
	private	int endPage = 0; 		// ������ �̵� �����۸�ũ �Ǵ� ��ư�� ǥ�õ� ������ ������ ��ȣ
	
	
	public void initPartyList(int pageSize, int totalCount, int currentPage) {
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		this.currentPage = currentPage;
		calculator();
	}	
	
	// pageSize, totalCount, currentPage�� ������ ������ ������ ����ؼ� �ʱ�ȭ ��Ű�� �޼ҵ�
	private void calculator() {
		totalPage = (totalCount - 1) / pageSize + 1;
		currentPage = currentPage > totalPage ? totalPage : currentPage;
 		startNo = (currentPage - 1) * pageSize + 1;
		endNo = startNo + pageSize - 1;
		endNo = endNo > totalCount ? totalCount : endNo;
		startPage = (currentPage-1)/10 * 10  + 1;
		endPage = startPage + 9;
		endPage = endPage > totalPage ? totalPage : endPage;
	}
	
}
