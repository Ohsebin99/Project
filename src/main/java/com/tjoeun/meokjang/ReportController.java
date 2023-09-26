package com.tjoeun.meokjang;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.dao.PartyDAO;
import com.tjoeun.dao.ReportDAO;
import com.tjoeun.vo.MemberVO;
import com.tjoeun.vo.Param;
import com.tjoeun.vo.PartyVO;
import com.tjoeun.vo.RepleList;
import com.tjoeun.vo.RepleVO;

@Controller
public class ReportController {
	
	private static final Logger logger = LoggerFactory.getLogger(ReportController.class);
	
	@Autowired
	private SqlSession sqlSession; 
	
	@Autowired
	private RepleList repleList;
	
	@Autowired
	Param param;
	
	@Autowired
	private RepleVO ro;
	
	@RequestMapping("/report")
	public String partyReport(HttpServletResponse response, HttpServletRequest request, Model model, PartyVO partyVO) throws IOException {
		logger.info("ReportController의 report()");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		PartyDAO partyMapper = sqlSession.getMapper(PartyDAO.class);
		ReportDAO reportMapper = sqlSession.getMapper(ReportDAO.class);
		
		// 회원정보
		HttpSession session = request.getSession();
		MemberVO mo = (MemberVO) session.getAttribute("mo");
		// 현재페이지
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// 신고수 증가
		partyVO.setDeleteReport(partyVO.getDeleteReport());
		partyMapper.partyReport(partyVO);
		
		param.setOriginIdx(partyVO.getIdx());
		param.setReportId(mo.getId());
		logger.info("{} line74", param);
		
		reportMapper.report(param);
		out.println("<script>");
		out.println("alert('신고 완료!!!')");
		out.println("location.href='selectByIdx?idx="+partyVO.getIdx()+"&currentPage="+currentPage+"&job=reple'");
		out.println("</script>");
		out.flush();
		
		return "";
		
	}
}
