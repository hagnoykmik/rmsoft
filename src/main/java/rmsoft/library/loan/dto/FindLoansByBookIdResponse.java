package rmsoft.library.loan.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class FindLoansByBookIdResponse {

    private List<FindLoanInstance> loans;

}
