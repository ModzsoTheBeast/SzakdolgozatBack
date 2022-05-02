package hu.erpex.project.manager.projectmanager.mvc.tasklistitem.controller;

import hu.erpex.project.manager.projectmanager.mvc.exceptions.IdNotFoundException;
import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.dto.CreateTaskListItemDTO;
import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.service.TaskListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/tasklistitem")
@RestController
public class TaskListItemController {

    @Autowired
    private TaskListItemService taskListItemService;

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        try{
            taskListItemService.delete(id);
            return ResponseEntity.ok().body("deleted successfully");
        }catch (IdNotFoundException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Az id nem található");
        }
    }

    @PostMapping("/save")
    public CreateTaskListItemDTO save (@RequestBody CreateTaskListItemDTO data){
        return taskListItemService.save(data);
    }
}
