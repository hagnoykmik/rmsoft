package rmsoft.library.book.service;

import rmsoft.library.book.dto.CreateBookRequest;
import rmsoft.library.book.dto.CreateBookResponse;
import rmsoft.library.book.dto.UpdateBookRequest;
import rmsoft.library.book.dto.UpdateBookResponse;

public interface BookService {
    CreateBookResponse createBook(CreateBookRequest request);  // 도서 생성
    UpdateBookResponse updateBook(UpdateBookRequest request, Long bookId);  // 도서 수정
}
