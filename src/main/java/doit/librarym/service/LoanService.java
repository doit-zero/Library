package doit.librarym.service;


import doit.librarym.dto.BookLoanDTO;
import doit.librarym.entity.Book;
import doit.librarym.entity.LoanHistory;
import doit.librarym.entity.User;
import doit.librarym.exception.BookErrorCode;
import doit.librarym.exception.BookException;
import doit.librarym.exception.UserErrorCode;
import doit.librarym.exception.UserException;
import doit.librarym.repository.BookRepository;
import doit.librarym.repository.LoanRepository;
import doit.librarym.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@RequiredArgsConstructor
@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public Map<String,Object> loanBook(BookLoanDTO bookLoanDTO, User user) {
        Optional<User> loanUserOptional = userRepository.findByUserId(bookLoanDTO.getLoanUserId());
        loanUserOptional.orElseThrow(() -> new UserException(UserErrorCode.NOT_EXIST_USER_ID));

        Book book = bookRepository.findByBookIdx(bookLoanDTO.getBookId());
        if(book.getLoanStatus()) throw new BookException(BookErrorCode.EXIST_LOAN);
        book.setLoanStatus(true);
        bookRepository.save(book);

        LoanHistory loanHistory = LoanHistory.builder()
                .userIdx(user)
                .loanDate(LocalDateTime.now())
                .bookIdx(book)
                .loanUserId(bookLoanDTO.getLoanUserId())
                .build();
        loanRepository.save(loanHistory);

        Map<String,Object> loanBook = new HashMap<>();
        loanBook.put("bookId", book.getBookIdx());
        loanBook.put("책 이름",book.getBookName());
        loanBook.put("대출 처리",book.getLoanStatus());
        loanBook.put("대출 유저",loanHistory.getLoanUserId());

        return loanBook;
    }

    public Map<String, Object> returnBook(Long bookId, User user) {
        Book book = bookRepository.findByBookIdx(bookId);
        book.setLoanStatus(false);
        bookRepository.save(book);

        LoanHistory loanHistory = loanRepository.findByBookIdx(book);
        loanHistory.setReturnDate(LocalDateTime.now());
        loanHistory.setUserIdx(user);
        loanRepository.save(loanHistory);

        Map<String,Object> returnBook = new HashMap<>();
        returnBook.put("bookId", book.getBookIdx());
        returnBook.put("책 이름",book.getBookName());
        returnBook.put("대출 처리",book.getLoanStatus());

        return returnBook;
    }

    public List<Map<String,Object>> getLoanHistory(Long bookId) {
        Book book = bookRepository.findByBookIdx(bookId);
        List<LoanHistory> loanHistoryList = loanRepository.findLoanHistoriesByBookIdx(book);

        List<Map<String,Object>> resultList = new ArrayList<>();
        for(LoanHistory loanHistory : loanHistoryList){
            Map<String,Object> history = new HashMap<>();
            history.put("대출 일자",loanHistory.getLoanDate());
            history.put("반납 일자",loanHistory.getReturnDate());
            history.put("빌려간 유저Id",loanHistory.getLoanUserId());
            resultList.add(history);
        }

        return resultList;

    }
}
