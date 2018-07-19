package kh.spring.interfaces;

import java.util.List;

import kh.spring.dto.BoardDTO;

public interface IBoardService {
	public List<BoardDTO> getBoardData();
}
