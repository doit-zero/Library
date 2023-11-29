package doit.librarym.controller;

import doit.librarym.dto.BookDTO;
import doit.librarym.dto.BookUpdateDTO;
import doit.librarym.entity.User;
import doit.librarym.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("/resist")
    public Map<String,Object> resist(@RequestBody BookDTO bookDTO,
                                     @AuthenticationPrincipal User user){
        return bookService.resist(bookDTO,user);
    }

    @PutMapping("/update")
    public Map<String,Object> update(@RequestBody BookUpdateDTO bookUpdateDTO,
                         @AuthenticationPrincipal User user){
        return bookService.update(bookUpdateDTO,user);
    }

}
