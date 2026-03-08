package com.meerthika.service;

import com.meerthika.modal.User;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    User createUser(User user);
    List<User> getAllUsers();
    User getUserById(Long id) throws Exception;
    User updateUser(User user, Long id) throws Exception;
    void deleteUserById(Long id) throws Exception;

}
