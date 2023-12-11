package rmsoft.library.loan.entity;

import lombok.*;
import rmsoft.library.book.entity.Book;
import rmsoft.library.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Loan")
@Builder
@AllArgsConstructor
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loan_id")
    private Long id;

    @Column(name = "loan_date")
    private LocalDateTime loanData;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    //user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

}
