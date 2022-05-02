package hu.erpex.project.manager.projectmanager.mvc.user.controller;

import hu.erpex.project.manager.projectmanager.mvc.email.dto.EmailSendDTO;
import hu.erpex.project.manager.projectmanager.mvc.email.service.EmailService;
import hu.erpex.project.manager.projectmanager.mvc.exceptions.IdNotFoundException;
import hu.erpex.project.manager.projectmanager.mvc.role.entity.RoleEntity;
import hu.erpex.project.manager.projectmanager.mvc.role.repository.RoleRepository;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UserDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UserLoginDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UserUpdateDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UsersInProjectDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value ="api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private RoleRepository roleRepository;


    @GetMapping("/getusers/{projectId}")
    public List<UsersInProjectDTO> getusers(@PathVariable Long projectId){
        List<UsersInProjectDTO> users = new ArrayList<>();
        for(RoleEntity role : roleRepository.findAllByProjectId(projectId)){
           UsersInProjectDTO user = new UsersInProjectDTO();
           user.setUserId(role.getUser().getId());
           user.setUserEmail(role.getUser().getUserEmail());
           user.setUserName(role.getUser().getUserName());
           user.setRole(role.getRole());
           users.add(user);
        }
        return users;
    }

    // registration
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody UserDTO data){
        if(usersService.findByUserEmail(data.getUserEmail()).getUserEmail()!=null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("A megadott email-címmel már van regisztrált felhasználónk!");

        }
        else if(usersService.findByUsername(data.getUserName()).getUserName()!=null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("A felhasználónév már foglalt!");

        }
        else{
            return ResponseEntity.ok(usersService.save(data));
        }
    }


    // update -használt
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UserUpdateDTO data){
        try{
            if(data.getId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem megfelelő hívás, az ID kötelező!");
            }
            return ResponseEntity.ok(usersService.update(data));
        } catch (IdNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nem található elem. ID:" + exception.getId());
        }
    }

    //get userId by username
    @GetMapping("/{username}")
    public UserLoginDTO getUserId(@PathVariable String username){
        UserLoginDTO dto = new UserLoginDTO();
        dto.setId(usersService.findByUsername(username).getId());
        return dto;
    }

    //get userrole TODO


}
