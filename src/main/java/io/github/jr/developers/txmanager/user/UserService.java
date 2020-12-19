package io.github.jr.developers.txmanager.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


//    @Transactional
    public User create(User user) {
        return userRepository.save(user);
//        throw new RuntimeException();
    }
}
