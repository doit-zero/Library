package doit.librarym.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class LoanHistory {

    @Id @GeneratedValue
    @Column(name = "loan_idx")
    private Long loanIdx;

    @ManyToOne
    @JoinColumn(name = "book_idx")
    private Book bookIdx;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User userIdx;

    @Column(name = "loan_date")
    private LocalDateTime loanDate;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "loan_user_id")
    private String loanUserId;

    @Builder
    public LoanHistory(Book bookIdx, User userIdx, LocalDateTime loanDate, LocalDateTime returnDate, String loanUserId) {
        this.bookIdx = bookIdx;
        this.userIdx = userIdx;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.loanUserId = loanUserId;
    }

    public LoanHistory() {

    }
}
