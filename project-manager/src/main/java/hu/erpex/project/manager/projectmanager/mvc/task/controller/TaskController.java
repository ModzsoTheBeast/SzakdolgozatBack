package hu.erpex.project.manager.projectmanager.mvc.task.controller;

import hu.erpex.project.manager.projectmanager.mvc.exceptions.IdNotFoundException;
import hu.erpex.project.manager.projectmanager.mvc.list.entity.ListEntity;
import hu.erpex.project.manager.projectmanager.mvc.list.service.ListService;
import hu.erpex.project.manager.projectmanager.mvc.task.dto.*;
import hu.erpex.project.manager.projectmanager.mvc.task.entity.TaskEntity;
import hu.erpex.project.manager.projectmanager.mvc.task.service.TaskService;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UserUpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/task")
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ListService listService;

    @PostMapping("/setDone")
    public ResponseEntity<?> setDone(@RequestBody TaskIsDoneDTO data){
        try{
            return ResponseEntity.ok(taskService.setDone(data));
        }catch (IdNotFoundException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Task nem található");
        }
    }

    @GetMapping("/detailedtask/{taskId}")
    public ResponseEntity<?> getDetails(@PathVariable Long taskId){
        try {
            return ResponseEntity.ok(taskService.getDetailedTaskDTO(taskId));

        }catch (IdNotFoundException e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Az task nem található!");
        }
    }

    // save
    @PostMapping()
    public ResponseEntity<CreateTaskDTO> save(@RequestBody CreateTaskDTO data){
        return ResponseEntity.ok(taskService.createTask(data));
    }

    @PostMapping("/delete/{taskId}")
    public ResponseEntity<String> delete(@PathVariable Long taskId){
        try {
            taskService.delete(taskId);
            return ResponseEntity.ok().body("deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("az id nem található");
        }
    }

    //update
    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody UpdateTaskDTO data){
        try{
            if(data.getTaskId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem megfelelő hívás, az ID kötelező!");
            }
            return ResponseEntity.ok(taskService.updateTask(data));
        } catch (IdNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nem található elem. ID:" + exception.getId());
        }
    }

    //update level
    @PostMapping("/priority")
    public ResponseEntity<?> updateLevel(@RequestBody UpdateTaskLevelDTO data){
        try{
            if(data.getTaskId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem megfelelő hívás, az ID kötelező!");
            }
            return ResponseEntity.ok(taskService.updateLevel(data));
        } catch (Exception exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    //update deadline
    @PostMapping("/adddeadline")
    public ResponseEntity<?> updateDeadline(@RequestBody UpdateTaskDeadlineDTO data){
        try{
            if(data.getTaskId() == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem megfelelő hívás, az ID kötelező!");
            }
            return ResponseEntity.ok(taskService.updateDeadline(data));
        } catch (IdNotFoundException exception){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nem található elem. ID:" + exception.getId());
        }
    }

    //move task in list
    @PostMapping("/movetaskinlist")
    public MoveTaskInListDTO moveTaskInList(@RequestBody MoveTaskInListDTO data){
        TaskEntity entity = taskService.findByListAndPosition(data.getListId(), data.getStartPosition());
        entity = taskService.setPosition(entity, -1);
        if(data.getStartPosition()<data.getEndPosition()){
            for (int i = data.getStartPosition()+1; i <= data.getEndPosition(); i++){
                TaskEntity task = taskService.findByListAndPosition(data.getListId(), i);
                task = taskService.setPosition(task, i-1);
            }
            entity = taskService.setPosition(entity, data.getEndPosition());
        }
        if(data.getStartPosition()>data.getEndPosition()){
            for (int i = data.getStartPosition()-1; i >= data.getEndPosition(); i--){
                TaskEntity task = taskService.findByListAndPosition(data.getListId(), i);
                task = taskService.setPosition(task, i+1);
            }
            entity = taskService.setPosition(entity, data.getEndPosition());
        }
        return data;
    }

    @PostMapping("/movetaskbetweenlists")
    public MoveTaskBetweenListsDTO moveTaskBetweenLists(@RequestBody MoveTaskBetweenListsDTO dto){
        if(dto.getEndListLength() - 1 != dto.getEndTaskPosition()){
            for(int i = dto.getEndListLength() - 1; i > dto.getEndTaskPosition(); i--){
                TaskEntity task = taskService.findByListAndPosition(dto.getEndListId(), i-1);
                task.setPositionInList(i);
                taskService.updateMove(task);
            }
        }

        TaskEntity moved = taskService.findByListAndPosition(dto.getStartListId(), dto.getStartTaskPosition());
        ListEntity list = listService.findByID(dto.getEndListId());
        moved.setList(list);
        moved.setPositionInList(dto.getEndTaskPosition());
        moved = taskService.updateMove(moved);

        if(!Objects.equals(dto.getStartTaskPosition(), dto.getStartListLength())){
            for(int i = dto.getStartTaskPosition(); i < dto.getStartListLength(); i++){
                TaskEntity task1 = taskService.findByListAndPosition(dto.getStartListId(), i+1);
                task1.setPositionInList(i);
                taskService.updateMove(task1);
            }
        }


        return  dto;
    }

    @PostMapping("/addusers")
    public ResponseEntity<?> addUsers(@RequestBody AddUserToTaskDTO dto){
        try {
            return ResponseEntity.ok(taskService.addUserToTask(dto));
        }catch (IdNotFoundException exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("task nem található!");
        }
    }

    @PostMapping("/removeuserfromtask")
    public ResponseEntity<?> removeUserFromTask(@RequestBody RemoveUserFromTaskDTO dto){
        try{
            if(dto.getTaskId()==null || dto.getUserId()==null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nem megfelelő hívás, az ID kötelező!");
            }
            else{
                return ResponseEntity.ok(taskService.removeUserFromTask(dto));

            }
        }catch (IdNotFoundException exception){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("task nem található!");
        }
    }

}
