package com.tjoeun.meokjang;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjoeun.dao.MemberDAO;
import com.tjoeun.dao.PartyDAO;
import com.tjoeun.dao.RepleDAO;
import com.tjoeun.vo.MemberVO;
import com.tjoeun.api.NaverSMTP;
import com.tjoeun.vo.PartyVO;
import com.tjoeun.vo.RepleList;
import com.tjoeun.vo.RepleVO;

@Controller
public class RepleController {

	private static final Logger logger = LoggerFactory.getLogger(RepleController.class);

	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private RepleList repleList;

	@Autowired
	private RepleVO ro;

	@RequestMapping("/repleInsert")
	public String repleInsert(HttpServletRequest request, Model model, RepleVO repleVO) {
		logger.info("RepleController의 repleInsert()");
		RepleDAO mapper = sqlSession.getMapper(RepleDAO.class);
		logger.info("{} line31", repleVO);
		
		// 세션에 저장된 회원정보
		HttpSession session = request.getSession();
		MemberVO mo = (MemberVO) session.getAttribute("mo");
		repleVO.setRepleId(mo.getId());
		repleVO.setRepleNickName(mo.getNickName());
		repleVO.setRepleGender(mo.getGender());
		repleVO.setRepleAge(mo.getAge());
		repleVO.setReplePhoto(mo.getPhoto());
		logger.info("{} line59", repleVO);
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		logger.info("{} line35", currentPage);
		mapper.repleInsert(repleVO);

		return "redirect:selectByIdx?idx=" + repleVO.getOriginIdx() + "&currentPage=" + currentPage + "&job=reple";
	}

	@RequestMapping("/joinOK")
	public String joinOK(HttpServletResponse response, HttpServletRequest request, Model model, RepleVO repleVO)
			throws IOException {
		logger.info("RepleController의 joinOK()");
		RepleDAO mapper = sqlSession.getMapper(RepleDAO.class);
		PartyDAO PartyMapper = sqlSession.getMapper(PartyDAO.class);
		MemberDAO mapperMem = sqlSession.getMapper(MemberDAO.class);
		System.out.println(repleVO);

		// 메일 보내기
		String from = "ajrwkd1@naver.com";
		RepleVO reple = mapper.selectRepleId(repleVO.getRepleIdx());
		// 수정해
		PartyVO partyVO = PartyMapper.selectByIdx(reple.getOriginIdx());
		logger.info("{} line66", partyVO);
		String to = mapperMem.selectEmail(reple.getRepleId());
		String subject = "신청하신 파티 참여가 승인되었습니다.";
		SimpleDateFormat SDF = new SimpleDateFormat("yyyy년 MM월 dd일");
		String repledate = SDF.format(partyVO.getMealDate());
		String content = reple.getRepleId() + "님 신청하신 " + repledate + " 파티 참여가 승인되었습니다.";
//		System.out.println(content);

		// 폼값(이메일 내용) 저장
		Map<String, String> emailInfo = new HashMap<String, String>();
		emailInfo.put("from", from); // 보내는 사람
		emailInfo.put("to", to); // 받는 사람
		emailInfo.put("subject", subject); // 제목
		emailInfo.put("content", content);
		emailInfo.put("format", "text/plain;charset=UTF-8");
		System.out.println(emailInfo);

		try {
			NaverSMTP smtpServer = new NaverSMTP(); // 메일 전송 클래스 생성
			smtpServer.emailSending(emailInfo); // 전송
			System.out.print("이메일 전송 성공");
		} catch (Exception e) {
			System.out.print("이메일 전송 실패");
			e.printStackTrace();
		}

		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		mapper.updateJoin(repleVO.getRepleIdx());
		out.println("<script>");
		out.println("alert('승인 완료!!!')");
		out.println("location.href='selectByIdx?idx=" + idx + "&currentPage=" + currentPage + "&job=reple'");
		out.println("</script>");
		out.flush();

		return "";
	}

	@RequestMapping("/joinNO")
	public String joinNO(HttpServletResponse response, HttpServletRequest request, Model model, RepleVO repleVO)
			throws IOException {
		logger.info("RepleController의 joinNO()");
		RepleDAO mapper = sqlSession.getMapper(RepleDAO.class);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int idx = Integer.parseInt(request.getParameter("idx"));

		mapper.deleteJoin(repleVO.getRepleIdx());
		out.println("<script>");
		out.println("alert('거절 완료!!!')");
		out.println("location.href='selectByIdx?idx=" + idx + "&currentPage=" + currentPage + "&job=reple'");
		out.println("</script>");
		out.flush();

		return "";
	}

	@RequestMapping("/joinCancel")
	public String joinCancel(HttpServletResponse response, HttpServletRequest request, Model model, RepleVO repleVO) throws IOException {
		logger.info("RepleController의 joinCancel()");
		RepleDAO mapper = sqlSession.getMapper(RepleDAO.class);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int idx = Integer.parseInt(request.getParameter("idx"));
		logger.info("{} line142", idx);
		logger.info("{} line142", repleVO);

		mapper.deleteJoin(repleVO.getRepleIdx());
		out.println("<script>");
		out.println("alert('취소 완료!!!')");
		out.println("location.href='selectByIdx?idx=" + idx + "&currentPage=" + currentPage + "&job=reple'");
		out.println("</script>");
		out.flush();

		return "";
	}

	@RequestMapping(value = "/repleServlet", method = RequestMethod.POST)
	@ResponseBody
	public String memberServlet(HttpServletRequest request, Model model, PartyVO partyVO) {
		logger.info("MemberController의 memberServlet()");
		RepleDAO mapper = sqlSession.getMapper(RepleDAO.class);
		logger.info("{} line134", partyVO);

		// 세션에 저장된 회원정보
		HttpSession session = request.getSession();
		MemberVO mo = (MemberVO) session.getAttribute("mo");
		mo.getId();
		ro.setOriginIdx(partyVO.getIdx());
		ro.setRepleId(mo.getId());
		int result = mapper.selectById_Idx(ro);

		// 1있다. 0 없다.
		logger.info("{} line140", result);

		return String.valueOf(result);
	}
}
