package kh.spring.interfaces;

import java.util.List;

import org.springframework.stereotype.Component;

import kh.spring.dto.BoardDTO;

@Component
public interface IBoardService {
	public List<BoardDTO> getBoardData();
}
