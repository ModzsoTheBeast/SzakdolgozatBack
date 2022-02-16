package com.example.szakdolgozatBack.Services;

import com.example.szakdolgozatBack.Interfaces.UserServiceInterface;
import com.example.szakdolgozatBack.Models.DTOs.LoginDTO;
import com.example.szakdolgozatBack.Models.DTOs.RegisterDTO;
import com.example.szakdolgozatBack.Models.Entities.UserEntity;
import com.example.szakdolgozatBack.Models.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Service
public class UserService implements UserServiceInterface {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(){}

    @Transactional
    public void register(RegisterDTO registerDTO){
        System.out.println("asd");
        UserEntity user = new UserEntity();
        user.setUser_name(registerDTO.getUser_name());
        user.setEmail(registerDTO.getEmail());
        user.setFull_name(registerDTO.getFull_name());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        userRepository.save(user);
        entityManager.persist(user);
    }

}
