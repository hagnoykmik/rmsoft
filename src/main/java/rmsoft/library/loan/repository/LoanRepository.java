package rmsoft.library.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmsoft.library.book.entity.Book;
import rmsoft.library.loan.entity.Loan;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    Optional<Loan> findByBookId(Long bookId); // bookId로 loan 찾기
}
