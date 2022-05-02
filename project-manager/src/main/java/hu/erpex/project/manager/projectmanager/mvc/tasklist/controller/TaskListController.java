package hu.erpex.project.manager.projectmanager.mvc.tasklist.controller;

import hu.erpex.project.manager.projectmanager.mvc.exceptions.IdNotFoundException;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.dto.CreateTaskListDTO;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.dto.TaskListDTO;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.entity.TaskListEntity;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.service.TaskListService;
import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.dto.TaskListItemDTO;
import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.service.TaskListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping(value = "/api/tasklist")
@RestController
public class TaskListController {

    @Autowired
    private TaskListService taskListService;

    @Autowired
    private TaskListItemService taskListItemService;

    @PostMapping("/delete/{tasklistid}")
    public ResponseEntity<String> delete(@PathVariable Long tasklistid){
        try {
            taskListService.delete(tasklistid);
            return ResponseEntity.ok().body("deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.toString());
        }
    }

    @PostMapping("/save")
    public CreateTaskListDTO save(@RequestBody CreateTaskListDTO data){
        return taskListService.save(data);
    }

    @PostMapping("/updatetasklist")
    public ResponseEntity<?> updateItems(@RequestBody TaskListDTO data){
        try{
            TaskListEntity entity = taskListService.updateTaskList(data);
            for(TaskListItemDTO item: data.getTaskListItems()){
                taskListItemService.update(item);
            }
            return ResponseEntity.ok(data);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("A lista nem található");
        }
    }



}
