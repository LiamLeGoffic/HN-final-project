package com.HNformation.FinalProject.repository;

import com.HNformation.FinalProject.entity.UserType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends CrudRepository<UserType, Long> {

    List<UserType> findAll();

    @Query("SELECT u FROM UserType u WHERE LOWER(u.label) = LOWER(:label)")
    List<UserType> findByLabelIgnoreCase(@Param("label") String label);
}
