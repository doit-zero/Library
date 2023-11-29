package doit.librarym.repository;

import doit.librarym.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Book findByBookIdx(Long bookId);

    Optional<Book> findBookLoanStatusByBookIdx(Long bookId);
}
