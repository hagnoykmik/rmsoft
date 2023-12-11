package rmsoft.library.loan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmsoft.library.loan.dto.CreateLoanRequest;
import rmsoft.library.loan.dto.CreateLoanResponse;
import rmsoft.library.loan.dto.UpdateLoanRequest;
import rmsoft.library.loan.dto.UpdateLoanResponse;
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
        CreateLoanResponse loan = loanService.createLoan(request);
        return ResponseEntity.status(201).body(loan);
    }

    /**
     * 반납 처리
     */
    @PatchMapping("/return")
    public ResponseEntity<UpdateLoanResponse> updateLoan(@RequestBody UpdateLoanRequest request) {
        UpdateLoanResponse loan = loanService.updateLoan(request);
        return ResponseEntity.status(200).body(loan);

    }

}
