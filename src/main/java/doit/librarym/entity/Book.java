package doit.librarym.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Book {

    @Id @GeneratedValue
    @Column(name = "book_idx")
    private Long bookIdx;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    private User userIdx;

    @Column(name = "book_name")
    private String bookName;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "registration_date")
    private LocalDateTime registrationDate;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "loan_status")
    private Boolean loanStatus;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Builder

    public Book(User userIdx, String bookName, String publisher, LocalDateTime registrationDate, LocalDateTime updatedAt, Boolean loanStatus, Boolean isDeleted) {
        this.userIdx = userIdx;
        this.bookName = bookName;
        this.publisher = publisher;
        this.registrationDate = registrationDate;
        this.updatedAt = updatedAt;
        this.loanStatus = loanStatus;
        this.isDeleted = isDeleted;
    }

    public Book() {

    }
}
