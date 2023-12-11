package rmsoft.library.loan.service;

import rmsoft.library.loan.dto.CreateLoanRequest;
import rmsoft.library.loan.dto.CreateLoanResponse;
import rmsoft.library.loan.dto.UpdateLoanRequest;
import rmsoft.library.loan.dto.UpdateLoanResponse;

public interface LoanService {
    CreateLoanResponse createLoan(CreateLoanRequest request);  // 대출 처리
    UpdateLoanResponse updateLoan(UpdateLoanRequest request);  // 반납 처리
}
