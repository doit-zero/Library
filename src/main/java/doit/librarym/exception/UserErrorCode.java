package doit.librarym.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCode {

    // 4.x.x
    FAIL_TO_LOGIN("아이디 또는 비밀번호가 틀렸습니다.", HttpStatus.BAD_REQUEST.value()),
    NOT_EXIST_USER_ID("존재하지 않는 아이디 입니다.", HttpStatus.BAD_REQUEST.value()),
    EXIST_USER_ID("아이디가 존재 합니다..", HttpStatus.BAD_REQUEST.value());

    private final String description;
    private final Integer status;
}
