
package com.tjoeun.meokjang;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tjoeun.dao.MemberDAO;
import com.tjoeun.dao.PartyDAO;
import com.tjoeun.dao.RepleDAO;
import com.tjoeun.dao.ScoreDAO;
import com.tjoeun.vo.MemberList;
import com.tjoeun.vo.MemberVO;
import com.tjoeun.vo.PartyVO;
import com.tjoeun.vo.RepleVO;
import com.tjoeun.vo.ScoreVO;

@Controller
public class ScoreController {
	
	private static final Logger logger = LoggerFactory.getLogger(ScoreController.class);
	
	@Autowired
	private SqlSession sqlSession; 
	@Autowired
	private PartyVO vo;
	@Autowired
	private MemberVO mo;
	@Autowired
	private ScoreVO so;
	@Autowired
	private MemberList memberList;
	
		
	@RequestMapping("/score")
	public String score(HttpServletRequest request, Model model) {
		// 테스트용 코드 내 아이디
		mo.setId("eodlf177");
		logger.info("ScoreController의 score()");
		HttpSession session = request.getSession();
		
		PartyDAO partyMapper = sqlSession.getMapper(PartyDAO.class);
		RepleDAO repleMapper = sqlSession.getMapper(RepleDAO.class);
		MemberDAO memberMapper = sqlSession.getMapper(MemberDAO.class);
		
		int idx = Integer.parseInt(request.getParameter("idx"));
//		logger.info("idx: {}", idx);
		
		// 해당 방번호의 모임원 리스트 
		ArrayList<RepleVO>list =  repleMapper.selectRepleList(idx);
		logger.info("line61 {}", list);
		
		// 모임장 및 모임원 id 저장할 리스트
		ArrayList<String> repleIdList = new ArrayList<String>();
		
		for(RepleVO repleVO : list) {
			repleIdList.add(repleVO.getRepleId());
		}
		repleIdList.add(list.get(0).getMasterId());
		
		repleIdList.remove(mo.getId());
		logger.info("line61 {}", repleIdList);
		
		// 평가할 대상자의 mo값 가져오기
		ArrayList<MemberVO> memberVOs = new ArrayList<MemberVO>();
		for (String repleId : repleIdList) {
			memberVOs.add(memberMapper.memberList(repleId));
		}
//		평가
		memberList.setList(memberVOs);
		
		// 해당 방정보
		vo = partyMapper.selectByIdx(idx);
		
		logger.info("line81 {}", memberList);
		logger.info("line82 {}", vo);

		model.addAttribute("partyVO", vo);
		model.addAttribute("memberList", memberList);
		return "testRequest";
		
	}
	
	
	@RequestMapping(value = "/scoreServlet", method = RequestMethod.POST)
	@ResponseBody
	public String scoreServlet(HttpServletRequest request, Model model, ScoreVO scoreVO) {
		logger.info("ScoreController의 scoreServlet()");
//		logger.info("line101 {}", scoreVO);
		ScoreDAO mapper = sqlSession.getMapper(ScoreDAO.class);
		
		// 입력한 값의 총합
//		scoreVO.setScoreTotal(scoreVO.getPromise() + scoreVO.getManner() + scoreVO.getClean()); 
//		int scoreTotal = scoreVO.getScoreTotal();
//		scoreVO.setScoreCount(scoreVO.getScoreCount() + 1);

		int scoreTotal = scoreVO.getPromise() + scoreVO.getManner() + scoreVO.getClean();

		logger.info("line101 {}", scoreVO);
		
		so = mapper.scoreIDCheck(scoreVO);
		
		if (so == null) {
			scoreVO.setScoreTotal(scoreTotal);
			mapper.scoreInsert(scoreVO);
		}else {
			so.setClean(so.getClean() + scoreVO.getClean());
			so.setManner(so.getManner() + scoreVO.getManner());
			so.setPromise(so.getPromise() + scoreVO.getPromise());
			so.setScoreTotal(so.getScoreTotal() + scoreTotal);
			mapper.scoreUpdate(so);
		}
		
		
		return"";
//		return String.valueOf(result);
	}
	
}
