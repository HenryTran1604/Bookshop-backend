package com.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookshop.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByUsernameAndPassword(String username, String password);

    UserEntity findByUsernameNotAndEmail(String username, String email);
    UserEntity findByUsername(String username);
}
