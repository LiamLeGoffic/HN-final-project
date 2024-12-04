package com.HNformation.FinalProject.service;

import com.HNformation.FinalProject.entity.UserType;

import java.util.List;

public interface UserTypeService {

    List<UserType> findAllUserTypes();

    UserType findUserTypeById(Long id);

    UserType saveNewUserType(UserType userType);

    void saveUserTypeModifications(UserType userType);

    void deleteUserType(Long id);
}
