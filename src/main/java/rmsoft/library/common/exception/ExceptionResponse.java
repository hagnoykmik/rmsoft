package rmsoft.library.common.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ExceptionResponse {
    private final LocalDateTime timestamp;  // 현재 시간
    private final HttpStatus status;        // 에러코드
    private final String message;           // 에러메세지

    public static ExceptionResponse create(CustomException e) {

        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(e.getErrorCode().getStatus())
                .message(e.getErrorCode().getMessage())
                .build();
    }
}
