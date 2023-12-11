package rmsoft.library.loan.service;

import rmsoft.library.loan.dto.*;

import java.util.List;

public interface LoanService {
    List<FindLoanInstance> findLoansByBookId(Long bookId); // 대출 이력 조회
    CreateLoanResponse createLoan(CreateLoanRequest request);  // 대출 처리
    UpdateLoanResponse updateLoan(UpdateLoanRequest request);  // 반납 처리
}
