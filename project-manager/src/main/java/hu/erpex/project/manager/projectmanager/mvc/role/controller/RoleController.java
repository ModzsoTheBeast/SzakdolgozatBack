package hu.erpex.project.manager.projectmanager.mvc.role.controller;

import hu.erpex.project.manager.projectmanager.mvc.email.dto.EmailSendDTO;
import hu.erpex.project.manager.projectmanager.mvc.email.service.EmailService;
import hu.erpex.project.manager.projectmanager.mvc.enums.RoleEnum;
import hu.erpex.project.manager.projectmanager.mvc.exceptions.IdNotFoundException;
import hu.erpex.project.manager.projectmanager.mvc.role.dto.AddUserToProjectDTO;
import hu.erpex.project.manager.projectmanager.mvc.role.dto.RoleDTO;
import hu.erpex.project.manager.projectmanager.mvc.role.service.RoleService;
import hu.erpex.project.manager.projectmanager.mvc.user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/role")
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UsersService usersService;


    @Autowired
    private EmailService emailService;

    // findByIds
    @PostMapping("/get")
    public ResponseEntity<?> findByIds(@RequestBody RoleDTO data){

        try{
            return ResponseEntity.ok(roleService.findRoleDTOByIds(data));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }


    // update role
    @PostMapping("/updaterole")
    public ResponseEntity<?> update(@RequestBody RoleDTO data){

        if(roleService.findRoleEntityByIds(data).getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem megfelelő hívás, nincs ilyen user a projektben!");
        }
        roleService.update(data);

        return ResponseEntity.ok(data);

    }

    @PostMapping("/adduser")
    public ResponseEntity<?> addUserToProject(@RequestBody AddUserToProjectDTO data){
        try {
            RoleDTO role = new RoleDTO();
            role.setProjectId(data.getProjectId());
            role.setUserId(usersService.findByUserEmail(data.getUserEmail()).getId());
            RoleDTO dto = roleService.create(role.getRole(), role.getProjectId(), role.getUserId());
            return ResponseEntity.ok(dto);
        }catch (IdNotFoundException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Nem található ilyen priject.");
        }
    }



    @PostMapping(value = "/email")
    public ResponseEntity<?> sendEmail(@RequestBody EmailSendDTO email){
        try {
            emailService.sendSimpleMessage(email.getToEmail(), email.getProjectId());
            roleService.create(RoleEnum.DEVELOPER, email.getProjectId(), usersService.findByUserEmail(email.getToEmail()).getId());
            return new ResponseEntity<>(email,  HttpStatus.OK);
        } catch( MailException e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestBody RoleDTO data){
        try{
            roleService.deleteByIds(data);
            return ResponseEntity.ok().body("noice");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }
}
