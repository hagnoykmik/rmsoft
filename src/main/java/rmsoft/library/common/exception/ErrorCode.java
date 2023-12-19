package rmsoft.library.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    //400 BAD_REQUEST
    INVALID_PARAMETER(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),

    //404 NOT_FOUND
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    NOT_FOUND_BOOK(HttpStatus.NOT_FOUND, "존재하지 않는 도서입니다."),
    NOT_FOUND_LOAN(HttpStatus.NOT_FOUND, "대출이력이 존재하지 않습니다."),

    //409 CONFLICT
    ALREADY_BORROWED_BOOK(HttpStatus.CONFLICT, "이미 대출중인 도서입니다."),
    ALREADY_RETURNED_BOOK(HttpStatus.CONFLICT, "이미 반납처리된 도서입니다.");


    private final HttpStatus status;
    private final String message;
}