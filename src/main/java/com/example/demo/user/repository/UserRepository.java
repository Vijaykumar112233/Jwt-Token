package com.example.demo.user.repository;

import com.example.demo.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

    Optional<UserEntity> findByEmail(String email);

    List<UserEntity> findByFirstNameIgnoreCase(String firstName);

    List<UserEntity> findByFirstNameContainingIgnoreCase(String firstName);

    UserEntity findByMobile(String number);

}
