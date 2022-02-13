package Controllers;


import Models.DTOs.RegisterDTO;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService _userService;

    @PostMapping(value = "register")
    public ResponseEntity<String> register(@RequestBody RegisterDTO registerDTO){
        return new ResponseEntity<String>(this._userService.register(registerDTO), HttpStatus.OK);
    }
}
