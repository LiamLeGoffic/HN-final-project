package com.HNformation.FinalProject.service;

import com.HNformation.FinalProject.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findUserById(Long id);

    void saveNewUser(User user);

    void saveUserModifications(User user);

    void deleteUser(Long id);
}
