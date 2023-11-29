package doit.librarym.service;

import doit.librarym.dto.BookDTO;
import doit.librarym.dto.BookUpdateDTO;
import doit.librarym.entity.Book;
import doit.librarym.entity.User;
import doit.librarym.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;


    @Transactional
    public Map<String,Object> resist(BookDTO bookDTO, User user) {
        Book book = Book.builder()
                .userIdx(user)
                .bookName(bookDTO.getBookName())
                .publisher(bookDTO.getPublisher())
                .registrationDate(LocalDateTime.now())
                .loanStatus(false)
                .isDeleted(false)
                .build();

        bookRepository.save(book);

        Map<String,Object> resistBook = new HashMap<>();
        resistBook.put("BookId", book.getBookIdx());
        resistBook.put("책 이름",book.getBookName());
        resistBook.put("출판사",book.getPublisher());

        return resistBook;
    }


    @Transactional
    public Map<String,Object>  update(BookUpdateDTO bookUpdateDTO, User user) {
        Book book = bookRepository.findByBookIdx(bookUpdateDTO.getBookId());

        book.setBookName(bookUpdateDTO.getBookName());
        book.setPublisher(book.getPublisher());
        book.setUserIdx(user);
        book.setUpdatedAt(LocalDateTime.now());

        bookRepository.save(book);

        Map<String,Object> updatedBook = new HashMap<>();
        updatedBook.put("BookId", book.getBookIdx());
        updatedBook.put("책 이름",book.getBookName());
        updatedBook.put("출판사",book.getPublisher());

        return updatedBook;
    }
}
