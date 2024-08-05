package org.example.domain.user.service;

import org.example.domain.user.User;
import org.example.domain.user.exception.EmailNameDuplicationException;
import org.example.domain.user.repository.UserRepositoryImpl;

public class SignUpService {
    private UserRepository userRepository;

    public static void main(String[] args) {
        SignUpService service = new SignUpService(new UserRepositoryImpl());
        String s = service.signUp(new User("dwd", "a@a.com", "1234", "01012121212", false));
        System.out.println(s); // 실패 테스트
    }
    public SignUpService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String signUp(User user) {
        try {
            userRepository.save(user);
            return "회원가입에 성공했습니다.";
        } catch (EmailNameDuplicationException e) {
            return e.getMessage();
        }
    }
}
