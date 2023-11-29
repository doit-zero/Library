package doit.librarym.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum BookErrorCode {

    // 4.x.x
    EXIST_LOAN("대출 된 책입니다.", HttpStatus.BAD_REQUEST.value());

    private final String description;
    private final Integer status;
}