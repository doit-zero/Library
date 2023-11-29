package doit.librarym.service;

import doit.librarym.dto.LoginDTO;
import doit.librarym.dto.SignUpDTO;
import doit.librarym.entity.User;
import doit.librarym.exception.UserErrorCode;
import doit.librarym.exception.UserException;
import doit.librarym.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String signUp(SignUpDTO signUpDTO) {
        if(userRepository.existsByUserId(signUpDTO.getUserId()))
            throw new UserException(UserErrorCode.EXIST_USER_ID);

        User user = User.builder()
                .userId(signUpDTO.getUserId())
                .password(passwordEncoder.encode(signUpDTO.getPassword()))
                .createdAt(LocalDateTime.now())
                .isDeleted(false)
                .build();

        userRepository.save(user);
        return "회원 가입 완료";
    }


    @Transactional
    public String login(LoginDTO loginDTO) {
        Optional<User> userOptional = userRepository.findByUserId(loginDTO.getUserId());
        userOptional.filter(u -> u.isPasswordMatch(passwordEncoder, loginDTO.getPassword()))
                .orElseThrow(() -> new UserException(UserErrorCode.FAIL_TO_LOGIN));

        return "로그인 완료";
    }


}
