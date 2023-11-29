package doit.librarym.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookException extends RuntimeException {
    private Integer errorCode;
    private String errorMessage;

    public BookException(BookErrorCode bookErrorCode) {
        this.errorCode = bookErrorCode.getStatus();
        this.errorMessage = bookErrorCode.getDescription();
    }
}