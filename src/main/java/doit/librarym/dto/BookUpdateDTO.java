package doit.librarym.dto;

import lombok.Getter;

@Getter
public class BookUpdateDTO {

    private Long bookId;

    private String bookName;

    private String publisher;
}
