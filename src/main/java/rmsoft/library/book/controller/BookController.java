package rmsoft.library.book.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rmsoft.library.book.dto.CreateBookRequest;
import rmsoft.library.book.dto.CreateBookResponse;
import rmsoft.library.book.service.BookService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    /**
     * 도서 생성
     */
    @PostMapping
    public ResponseEntity<CreateBookResponse> createBook(@RequestBody @Validated CreateBookRequest request) {
        CreateBookResponse book = bookService.createBook(request);
        return ResponseEntity.status(201).body(book);

    }

}
