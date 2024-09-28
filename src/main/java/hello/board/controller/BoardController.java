package hello.board.controller;

import hello.board.domain.Board;
import hello.board.security.SecurityUser;
import hello.board.service.BoardServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;

    @RequestMapping("/getBoardList")
    public String getBoardList(Model model, Board board) {

        Page<Board> boardList = boardService.getBoardList(board);
        model.addAttribute("boardList", boardList);
        return "board/getBoardList";
    }

    @GetMapping("/getBoard")
    public String getBoard(Board board, Model model) {
        model.addAttribute("board", boardService.getBoard(board));
        return "board/getBoard";
    }

    @GetMapping("/insertBoard")
    public String insertBoardView() {
        return "board/insertBoard";
    }

    @PostMapping("/insertBoard")
    public String insertBoard(Board board,
                              @AuthenticationPrincipal SecurityUser principal) {
        board.setMember(principal.getMember());
        boardService.insertBoard(board);
        return "redirect:/board/getBoardList";
    }

    @PostMapping("/updateBoard")
    public String updateBoard(Board board) {
        boardService.updateBoard(board);
        return "redirect:/board/getBoardList";
    }

    @PostMapping("/deleteBoard")
    public String deleteBoard(Board board) {
        boardService.deleteBoard(board);
        return "redirect:/board/getBoardList";
    }
}
