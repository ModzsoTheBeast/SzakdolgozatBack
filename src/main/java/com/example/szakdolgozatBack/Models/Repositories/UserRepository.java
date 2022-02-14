package com.example.szakdolgozatBack.Models.Repositories;

import com.example.szakdolgozatBack.Models.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> { }
