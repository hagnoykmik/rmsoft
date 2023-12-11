package rmsoft.library.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rmsoft.library.book.entity.Book;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Override
    Optional<Book> findById(Long bookId);  // 책 정보 조회
}
