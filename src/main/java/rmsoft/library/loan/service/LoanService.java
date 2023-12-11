package rmsoft.library.loan.service;

import rmsoft.library.loan.dto.CreateLoanRequest;
import rmsoft.library.loan.dto.CreateLoanResponse;

public interface LoanService {
    CreateLoanResponse createRoan(CreateLoanRequest request);  // 대출 처리
}
