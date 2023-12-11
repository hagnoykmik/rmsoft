package rmsoft.library.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import rmsoft.library.loan.entity.Loan;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class UpdateLoanResponse {

    private Long loanId;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private Long userId;
    private Long bookId;

    //==생성 메서드==//
    public static UpdateLoanResponse create(Loan loan) {
        return UpdateLoanResponse.builder()
                .loanId(loan.getId())
                .loanDate(loan.getLoanData())
                .returnDate(loan.getReturnDate())
                .userId(loan.getUser().getId())
                .bookId(loan.getBook().getId())
                .build();
    }

}
