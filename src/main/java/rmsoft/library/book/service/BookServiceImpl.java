package rmsoft.library.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rmsoft.library.book.dto.CreateBookRequest;
import rmsoft.library.book.dto.CreateBookResponse;
import rmsoft.library.book.dto.UpdateBookRequest;
import rmsoft.library.book.dto.UpdateBookResponse;
import rmsoft.library.book.entity.Book;
import rmsoft.library.book.repository.BookRepository;
import rmsoft.library.common.exception.CustomException;
import rmsoft.library.common.exception.ErrorCode;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    /**
     * 도서 생성
     */
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

    /**
     * 도서 수정
     */
    @Override
    @Transactional
    public UpdateBookResponse updateBook(UpdateBookRequest request, Long bookId) {
        // bookId로 책 가져오기
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_FOUND_BOOK)
        );

        // db에 정보 수정
        Book updateBook = book.updateBook(request.getTitle(), request.isBorrow());  // -> 변경감지해서 db 변경

        // 응답 dto로 반환
        UpdateBookResponse response = UpdateBookResponse.create(updateBook);
        return response;
    }
}
