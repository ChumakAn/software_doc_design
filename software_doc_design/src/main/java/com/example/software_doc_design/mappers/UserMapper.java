package com.example.software_doc_design.mappers;

import com.example.software_doc_design.domain.User;
import com.example.software_doc_design.persistance.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity userEntity) {
        User.UserBuilder userBuilder = User.builder();
        return userBuilder
                .id(userEntity.getId())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .phoneNumber(userEntity.getPhoneNumber())
                .login(userEntity.getLogin())
                .password(userEntity.getPassword())
                .build();

    }

    public UserEntity toEntity(User user) {
        UserEntity.UserEntityBuilder userEntityBuilder = UserEntity.builder();
        return userEntityBuilder
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNumber(user.getPhoneNumber())
                .login(user.getLogin())
                .password(user.getPassword()).build();
    }

}
