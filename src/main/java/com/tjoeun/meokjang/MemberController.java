package com.tjoeun.meokjang;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.tjoeun.api.NaverLoginBO;
import com.tjoeun.dao.MemberDAO;
import com.tjoeun.vo.MemberList;
import com.tjoeun.vo.MemberVO;
import com.tjoeun.vo.PartyList;


@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private MemberVO mo;
	
	@Autowired
	NaverLoginBO naverLoginBO;
	private String apiResult;


	@RequestMapping("/login")
	public String login(HttpServletRequest request, Model model, MemberVO memberVO, HttpServletResponse response) throws IOException {
		logger.info("MemberController의 login()");
		HttpSession session = request.getSession();
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
		logger.info("{} line47", memberVO);
//		아이디 조회
		mo = mapper.selectByID(memberVO);
		logger.info("{} line50", mo);
		out.println("<script>");
		if (mo == null) {
			out.println("alert('정보가 일치하지 않습니다.')");
			out.println("location.href='loginPage'");
			out.println("</script>");
		} else if (memberVO.getPassword().equals(mo.getPassword())) {
			session.setAttribute("mo", mo);
			session.setAttribute("loginType", "normal");
			logger.info("{} line50", mo);
//			model.addAttribute("memberVO", mo);
			out.println("alert('" + mo.getNickName() + "님 반갑습니다.')");
			out.println("location.href='list'");
			out.println("</script>");
		} else {
			out.println("alert('정보가 일치하지 않습니다.')");
			out.println("location.href='loginPage'");
			out.println("</script>");
		}
		out.flush();
		return"home";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, Model model) {
		logger.info("MemberController의 logout()");
		
		HttpSession session = request.getSession();
		session.removeAttribute("mo");
		return "redirect:list";
	}
	
	@RequestMapping("/idSerch")
	public String idSerch(HttpServletRequest request, Model model, MemberVO memberVO, HttpServletResponse response) throws IOException {
		logger.info("MemberController의 idSerch()");
		logger.info("{} line97", memberVO);
		MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
//		logger.info("{} line93", mo);
		mo = mapper.selectByName(memberVO); // 오류
		logger.info("{} line95", mo);
		if(mo != null) {
			logger.info("{} line104", mo);
			out.println("<script>");
			if (mo != null) {
				String content = mo.getName() + "님의 아이디는 " + mo.getId() + " 입니다.";
				out.println("alert('"+ content + "')");
			} else {
				out.println("alert('정보가 일치하지 않습니다.')");
			}
			out.println("location.href='loginPage'");
			out.println("</script>");
			out.flush();
		}
		return "login";
	}
	@RequestMapping("/pwSerch")
	public String pwSerch(HttpServletRequest request, Model model, MemberVO memberVO, HttpServletResponse response) throws IOException {
		logger.info("MemberController의 pwSerch()");
		logger.info("{} line116", memberVO);
		MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		mo = mapper.passwordSerch(memberVO);
		logger.info("{} line120", mo);
		
		out.println("<script>");
		if (mo != null) {
			String content = mo.getName() + "님의 비밀번호는 " + mo.getPassword() + " 입니다.";
			out.println("alert('"+ content + "')");
		} else {
			out.println("alert('정보가 일치하지 않습니다.')");
		}
		out.println("location.href='loginPage'");
		out.println("</script>");
		out.flush();
		return "login";
	}
	@RequestMapping("/member")
	public String member(MultipartHttpServletRequest request, Model model, MemberVO memberVO, HttpServletResponse response) throws IOException {
		logger.info("MemberController의 member()");
		MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		logger.info("{} line49", memberVO);
		String rootUplordDir = "C:" + File.separator + "upload" + File.separator + "memberphoto"; // C:\Upload\memberphoto
		logger.info("uploadDirectory: {}", rootUplordDir);
		
		Iterator<String> iterator = request.getFileNames();
	    MultipartFile multipartFile = null;
	    String uploadFilename = iterator.next();
	    multipartFile = request.getFile(uploadFilename);
	    logger.info("aaa: {}", multipartFile);
//	    logger.info("uploadFilename: {}", uploadFilename);
	    String originalName = multipartFile.getOriginalFilename();
	    logger.info("originalName: {}", originalName);
	    
	    String photo = uploadFile(originalName);
	    if(originalName != null && originalName.length() != 0) {
	    	try {
	    		multipartFile.transferTo(new File(rootUplordDir + File.separator + photo));
	        } catch (Exception e) {}
	    } else {
	    	photo = "default.jpg";
	    }
	    
	    memberVO.setPhoto(photo);
		mapper.memberInsert(memberVO);
		
		out.println("<script>");
		out.println("alert('회원 가입을 축하드립니다~!')");
		out.println("location.href='loginPage'");
		out.println("</script>");
		out.flush();
		return "login";
	}
	@RequestMapping("/pwdChange")
	public String pwdChange(HttpServletRequest request, Model model, MemberVO memberVO, HttpServletResponse response) throws IOException {
		logger.info("MemberController의 pwdChange()");
		MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		logger.info("{} line153", memberVO);
		session.getAttribute("mo");
		memberVO.setId(mo.getId());
		logger.info("{} line156", memberVO);
		
		mapper.passwordChange(memberVO);
		
		out.println("<script>");
		out.println("alert('비밀번호 변경 완료!')");
		out.println("location.href='list'");
		out.println("</script>");
		out.flush();
		
		return "";
	}
	
//	네이버 로그인 성공시 callback 호출 메소드
	@RequestMapping("/callback")
	public String callback(Model model, HttpSession session,
	        @RequestParam String code, @RequestParam String state, MemberVO memberVO, ServletResponse response) throws IOException, ParseException {
		logger.info("MemberController의 callback() 메소드 실행");
		response.setContentType("text/html; charset=UTF-8");
		// String code = request.getParameter("code");
		// String state = request.getParameter("state");
		// logger.info("code: {}", code);
		// logger.info("state: {}", state);
		OAuth2AccessToken oauthToken = naverLoginBO.getAccessToken(session, code, state);
		
		// 로그인 사용자 정보를 얻어온다.
		apiResult = naverLoginBO.getUserProfile(oauthToken);
		logger.info("apiResult: {}", apiResult);
		
		// String 형식인 로그인 사용자 정보를 json 형태로 바꾼다.
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(apiResult);
		JSONObject jsonObj = (JSONObject) obj;
		
	    // top 레벨 단계 데이터 파싱 - response
	    JSONObject response_obj = (JSONObject) jsonObj.get("response");
	    // top 레벨 단계 데이터 파싱 결과에서 name, email 등을 파싱
		// logger.info("response_obj: {}", response_obj);
	    String id = (String) response_obj.get("id"); // 예시, 실제 JSON 구조에 맞춰야 함
	    String nickName = (String) response_obj.get("nickname");
	    String photo = (String) response_obj.get("profile_image");
	    String name = (String) response_obj.get("name");
	    int birthYear = Integer.parseInt((String)response_obj.get("birthyear"));
	    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
	    int age = currentYear - birthYear + 1;
	    String gender = (String) response_obj.get("gender");
	    String email = (String) response_obj.get("email");
	    String telephone = (String) response_obj.get("mobile");
	    
	    // 파싱된 값을 memberVO에 저장한다.
	    memberVO.setId(id);
	    memberVO.setNickName(nickName);
	    memberVO.setPhoto(photo);
	    memberVO.setName(name);
	    memberVO.setAge(age);
	    memberVO.setGender(gender);
	    memberVO.setEmail(email);
	    memberVO.setTelephone(telephone);
		// System.out.println(memberVO);
	    
		PrintWriter out = response.getWriter();
	    // DB에 저장 또는 업데이트
	    MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
	    logger.info("memberDAO: {}", mapper);
//		아이디 조회
		mo = mapper.selectByID(memberVO);
		logger.info("{} line47", mo);
			out.print("<script>");
		if (mo == null) {
			mapper.insertNaverUser(memberVO); // 저장값 없다면 저장
		    
			// DB에서 새로 저장된 사용자 정보를 다시 조회
		    mo = mapper.selectByID(memberVO);
		    // 로그인 정보를 세션에 추가
		    session.setAttribute("mo", mo);
		    session.setAttribute("loginType", "naver");
		    
		    out.println("alert('" + memberVO.getNickName() + "님 가입을 축하합니다.')");
		    out.println("location.href='list'"); 
		} else { // 저장값 있을 때 로그인
			model.addAttribute("result", apiResult);
			session.setAttribute("mo", mo);
			session.setAttribute("loginType", "naver");
	        out.println("alert('" + mo.getNickName() + "님 반갑습니다.')");
	        out.println("location.href='list'"); 
		}
		out.println("</script>");
		out.flush();
		return "home";
	}
	
	@RequestMapping("/naverlogout")
	public String naverlogout(HttpServletRequest request, Model model, HttpSession session) {
		logger.info("MemberController의 callback() 메소드 실행");
	    session.removeAttribute("mo");  // 네이버 사용자 정보를 세션에서 제거
	    session.invalidate();
	    return "redirect:list";
	}
	
	@RequestMapping("/myProfileOK")
	public String myProfileOK(MultipartHttpServletRequest request, Model model, MemberVO memberVO, HttpServletResponse response) throws IOException {
		logger.info("MemberController의 myProfileOK()");
		MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
		HttpSession session = request.getSession();
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		logger.info("{} line175", memberVO);
		String defaultImgCheck = request.getParameter("defaultImgCheck");
		String rootUplordDir = "C:" + File.separator + "upload" + File.separator + "memberphoto"; // C:\Upload\memberphoto
		
		Iterator<String> iterator = request.getFileNames();
		MultipartFile multipartFile = null;
		String uploadFilename = iterator.next();
		multipartFile = request.getFile(uploadFilename);
		String originalName = multipartFile.getOriginalFilename();
		logger.info("originalName: {}", originalName);
		
		// mo에 저장된 랜덤 사진이름 가져오기
//		MemberVO mo = (MemberVO) session.getAttribute("mo");
//		String randomPhotoName = mo.getPhoto().substring(0, 37);
//		String photo = randomPhotoName + originalName;

		String photo = uploadFile(originalName);
		if(originalName != null && originalName.length() != 0) {
	          try {
	             multipartFile.transferTo(new File(rootUplordDir + File.separator + photo));
	           } catch (Exception e) {}
	       } else if(defaultImgCheck.equals("N")) {
	          photo = "default.jpg";
	       } else {
	          photo = mo.getPhoto();
	       }
		memberVO.setPhoto(photo);
        mapper.myProfileUpdate(memberVO);
//		
		session.setAttribute("mo", memberVO);
		out.println("<script>");
		out.println("alert('" + memberVO.getName() + " 님 개인 정보가 수정되었습니다 ~!')");
		out.println("location.href='list'");
		out.println("</script>");
		out.flush();
		return "";
	}
	@RequestMapping(value = "/memberServlet", method = RequestMethod.POST)
	@ResponseBody
	public String memberServlet(HttpServletRequest request, Model model, MemberVO memberVO) {
		logger.info("MemberController의 memberServlet()");
		
		MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
		logger.info("{} line125", memberVO);
		
		int result = mapper.IDCheck(memberVO.getId());
		// 1있다. 0 없다.
		logger.info("{} line129", result);
		
		return String.valueOf(result);
	}
	
	
	// 파일명 랜덤생성 메소드
	private String uploadFile(String originalName) {
		UUID uuid = UUID.randomUUID();
		String savedName =  uuid.toString()+"_"+originalName;
	    return savedName;
	}
	
}
