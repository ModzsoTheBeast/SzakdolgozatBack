package hu.erpex.project.manager.projectmanager.mvc.project.controller;

import hu.erpex.project.manager.projectmanager.mvc.email.dto.EmailSendDTO;
import hu.erpex.project.manager.projectmanager.mvc.email.service.EmailService;
import hu.erpex.project.manager.projectmanager.mvc.project.dto.CreateProjectDTO;
import hu.erpex.project.manager.projectmanager.mvc.project.dto.ProjectDTO;
import hu.erpex.project.manager.projectmanager.mvc.project.service.ProjectService;
import hu.erpex.project.manager.projectmanager.mvc.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/project")
@RestController
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private RoleService roleService;



    // findAllByUserId - használva
    @GetMapping("/findAllByUserId/{id}")
    public ResponseEntity<List<ProjectDTO>> findAllByUserId(@PathVariable Long id){

        return ResponseEntity.ok(projectService.findAllByUserId(id));
    }

    // save
    @PostMapping()
    public CreateProjectDTO save(@RequestBody CreateProjectDTO data){
        return projectService.save(data);
    }

    //ha a user role az project owner TODO
    @PostMapping("/delete/{projectid}")
    public ResponseEntity<?> delete(@PathVariable Long projectid){
        roleService.deleteRoleByProjectId(projectid);
        projectService.delete(projectid);
        return ResponseEntity.ok().body("deleted successfully");
    }





}
