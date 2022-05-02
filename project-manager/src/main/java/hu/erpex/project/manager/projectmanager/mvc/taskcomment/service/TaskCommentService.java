package hu.erpex.project.manager.projectmanager.mvc.taskcomment.service;

import hu.erpex.project.manager.projectmanager.mvc.task.service.TaskService;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.dto.CreateTaskCommentDTO;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.dto.TaskCommentDTO;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.dto.UpdateCommentDTO;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.entity.TaskCommentEntity;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.repository.TaskCommentRepository;
import hu.erpex.project.manager.projectmanager.mvc.task.repository.TaskRepository;
import hu.erpex.project.manager.projectmanager.mvc.user.repository.UsersRepository;
import hu.erpex.project.manager.projectmanager.mvc.user.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskCommentService {
    @Autowired
    private TaskCommentRepository taskCommentRepository;

    @Autowired
    private UsersService usersService;

    @Autowired
    private TaskRepository taskRepository;


    public List<TaskCommentEntity> findAllByTaskId(Long id){
        return taskCommentRepository.findAllByTaskId(id);
    }

    @Transactional
    public void delete(Long id) {
        taskCommentRepository.delete(id);
    }


    @Transactional
    public CreateTaskCommentDTO save(CreateTaskCommentDTO dto){
        TaskCommentEntity entity = new TaskCommentEntity();
        entity.setCommentDate(new Date());
        entity.setCommentName(dto.getComment());
        entity.setCreatedBy(usersService.findById(dto.getUserId()));
        entity.setTask(taskRepository.findById(dto.getTaskId()));
        entity= taskCommentRepository.save(entity);
        return entityToDTO(entity);
    }

    private CreateTaskCommentDTO entityToDTO(TaskCommentEntity entity){
        CreateTaskCommentDTO dto = new CreateTaskCommentDTO();
        dto.setTaskId(entity.getTask().getId());
        dto.setUserId(entity.getCreatedBy().getId());
        dto.setComment(entity.getCommentName());
        dto.setCreatedOn(entity.getCommentDate());
        dto.setCommentId(entity.getId());
        return dto;
    }

    public List<TaskCommentDTO> getTaskCommentDTObyTaskId(Long taskId){
        List<TaskCommentDTO> comments = new ArrayList<>();
        for(TaskCommentEntity entity: taskCommentRepository.findAllByTaskId(taskId)){
            comments.add(entityToTaskCommentDTO(entity));
        }
        return comments;
    }

    private TaskCommentDTO entityToTaskCommentDTO(TaskCommentEntity entity){
        TaskCommentDTO dto = new TaskCommentDTO();
        dto.setComment(entity.getCommentName());
        dto.setCreatedOn(entity.getCommentDate());
        dto.setTaskCommentId(entity.getId());
        dto.setCreatedBy(entity.getCreatedBy().getUserName());
        return dto;
    }

    @Transactional
    public UpdateCommentDTO update(UpdateCommentDTO data) {
        TaskCommentEntity entity = taskCommentRepository.findById(data.getTaskCommentId());
        entity.setCommentName(data.getComment());
        entity= taskCommentRepository.update(entity);
        UpdateCommentDTO updated = new UpdateCommentDTO();
        updated.setComment(entity.getCommentName());
        updated.setTaskCommentId(entity.getId());
        return updated;
    }
}



















