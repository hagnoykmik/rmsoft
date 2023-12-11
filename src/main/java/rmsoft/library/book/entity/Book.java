package rmsoft.library.book.entity;

import lombok.*;
import rmsoft.library.book.dto.CreateBookRequest;
import rmsoft.library.loan.entity.Loan;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "Book")
@Builder
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    private String title;

    @Column(name = "is_borrow")
    private boolean isBorrow;

//    대출 엔티티와 일대다관계(일)
    @OneToMany(mappedBy = "book")
    private List<Loan> loans = new ArrayList<>();


    //==생성 메서드==//
    public static Book create(CreateBookRequest request) {
        return Book.builder()
                .title(request.getTitle())
                .isBorrow(request.isBororow())
                .build();
    }

}
