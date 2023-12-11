package rmsoft.library.loan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmsoft.library.book.entity.Book;
import rmsoft.library.book.repository.BookRepository;
import rmsoft.library.loan.dto.CreateLoanRequest;
import rmsoft.library.loan.dto.CreateLoanResponse;
import rmsoft.library.loan.entity.Loan;
import rmsoft.library.loan.repository.LoanRepository;
import rmsoft.library.user.entity.User;
import rmsoft.library.user.repository.UserRepository;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class LoanServiceImpl implements LoanService{

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    @Override
    @Transactional
    public CreateLoanResponse createRoan(CreateLoanRequest request) {
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
}
