package doit.librarym.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "user_idx")
    private Long userIdx;

    @Column(name = "user_id",unique = true, nullable = false)
    private String userId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Builder

    public User(Long userIdx, String userId, String password, LocalDateTime createdAt, Boolean isDeleted) {
        this.userIdx = userIdx;
        this.userId = userId;
        this.password = password;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public User() {

    }

    public Boolean isPasswordMatch(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password,this.password);
    }
}
