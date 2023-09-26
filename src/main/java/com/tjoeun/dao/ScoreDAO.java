package com.tjoeun.dao;

import com.tjoeun.vo.ScoreVO;

public interface ScoreDAO {

	void scoreInsert(ScoreVO scoreVO);

	ScoreVO scoreIDCheck(ScoreVO scoreVO);

	void scoreUpdate(ScoreVO so);


}
