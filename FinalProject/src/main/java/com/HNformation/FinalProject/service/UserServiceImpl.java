package com.HNformation.FinalProject.service;

import com.HNformation.FinalProject.entity.User;
import com.HNformation.FinalProject.exception.IdNotAllowedException;
import com.HNformation.FinalProject.exception.NoChangesDetectedException;
import com.HNformation.FinalProject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not attributed to any User"));
    }

    @Override
    public void saveNewUser(User user) {
        if (user.getUserId() != null) {
            throw new IdNotAllowedException("Id must not be given, it is automatically attributed");
        }
        userRepository.save(user);
    }

    @Override
    public void saveUserModifications(User user) {
        User savedUser = userRepository.findById(user.getUserId()).orElseThrow(() -> new EntityNotFoundException("Id not attributed to any User"));
        System.out.println(savedUser);
        System.out.println(user);
        if (user.equals(savedUser)) {
            throw new NoChangesDetectedException("No changes detected");
        }
        user.setUserId(savedUser.getUserId());
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not attributed to any User"));
        userRepository.delete(user);
    }


}
