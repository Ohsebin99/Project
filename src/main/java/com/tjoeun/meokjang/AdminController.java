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


@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private MemberVO mo;
	@Autowired
	private MemberList memberList;
	@Autowired
	NaverLoginBO naverLoginBO;
	private String apiResult;


	@RequestMapping("/admin")
	public String admin(HttpServletRequest request, Model model, MemberVO memberVO, HttpServletResponse response) throws IOException {
		logger.info("AdminController의 adminLogin()");
		
		return "admin/adminPage";
	}
	
	@RequestMapping("/userPage")
	public String userPage(HttpServletRequest request, Model model, MemberVO memberVO, HttpServletResponse response) throws IOException {
		logger.info("AdminController의 userPage()");
		MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
		
		memberList.setList(mapper.selectMembers());
		logger.info("{} line1266", memberList);
		
		request.setAttribute("memberList", memberList);
		return "admin/userPage";
	}
	
	@RequestMapping("/userUpdate")
	public String userUpdate(HttpServletRequest request, Model model, MemberVO memberVO, HttpServletResponse response) throws IOException {
		logger.info("AdminController의 userUpdate()");
		
		return "";
	}
	
	
}
