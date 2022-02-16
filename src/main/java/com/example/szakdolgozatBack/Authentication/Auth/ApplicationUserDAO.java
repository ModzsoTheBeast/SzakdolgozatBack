package com.example.szakdolgozatBack.Authentication.Auth;

import com.example.szakdolgozatBack.Models.Entities.UserEntity;

import java.util.Optional;

public interface ApplicationUserDAO {
    Optional<UserEntity> selectApplicationUserByUsername(String username);
}
