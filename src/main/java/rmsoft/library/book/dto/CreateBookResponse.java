package rmsoft.library.book.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import rmsoft.library.book.entity.Book;


@Data
@Builder
@AllArgsConstructor
public class CreateBookResponse {

    private Long bookId;
    private String title;
    private boolean isBorrow;

    //==생성 메서드==//
    public static CreateBookResponse create(Book book) {
        return CreateBookResponse.builder()
                .bookId(book.getId())
                .title(book.getTitle())
                .isBorrow(book.isBorrow())
                .build();
    }

}
