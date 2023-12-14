package rmsoft.library.book.entity;

import lombok.*;
import rmsoft.library.book.dto.CreateBookRequest;
import rmsoft.library.book.dto.UpdateBookRequest;
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
                .isBorrow(false)
                .build();
    }

    /**
     * 도서 정보 수정
     */
    public Book updateBook(String title, boolean isBorrow) {
        this.title = title;
        this.isBorrow = isBorrow;

        return this;
    }

    /**
     * 도서 대출 여부 수정
     */
    public Book updateIsBorrow() {
        if (this.isBorrow) {
            this.isBorrow = false;
        } else {
            this.isBorrow = true;
        }

        return this;
    }

}
