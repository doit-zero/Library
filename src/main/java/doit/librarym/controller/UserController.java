package doit.librarym.controller;


import doit.librarym.dto.LoginDTO;
import doit.librarym.dto.SignUpDTO;
import doit.librarym.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/sign-up")
    public String signUp(@RequestBody SignUpDTO signUpDTO){
        return userService.signUp(signUpDTO);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }
}
