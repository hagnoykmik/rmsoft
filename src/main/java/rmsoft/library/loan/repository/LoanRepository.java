package rmsoft.library.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmsoft.library.loan.entity.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
}
