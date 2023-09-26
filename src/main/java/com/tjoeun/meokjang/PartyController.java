
package com.tjoeun.meokjang;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tjoeun.dao.PartyDAO;
import com.tjoeun.dao.RepleDAO;
import com.tjoeun.dao.ReportDAO;
import com.tjoeun.vo.MemberVO;
import com.tjoeun.vo.Param;
import com.tjoeun.vo.PartyList;
import com.tjoeun.vo.PartyVO;
import com.tjoeun.vo.RepleList;
import com.tjoeun.vo.RepleVO;
import com.tjoeun.vo.ReportVO;

@Controller
public class PartyController {
	
	private static final Logger logger = LoggerFactory.getLogger(PartyController.class);
	
	@Autowired
	private SqlSession sqlSession; 
	@Autowired
	private PartyList partyList;
	@Autowired
	private PartyList partyListSlider;
	@Autowired
	private PartyVO vo;
	@Autowired
	private MemberVO mo;
	@Autowired
	private ReportVO reportVO;
	@Autowired
	private RepleList repleList;
	
	@RequestMapping("/")
	public String home(HttpServletRequest request, Model model) {
		return "redirect:list";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, Param param) {
		logger.info("PartyController의 list()");
		PartyDAO mapper = sqlSession.getMapper(PartyDAO.class);
		
		int pageSize = 8;
		int currentPage = 1;
		try{
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}catch(NumberFormatException e){}
		
		String item = request.getParameter("item");	
		String condition = request.getParameter("condition");
		
		String location = request.getParameter("location");
		String acholchk = request.getParameter("acholchk");
		String partyGender = request.getParameter("partyGender");
		String category = request.getParameter("category");
		
		int minLimitAge = 19;
		try{
			minLimitAge = Integer.parseInt(request.getParameter("minLimitAge"));
		}catch(NumberFormatException e){}
		int maxLimitAge = 80;
		try{
			maxLimitAge = Integer.parseInt(request.getParameter("maxLimitAge"));
		}catch(NumberFormatException e){}	
		
		// 넘어온 검색어가 있으면 카테고리와 검색어를 세션에 저장하고 넘어온 검색어가 없으면 세션에 
		// 저장된 카테고리와 검색어를 읽어온다.
		if (item != null) {
			model.addAttribute("condition", condition);
			item = item.trim().length() == 0 ? "" : item;
			model.addAttribute("item", item);
		} else {
			condition = request.getParameter("condition");
			item = request.getParameter("item");
		}	
		
		
// 브라우저 화면에 표시할 1페이지 분량의 글 목록과 페이징 작업에 사용할 8개의 변수를 초기화 
// 시키는 메소드를 실행한다.
// 검색어가 넘어온 경우 (내용, 이름, 내용+이름)와 넘어오지 않은 경우에 따른 메소드를
// 각각 실행한다.
		param.setItem(item);
		param.setCondition(condition);
		param.setCategory(category);
		param.setLocation(location);
		param.setAcholchk(acholchk);
		param.setPartyGender(partyGender);
		param.setMinLimitAge(minLimitAge);
		param.setMaxLimitAge(maxLimitAge);
		logger.info("{} line121", param);
		
		// 검색어가 있는 경우와 검색어가 없는 경우로 메소드를 구현한다.	
		if (param.getLocation() == null && param.getAcholchk() == null && param.getPartyGender() == null && param.getCategory() == null) {
		// 검색어가 입력되지 않은 경우
			int totalCount = mapper.selectCount();
			partyList.initPartyList(pageSize, totalCount, currentPage);
			HashMap<String, Integer> hmap = new HashMap<String, Integer>();
			hmap.put("startNo", partyList.getStartNo());
			hmap.put("endNo", partyList.getEndNo());
			partyList.setList(mapper.selectList(hmap));
		} else {
			// 검색어가 입력된 경우 (condition 구별을 xml에서 한다.)
			int totalCount = mapper.selectCountMulti(param);
			logger.info("totalCount : {}", totalCount);
			partyList.initPartyList(pageSize, totalCount, currentPage);
			param.setStartNo(partyList.getStartNo());
			param.setEndNo(partyList.getEndNo());
			partyList.setList(mapper.selectListMulti(param));
			logger.info("totalCount : {}", partyList);
		}
		logger.info("totalCount : {}", partyList);
		
		
		partyListSlider.setList(mapper.selectSlider());
	
//		1페이지 분량의 글 목록, 상단에 표시할 글목록, 현재페이지, ("\r\n", "enter")를 request 영역에 저장한다.
		
		model.addAttribute("partyListSlider", partyListSlider);
		model.addAttribute("partyList", partyList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("enter", "\r\n"); 
		model.addAttribute("partyGender", partyGender); 
		model.addAttribute("item", item); 
		model.addAttribute("condition", condition); 
		model.addAttribute("acholchk",acholchk); 
		model.addAttribute("location", location); 
		model.addAttribute("category", category);  
	
		return "list";
	}
	
	@RequestMapping("/mylist")
	public String mylist(HttpServletRequest request, Model model, MemberVO memberVO) {
		logger.info("PartyController의 list()");
		PartyDAO partyMapper = sqlSession.getMapper(PartyDAO.class);
		RepleDAO repleMapper = sqlSession.getMapper(RepleDAO.class);
		logger.info("{} line123", memberVO);
		
		HttpSession session = request.getSession();
		MemberVO original = (MemberVO) session.getAttribute("mo");
		
		logger.info("{} line128", original);
//		생성한 방 리스트
		partyList.setList(partyMapper.createMyList(original));
		logger.info("{} line131", partyList);
		
		ArrayList<PartyVO> joinList  = new ArrayList<PartyVO>();
//		참여한 방 리스트
		repleList.setList(repleMapper.selectByRepleID(original.getId()));
		
		ArrayList<Integer> originIdxList = new ArrayList<Integer>();
		
		for (RepleVO ro : repleList.getList()) {
			originIdxList.add(ro.getOriginIdx());
		}
		
		for(int idx : originIdxList) {
//			수정해야 할 코드 각각 partyVO를 가져와 list에 add 해야한다.
			joinList.add(partyMapper.joinMyList(idx));
		}
		logger.info("{} line150", originIdxList);
		logger.info("{} line151", joinList);
		
		
//		생성한 방 리스트
		request.setAttribute("partyList", partyList);
//		참여한 방 리스트
		request.setAttribute("joinList", joinList);
		
		return "mylist";
	}
	
	@RequestMapping("/partyUpdate")
	public String partyUpdate(HttpServletResponse response, HttpServletRequest request, Model model, PartyVO partyVO) throws IOException {
		logger.info("PartyController의 partyUpdate()");
		PartyDAO mapper = sqlSession.getMapper(PartyDAO.class);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		mapper.partyUpdate(partyVO);
		
		out.println("<script>");
		out.println("alert('수정완료!!!')");
		out.println("location.href='selectByIdx?idx="+partyVO.getIdx()+"&currentPage="+currentPage+"&job=reple'");
		out.println("</script>");
		out.flush();
		
		return "";
		
	}
	
	@RequestMapping("/partyDelete")
	public String partyDelete(HttpServletResponse response, HttpServletRequest request, Model model, PartyVO partyVO) throws IOException {
		logger.info("PartyController의 partyDelete()");
		PartyDAO mapper = sqlSession.getMapper(PartyDAO.class);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		mapper.partyDelete(partyVO);
		
		out.println("<script>");
		out.println("alert('삭제완료!!!')");
		out.println("location.href='selectByIdx?idx="+partyVO.getIdx()+"&currentPage="+currentPage+"&job=redirect:list'");
		out.println("</script>");
		out.flush();
		
		return "";
		
	}
	
	@RequestMapping("/selectByIdx")
	public String selectByIdx(HttpServletResponse response, HttpServletRequest request, Model model) throws IOException {
		logger.info("PartyController의 list()");
		HttpSession session = request.getSession();
		PartyDAO mapper = sqlSession.getMapper(PartyDAO.class);
		ReportDAO ReportMapper = sqlSession.getMapper(ReportDAO.class);
		RepleDAO repleMapper = sqlSession.getMapper(RepleDAO.class); 
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		mo = (MemberVO) session.getAttribute("mo");
		
		String job = request.getParameter("job");

//		로그인이 되어있지 않을 경우
		if (mo == null) {
			out.println("<script>");
			out.println("alert('로그인이 필요한 서비스입니다.');");
			out.println("location.href='loginPage';");
			out.println("</script>");
			out.flush();
		}
	//		listView.jsp 또는 repleInsert.jsp에서 넘어오는 글번호, 돌아갈 페이지 번호, 분기할 페이지 이름을 받는다.


		int currentPage = 1;
		int idx = Integer.parseInt(request.getParameter("idx"));
		try {
		    currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {
		    // 예외 처리
		}
		
		logger.info("{} line218", job);
		
		// 메인글 1건을 얻어오는 메소드를 호출한다.	
		vo = mapper.selectByIdx(idx);
		
		logger.info("{} line245", vo);
		// 메인글 1건의 종속한 reple List를 얻어온다.
		repleList.setList(repleMapper.selectRepleList(idx));
		logger.info("{} line248", repleList);
		
		reportVO.setReportId(mo.getId());
		reportVO.setOriginIdx(idx);
		logger.info("{} line252", reportVO);
		// 메인글 1건의 종속한 report DB 중 회원정보한 일치한 report 조회
		int reportCount = ReportMapper.reportCount(reportVO);
		logger.info("{} line254", reportCount);
		
		// 브라우저에 출력할 메인글이 저장된 객체(vo), 작업 후 돌아갈 페이지 번호, 줄바꿈에 사용할
		request.setAttribute("reportCount", reportCount);
		request.setAttribute("repleList", repleList);
		request.setAttribute("vo", vo);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("enter", "\r\n");
		
		return job;
		
	}
	@RequestMapping("/partyInsert")
	public String partyInsert(MultipartHttpServletRequest request, Model model, PartyVO partyVO) {
		logger.info("컨트롤러의 InsertServlet() 메소드 실행");
		PartyDAO mapper = sqlSession.getMapper(PartyDAO.class);
		
		String rootUplordDir = "C:" + File.separator + "upload" + File.separator + "thumbnail"; // C:\Upload\thumbnail
		logger.info("uploadDirectory: {}", rootUplordDir);
	      
		Iterator<String> iterator = request.getFileNames();
	    MultipartFile multipartFile = null;
	    String uploadFilename = iterator.next();
	    multipartFile = request.getFile(uploadFilename);
//	    logger.info("uploadFilename: {}", uploadFilename);
	    String originalName = multipartFile.getOriginalFilename();
//	    logger.info("originalName: {}", originalName);
	    String photo = uploadFile(originalName);
	    logger.info("photo: {}", photo);
	    if(originalName != null && originalName.length() != 0) {
	    	try {
	    		multipartFile.transferTo(new File(rootUplordDir + File.separator + photo));
	        } catch (Exception e) {}
	    }
	      
	    logger.info("{}", partyVO);
	    String dateObject1 = request.getParameter("dateObject1");
	    String dateObject2 = request.getParameter("dateObject2"); 
	    String combinedDateTimeString1 = dateObject1 + " " + dateObject2;
	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	    Date mealDate = null;
	    try {
	        mealDate = dateTimeFormat.parse(combinedDateTimeString1);
	        System.out.println(mealDate);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    partyVO.setMealDate(mealDate);
	    partyVO.setThumbnail(photo);
	      
//	    logger.info("{}", party VO);
	    
	    mapper.insert(partyVO);
	    
	    return "redirect:list";
	}
	   
	// 파일명 랜덤생성 메소드
	private String uploadFile(String originalName) {
		UUID uuid = UUID.randomUUID();
		String savedName =  uuid.toString()+"_"+originalName;
	    return savedName;
	}
	
	      
}
