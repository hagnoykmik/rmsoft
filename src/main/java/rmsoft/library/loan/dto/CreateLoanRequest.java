package rmsoft.library.loan.dto;

import com.sun.istack.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateLoanRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;
}
