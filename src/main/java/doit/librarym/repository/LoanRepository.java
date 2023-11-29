package doit.librarym.repository;

import doit.librarym.entity.Book;
import doit.librarym.entity.LoanHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<LoanHistory,Long> {

    LoanHistory findByBookIdx(Book book);
    List<LoanHistory> findLoanHistoriesByBookIdx(Book book);
}
