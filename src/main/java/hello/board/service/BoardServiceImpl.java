package hello.board.service;

import hello.board.domain.Board;
import hello.board.persistence.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    public void insertBoard(Board board) {
        boardRepository.save(board);
    }

    public void updateBoard(Board board) {
        Board findBoard = boardRepository.findById(board.getSeq()).get();

        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        boardRepository.save(board);
    }

    public void deleteBoard(Board board) {
        boardRepository.deleteById(board.getSeq());
    }

    public Board getBoard(Board board) {
        return boardRepository.findById(board.getSeq()).get();
    }

    public Page<Board> getBoardList(Board board) {
        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "seq");
        return boardRepository.getBoradList(pageable);
    }
}
