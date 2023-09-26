package com.tjoeun.meokjang;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tjoeun.api.NaverLoginBO;



@Controller
public class PageController {
	
	@Autowired
	private NaverLoginBO naverLoginBO;
	
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
//	Membercontroller
	@RequestMapping("/loginPage")
	public String loginPage(HttpServletRequest request, Model model, HttpServletResponse response, HttpSession session) {
		logger.info("PageController의 loginPage()");
		String naverAuthUrl = naverLoginBO.getAuthorizationUrl(session);
		model.addAttribute("url", naverAuthUrl);
		return "login";
	}
	
	@RequestMapping("/memberPage")
	public String memberPage() {
		logger.info("PageController의 memberPage()");
		return "member";
	}
	
	@RequestMapping("/test")
	public String test() {
		logger.info("PageController의 test()");
		return "test";
	}
	
	@RequestMapping("/idSerchPage")
	public String idSerchPage() {
		logger.info("PageController의 loginPage()");
		return "idSerch";
	}
	
	@RequestMapping("/pwSerchPage")
	public String pwSerchPage() {
		logger.info("PageController의 passwordSerchPage()");
		return "pwSerch";
	}
	
	@RequestMapping("/myProfileChk")
	public String myProfileChk() {
		logger.info("PageController의 myProfile()");
		return "myProfileChk";
	}
	
	@RequestMapping("/myProfilePage")
	public String myProfile() {
		logger.info("PageController의 myProfile()");
		return "myProfile";
	}
	
	@RequestMapping("/pwdChangePage")
	public String passwordChange() {
		logger.info("PageController의 passwordChange()");
		return "pwChange";
	}
	
//	Partycontroller
	@RequestMapping("/partyInsertPage")
	public String partyInsertPage() {
		logger.info("PageController의 partyInsertPage()");
		return "partyInsert";
	}
}
