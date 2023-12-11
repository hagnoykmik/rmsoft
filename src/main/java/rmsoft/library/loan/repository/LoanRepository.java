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
    Optional<Loan> findByBookIdAndUserIdAndReturnDateIsNull(Long bookId, Long userId); // bookId로 loan 찾기
    @Query("SELECT l FROM Loan l WHERE l.book.id = :bookId")
    List<Loan> findLoansByBookId(@Param("bookId") Long bookId);  // bookId로 loan 목록 찾기
}
