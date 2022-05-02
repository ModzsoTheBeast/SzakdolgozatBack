package hu.erpex.project.manager.projectmanager.mvc.list.controller;

import hu.erpex.project.manager.projectmanager.mvc.exceptions.IdNotFoundException;
import hu.erpex.project.manager.projectmanager.mvc.list.dto.*;
import hu.erpex.project.manager.projectmanager.mvc.list.entity.ListEntity;
import hu.erpex.project.manager.projectmanager.mvc.list.service.ListService;
import hu.erpex.project.manager.projectmanager.mvc.role.entity.RoleEntity;
import hu.erpex.project.manager.projectmanager.mvc.role.repository.RoleRepository;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UsersInProjectDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/list")
@RestController
public class ListController {


    @Autowired
    private ListService listService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/delete/{listId}")
    public ResponseEntity<String> delete(@PathVariable Long listId){
        try {
            int pos = listService.findByID(listId).getPositionInProject();
            Long projectId = listService.findByID(listId).getProject().getId();
            listService.delete(listId);
            listService.moveLists(pos, projectId);
            return ResponseEntity.ok().body("deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/updatelist")
    public ResponseEntity<?> updateList(@RequestBody UpdateListDTO data){
        try{
            return ResponseEntity.ok(listService.updateList(data));
        }catch (IdNotFoundException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Lista nem található");
        }
    }

    // save
    @PostMapping()
    public ResponseEntity<?> save(@RequestBody CreateListDTO data){
        if(data.getPosition()!=null && data.getProjectId()!=null){
            return ResponseEntity.ok(listService.createList(data));
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem megfelelő hívás, a pozíció és a projectid megadása kötelező!");
    }

    //move list
    @PostMapping("/move")
    public ResponseEntity<?> moveList(@RequestBody MoveListDTO data){
        if(data.getStartPosition() == null || data.getEndPosition() == null || data.getProjectId()== null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Minden mező kitöltése köztelező!");
        }
        else {
            ListEntity entity = listService.findEntityByProjectAndPosition(data.getProjectId(), data.getStartPosition());
            entity = listService.setPosition(entity, -1);
            if(data.getStartPosition()>data.getEndPosition()){
                for(Integer i = data.getStartPosition(); i > data.getEndPosition(); i--){
                    ListEntity e = listService.findEntityByProjectAndPosition(data.getProjectId(), i-1);
                    e = listService.setPosition(e, i);
                }
            }
            if(data.getStartPosition()<data.getEndPosition()){
                for(Integer i = data.getStartPosition(); i < data.getEndPosition(); i++){
                    ListEntity e = listService.findEntityByProjectAndPosition(data.getProjectId(), i+1);
                    e = listService.setPosition(e, i);
                }
            }
            entity = listService.setPosition(entity, data.getEndPosition());
            return ResponseEntity.ok(data);
        }
    }

    @GetMapping("findByProject/{projectId}")
    public ResponseEntity<?> findByProject(@PathVariable Long projectId){
        try {
            return ResponseEntity.ok(listService.findByProject(projectId));
        }catch (IdNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem megfelelő hívás, a következő id-val nem található projekt:" + e.getId());
        }
    }

    @PostMapping("/moveRight")
    public MoveDTO moveRight(@RequestBody MoveDTO dto){
        return listService.moveRight(dto);
    }

    @PostMapping("/moveLeft")
    public MoveDTO moveLeft(@RequestBody MoveDTO dto){
        return listService.moveLeft(dto);
    }
}
