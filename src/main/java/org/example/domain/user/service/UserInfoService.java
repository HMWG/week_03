package org.example.domain.user.service;

import org.example.domain.delivery.DeliveryAddress;
import org.example.domain.user.User;
import org.example.domain.user.repository.UserRepositoryImpl;

import java.util.Optional;

public class UserInfoService {

    public static void main(String[] args) {
        UserInfoService service = new UserInfoService(new UserRepositoryImpl());
        System.out.println(service.getMyUserInfo(2));
        Optional<User> byId = new UserRepositoryImpl().findById(2L);

        service.editPassword(byId.get(), "1234", "5678");
        service.deleteUser(2);
    }
    private UserRepository userRepository;

    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getMyUserInfo(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.isPresent() ? user.toString() : "데이터베이스 오류";
    }

    /**
     * 사용자에게 비밀번호를 입력받아서 그 번호와 원래 내 비밀번호가 일치하면 새로운 비밀번호로 변경
     * @param user
     * @param cur_password
     * @param new_password
     */
    public void editPassword(User user, String cur_password, String new_password) {

        if(cur_password.equals(user.getPassword())) {
            user.setPassword(new_password);
            userRepository.update(user);
        }
    }

    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

}
