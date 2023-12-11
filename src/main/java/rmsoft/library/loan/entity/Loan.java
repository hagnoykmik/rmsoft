package rmsoft.library.loan.entity;

import lombok.*;
import rmsoft.library.book.dto.CreateBookRequest;
import rmsoft.library.book.entity.Book;
import rmsoft.library.loan.dto.CreateLoanRequest;
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


    //==생성 메서드==//
    public static Loan create(User user, Book book) {
        return Loan.builder()
                .loanData(LocalDateTime.now())
                .returnDate(null)
                .user(user)
                .book(book)
                .build();
    }

    /**
     * 반납 일자 추가
     */
    public Loan update(LocalDateTime returnDate) {
        this.returnDate = returnDate;

        return this;
    }

}
