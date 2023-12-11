package rmsoft.library.loan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmsoft.library.book.entity.Book;
import rmsoft.library.book.repository.BookRepository;
import rmsoft.library.loan.dto.*;
import rmsoft.library.loan.entity.Loan;
import rmsoft.library.loan.repository.LoanRepository;
import rmsoft.library.user.entity.User;
import rmsoft.library.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LoanServiceImpl implements LoanService{

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

//    /**
//     * 대출 이력 조회
//     */
//    @Override
//    public List<FindLoanInstance> findLoansByBookId(Long bookId) {
//        List<Loan> loanList = loanRepository.findLoansByBookId(bookId);
//
//        List<FindLoanInstance> loans = new ArrayList<>();
//
//        for (Loan loan : loanList) {
//
//            loans.add(FindLoanInstance.create(loan));
//        }
//
//        return loans;
//    }

    /**
     * 도서 대출
     */
    @Override
    @Transactional
    public CreateLoanResponse createLoan(CreateLoanRequest request) {
        // userId로 user 찾기
        Long userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new IllegalStateException("존재하는 회원이 아닙니다.")
                );

        // bookId로 book 찾기
        Long bookId = request.getBookId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(
                        () -> new IllegalStateException("존재하는 도서가 아닙니다.")
                );

        // book 상태 업데이트
        Book borrowedBook = book.updateIsBorrow();

        // db에 저장
        Loan loan = Loan.create(user, borrowedBook);
        loanRepository.save(loan);

        // 응답 dto로 반환
        CreateLoanResponse response = CreateLoanResponse.create(loan);
        return response;
    }

    /**
     * 도서 반납
     */
    @Override
    @Transactional
    public UpdateLoanResponse updateLoan(UpdateLoanRequest request) {
        // userId로 user 찾기
        Long userId = request.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(
                        () -> new IllegalStateException("존재하는 회원이 아닙니다.")
                );

        // bookId로 book 찾기
        Long bookId = request.getBookId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(
                        () -> new IllegalStateException("존재하는 도서가 아닙니다.")
                );

        // book 상태 업데이트
        Book borrowedBook = book.updateIsBorrow();

        // 반납일자 추가
        // bookId로 대출 테이블에서 대출 찾기
        Loan loan = loanRepository.findByBookId(bookId)
                .orElseThrow(
                        () -> new IllegalStateException("대출 목록에 해당 도서가 없습니다.")
                );

        LocalDateTime returnDate = LocalDateTime.now();
        loan.update(returnDate);

        // 응답 dto로 반환
        UpdateLoanResponse response = UpdateLoanResponse.create(loan);
        return response;
    }




}
