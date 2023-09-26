package com.tjoeun.dao;


import com.tjoeun.vo.Param;
import com.tjoeun.vo.ReportVO;

public interface ReportDAO {

	void report(Param param);

	int reportCount(ReportVO reportVO);

}
