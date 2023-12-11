package rmsoft.library.loan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmsoft.library.loan.dto.CreateLoanRequest;
import rmsoft.library.loan.dto.CreateLoanResponse;
import rmsoft.library.loan.service.LoanService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    /**
     * 대출 처리
     */
    @PostMapping
    public ResponseEntity<CreateLoanResponse> createLoan(@RequestBody CreateLoanRequest request) {
        CreateLoanResponse loan = loanService.createRoan(request);
        return ResponseEntity.status(201).body(loan);
    }
}
