package com.bookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bookshop.entity.UserEntity;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>{
    UserEntity findByUsernameAndPassword(String username, String password);

    UserEntity findByUsernameNotAndEmail(String username, String email);
    @Query(value="SELECT * FROM User WHERE username = ?1 AND user_id != ?2", nativeQuery = true)
    UserEntity findByUsername(String username, int userId);
}
