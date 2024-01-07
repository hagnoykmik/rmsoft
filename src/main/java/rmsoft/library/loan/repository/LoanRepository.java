package rmsoft.library.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rmsoft.library.book.entity.Book;
import rmsoft.library.loan.entity.Loan;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    // null이면 아직 반납안한상태(같은 사람이 같은 책을 여러번 빌려도 null인 필드가 제일 최신 정보)
    Optional<Loan> findByBookIdAndUserIdAndReturnDateIsNull(Long bookId, Long userId); // bookId로 loan 찾기

    Optional<Loan> findByUserIdAndBookId(Long userId, Long bookId);

    @Query("SELECT l FROM Loan l JOIN FETCH l.book WHERE l.book.id = :bookId")
    List<Loan> findLoansByBookId(@Param("bookId") Long bookId);  // bookId로 loan 목록 찾기
}
