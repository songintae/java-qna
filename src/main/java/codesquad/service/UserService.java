package codesquad.service;

import codesquad.domain.User;
import codesquad.domain.UserRepository;
import codesquad.exception.UserLoginFailException;
import codesquad.exception.UserUpdateFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).get();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public void update(Long id, User sessionUser, User updateUser){
        User user = userRepository.findById(id).get();

        if(!sessionUser.isSameUser(user))
            throw new UserUpdateFailException();
        user.updateUser(updateUser);
        userRepository.save(user);
    }

    public User findByUserId(String userId) {
        return userRepository.findByUserId(userId).get();
    }

    public User login(String userId, String password){
        User loginUser = findByUserId(userId);
        if(!loginUser.isSamePassword(password))
            throw new UserLoginFailException();
        return loginUser;

    }
}
