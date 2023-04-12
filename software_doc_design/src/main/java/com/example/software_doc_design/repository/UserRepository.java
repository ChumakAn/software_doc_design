package com.example.software_doc_design.repository;

import com.example.software_doc_design.persistance.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("select u from UserEntity u where u.login = :login and u.password =:password")
    UserEntity getByLoginAndPassword(String login, String password);
}
