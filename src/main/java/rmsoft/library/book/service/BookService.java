package rmsoft.library.book.service;

import rmsoft.library.book.dto.CreateBookRequest;
import rmsoft.library.book.dto.CreateBookResponse;

public interface BookService {
    CreateBookResponse createBook(CreateBookRequest request);  // 도서 생성
}
