package rmsoft.library.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmsoft.library.book.dto.CreateBookRequest;
import rmsoft.library.book.dto.CreateBookResponse;
import rmsoft.library.book.entity.Book;
import rmsoft.library.book.repository.BookRepository;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Override
    @Transactional
    public CreateBookResponse createBook(CreateBookRequest request) {
        // db에 저장
        Book book = Book.create(request);
        bookRepository.save(book);

        // 응답 dto로 반환
        CreateBookResponse response = CreateBookResponse.create(book);
        return response;

    }
}
