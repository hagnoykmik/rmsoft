package rmsoft.library.loan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rmsoft.library.loan.dto.*;
import rmsoft.library.loan.service.LoanService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;

    /**
     * 대출이력조회
     */
    @GetMapping("/{bookId}")
    public ResponseEntity<FindLoansByBookIdResponse> findLoansByBookId(@PathVariable("bookId") Long bookId) {
        FindLoansByBookIdResponse loans = loanService.findLoansByBookId(bookId);
        return ResponseEntity.status(200).body(loans);
    }

    /**
     * 대출 처리
     */
    @PostMapping
    public ResponseEntity<CreateLoanResponse> createLoan(@RequestBody @Valid CreateLoanRequest request) {
        CreateLoanResponse loan = loanService.createLoan(request);
        return ResponseEntity.status(201).body(loan);
    }

    /**
     * 반납 처리
     */
    @PatchMapping("/return")
    public ResponseEntity<UpdateLoanResponse> updateLoan(@RequestBody @Valid UpdateLoanRequest request) {
        UpdateLoanResponse loan = loanService.updateLoan(request);
        return ResponseEntity.status(200).body(loan);

    }

}
