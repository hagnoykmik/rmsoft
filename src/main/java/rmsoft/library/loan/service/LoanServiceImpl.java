package rmsoft.library.loan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmsoft.library.book.entity.Book;
import rmsoft.library.book.repository.BookRepository;
import rmsoft.library.common.exception.CustomException;
import rmsoft.library.common.exception.ErrorCode;
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

    /**
     * 대출 이력 조회
     */
    @Override
    public List<FindLoanInstance> findLoansByBookId(Long bookId) {
        // 도서 id로 도서 대출이력 찾기
        List<Loan> loanList = loanRepository.findLoansByBookId(bookId);

        // 찾은 도서의 대출이력이 없을 때 예외 발생
        if (loanList.isEmpty()) {
            throw new CustomException(ErrorCode.NOT_FOUND_LOAN);
        }

        // 응답 dto 리스트에 넣어주기
        List<FindLoanInstance> loans = new ArrayList<>();

        for (Loan loan : loanList) {
            loans.add(FindLoanInstance.create(loan));
        }

        return loans;
    }

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
                        () -> new CustomException(ErrorCode.NOT_FOUND_USER)
                );

        // bookId로 book 찾기
        Long bookId = request.getBookId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_BOOK)
                );

        // book 상태 체크
        if (book.isBorrow()) {
            throw new CustomException(ErrorCode.ALREADY_BORROWED_BOOK);
        }
        // 빌릴 수 있으면 대출
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
                        () -> new CustomException(ErrorCode.NOT_FOUND_USER)
                );

        // bookId로 book 찾기
        Long bookId = request.getBookId();
        Book book = bookRepository.findById(bookId)
                .orElseThrow(
                        () -> new CustomException(ErrorCode.NOT_FOUND_BOOK)
                );

        // book 상태 업데이트
        Book borrowedBook = book.updateIsBorrow();

        // 반납일자 추가
        // bookId로 대출 테이블에서 대출 찾기
        Loan loan = loanRepository.findByBookIdAndUserIdAndReturnDateIsNull(bookId, userId)
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
