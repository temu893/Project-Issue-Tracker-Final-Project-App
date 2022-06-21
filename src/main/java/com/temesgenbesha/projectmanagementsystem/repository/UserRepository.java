package com.temesgenbesha.projectmanagementsystem.repository;

import com.temesgenbesha.projectmanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    //Hibernate will translate this to quire language
    Optional<User> findByUsername(String username);
}
