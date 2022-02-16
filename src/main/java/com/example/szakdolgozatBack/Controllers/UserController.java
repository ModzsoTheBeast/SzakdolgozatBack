package com.example.szakdolgozatBack.Controllers;

import com.example.szakdolgozatBack.Models.DTOs.LoginDTO;
import com.example.szakdolgozatBack.Models.DTOs.RegisterDTO;
import com.example.szakdolgozatBack.Models.Entities.UserEntity;
import com.example.szakdolgozatBack.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService _userService;

    @PostMapping(value = "register")
    public void register(@RequestBody RegisterDTO registerDTO){
        this._userService.register(registerDTO);
    }

    @GetMapping(value = "login")
    public ResponseEntity<LoginDTO> login() {
        return new ResponseEntity<LoginDTO> (new LoginDTO(), HttpStatus.OK);
    }

}
