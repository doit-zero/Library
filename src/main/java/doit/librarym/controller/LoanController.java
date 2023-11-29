package doit.librarym.controller;

import doit.librarym.dto.BookLoanDTO;
import doit.librarym.entity.User;
import doit.librarym.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/loan")
public class LoanController {

    private final LoanService loanService;

    // 아이디를 입렵 후 도서에 대한 대출처리
    @PostMapping
    public Map<String,Object> loanBook(@RequestBody BookLoanDTO bookLoanDTO,
                                       @AuthenticationPrincipal User user){
        return loanService.loanBook(bookLoanDTO,user);
    }

    // 도서 반납 처리
    @PostMapping("/return")
    public Map<String,Object> returnBook(@RequestParam(value = "bookId") Long bookId,
                                         @AuthenticationPrincipal User user) {
        return loanService.returnBook(bookId,user);
    }

    // 대출 이력 확인
    @GetMapping("/history")
    public List<Map<String,Object>> getLoanHistory(@RequestParam Long bookId) {
        return loanService.getLoanHistory(bookId);
    }

}
