package com.example.szakdolgozatBack.Authentication.Auth;

import com.example.szakdolgozatBack.Models.Entities.UserEntity;
import com.example.szakdolgozatBack.Models.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("user")
public class ApplicationUserDAOService implements ApplicationUserDAO{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public ApplicationUserDAOService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<UserEntity> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream()
                .filter(userEntity -> username.equals(userEntity.getUser_name()))
                .findFirst();
    }

    private List<UserEntity> getApplicationUsers(){
        return this.userRepository.findAll();
    }
}
