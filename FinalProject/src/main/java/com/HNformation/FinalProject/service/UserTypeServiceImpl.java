package com.HNformation.FinalProject.service;

import com.HNformation.FinalProject.entity.UserType;
import com.HNformation.FinalProject.exception.DuplicateUserTypeException;
import com.HNformation.FinalProject.exception.IdNotAllowedException;
import com.HNformation.FinalProject.exception.NoChangesDetectedException;
import com.HNformation.FinalProject.repository.UserTypeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;

    @Autowired
    public UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public List<UserType> findAllUserTypes() {
        return userTypeRepository.findAll();
    }

    @Override
    public UserType findUserTypeById(Long id) {
        return userTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not attributed to any User Type"));
    }

    @Override
    public UserType saveNewUserType(UserType userType) {
        if (userType.getUserTypeId() != null) {
            throw new IdNotAllowedException("Id must not be given, it is automatically attributed");
        }
        if (!userTypeRepository.findByLabelIgnoreCase(userType.getLabel()).isEmpty()) {
            throw new DuplicateUserTypeException("User type already existing");
        }
        return userTypeRepository.save(userType);
    }

    @Override
    public void saveUserTypeModifications(UserType userType) {
        UserType savedUserType = userTypeRepository.findById(userType.getUserTypeId()).orElseThrow(() -> new EntityNotFoundException("Id not attributed to any User"));
        if (userType.getLabel().equalsIgnoreCase(savedUserType.getLabel())) {
            throw new NoChangesDetectedException("No changes detected");
        }
        if (!userTypeRepository.findByLabelIgnoreCase(userType.getLabel()).isEmpty()) {
            throw new DuplicateUserTypeException("User type already existing");
        }
        userType.setUserTypeId(savedUserType.getUserTypeId());
        userTypeRepository.save(userType);
    }

    @Override
    public void deleteUserType(Long id) {
        UserType userType = userTypeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Id not attributed to any User"));
        userTypeRepository.delete(userType);
    }
}
