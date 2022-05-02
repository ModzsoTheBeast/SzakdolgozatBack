package hu.erpex.project.manager.projectmanager.mvc.taskcomment.controller;

import hu.erpex.project.manager.projectmanager.mvc.exceptions.IdNotFoundException;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.dto.CreateTaskCommentDTO;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.dto.UpdateCommentDTO;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.service.TaskCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping(value ="/api/taskcomment")
@RestController
public class TaskCommentController {

    @Autowired
    private TaskCommentService taskCommentService;

    @PostMapping("/delete/{taskCommentId}")
    public ResponseEntity<String> delete(@PathVariable Long taskCommentId){
        try {
            taskCommentService.delete(taskCommentId);
            return ResponseEntity.ok().body("deleted successfully");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("az id nem található");
        }
    }

    @PostMapping("/save")
    public CreateTaskCommentDTO save (@RequestBody CreateTaskCommentDTO dto){
        return taskCommentService.save(dto);
    }


    @PostMapping("/update")
    public UpdateCommentDTO update(@RequestBody UpdateCommentDTO data){
        return taskCommentService.update(data);
    }
}
