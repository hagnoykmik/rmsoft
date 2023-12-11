package rmsoft.library.loan.dto;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class UpdateLoanRequest {
    @NotNull
    private Long userId;

    @NotNull
    private Long bookId;
}
