package hu.erpex.project.manager.projectmanager.mvc.task.service;

import hu.erpex.project.manager.projectmanager.mvc.exceptions.IdNotFoundException;
import hu.erpex.project.manager.projectmanager.mvc.list.entity.ListEntity;
import hu.erpex.project.manager.projectmanager.mvc.list.repository.ListRepository;
import hu.erpex.project.manager.projectmanager.mvc.role.repository.RoleRepository;
import hu.erpex.project.manager.projectmanager.mvc.task.dto.*;
import hu.erpex.project.manager.projectmanager.mvc.task.entity.TaskEntity;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.entity.TaskCommentEntity;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.repository.TaskCommentRepository;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.service.TaskCommentService;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.entity.TaskListEntity;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.repository.TaskListRepository;
import hu.erpex.project.manager.projectmanager.mvc.task.repository.TaskRepository;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.service.TaskListService;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UsersOnTaskDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.entity.UsersEntity;
import hu.erpex.project.manager.projectmanager.mvc.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private TaskCommentService taskCommentService;

    @Autowired
    private TaskListService taskListService;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public UpdateTaskDTO updateTask(UpdateTaskDTO dto){
        TaskEntity entity = taskRepository.findById(dto.getTaskId());
        if(dto.getTaskName()!=null && !dto.getTaskName().equals("")){
            entity.setTaskName(dto.getTaskName());
        }
        if(dto.getTaskDescription()!=null && !dto.getTaskDescription().equals("")){
            entity.setTaskDescription(dto.getTaskDescription());
        }
        entity=taskRepository.update(entity);
        return dto;
    }

    public List<TaskDTO> findByList(Long listId){
        List<TaskDTO> tasks = new ArrayList<>();
        for(TaskEntity task : taskRepository.findAllByList(listId)){
            TaskDTO dto = new TaskDTO();
            dto.setTaskId(task.getId());
            dto.setTaskName(task.getTaskName());
            dto.setTaskDescription(task.getTaskDescription());
            dto.setPriority(task.getLevel());
            tasks.add(dto);
        }
        return tasks;
    }

    @Transactional
    public CreateTaskDTO createTask(CreateTaskDTO dto){
        TaskEntity entity = new TaskEntity();
        entity.setTaskName(dto.getTaskName());
        entity.setPositionInList(dto.getPositionInList());
        entity.setList(listRepository.findById(dto.getListId()));
        entity.setCreatedOn(new Date());
        entity = taskRepository.save(entity);
        return entityToDto(entity);
    }

    private CreateTaskDTO entityToDto(TaskEntity entity){
        CreateTaskDTO dto = new CreateTaskDTO();
        dto.setTaskId(entity.getId());
        dto.setTaskName(entity.getTaskName());
        dto.setListId(entity.getList().getId());
        dto.setPositionInList(entity.getPositionInList());
        return dto;
    }

    @Transactional
    public TaskEntity setPosition(TaskEntity entity, Integer position){
        entity.setPositionInList(position);
        entity = taskRepository.update(entity);
        return entity;
    }

    public TaskEntity findByListAndPosition(Long listId, Integer position){
        return taskRepository.findByListAndPosition(listId, position).get(0);
    }

    public TaskEntity findTaskByProjectAndListID(Long projectId, Integer listPosition, Integer taskPosition){
        TaskEntity entity = new TaskEntity();
        for(TaskEntity task: taskRepository.findAll()){
            if (task.getList().getProject().getId().equals(projectId) && task.getList().getPositionInProject().equals(listPosition) && task.getPositionInList().equals(taskPosition)) {
                entity = task;
            }
        }
        return entity;
    }

    @Transactional
    public TaskEntity updateMove(TaskEntity entity){
        return taskRepository.update(entity);
    }


    public List<TaskEntity> findAllByListId(Long id){
        return taskRepository.findAllByListId(id);
    }

    @Transactional
    public void delete(Long id) {
        for(TaskListEntity taskList: taskListService.findAllByTaskId(id)){
            taskListService.delete(taskList.getId());
        }
        for(TaskCommentEntity taskComment: taskCommentService.findAllByTaskId(id)){
            taskCommentService.delete(taskComment.getId());
        }
        taskRepository.delete(id);
    }

    public TaskEntity findById(Long taskId){
        try {
            return taskRepository.findById(taskId);
        }
        catch (IdNotFoundException e){
            return null;
        }
    }

    public DetailedTaskDTO getDetailedTaskDTO(Long taskId){
        TaskEntity entity = findById(taskId);
        return entityToDetailedTaskDTO(entity);
    }

    @Transactional
    public TaskIsDoneDTO setDone(TaskIsDoneDTO dta){
        TaskEntity entity = taskRepository.findById(dta.getTaskId());
        entity.setDone(dta.getIsDone());
        entity = taskRepository.update(entity);
        dta.setIsDone(entity.isDone());
        return dta;
    }

    private DetailedTaskDTO entityToDetailedTaskDTO(TaskEntity entity){
        DetailedTaskDTO dto = new DetailedTaskDTO();
        dto.setTaskId(entity.getId());
        dto.setTaskDescription(entity.getTaskDescription());
        dto.setTaskName(entity.getTaskName());
        dto.setCreatedOn(entity.getCreatedOn());
        dto.setDeadline(entity.getDeadline());
        dto.setLevel(entity.getLevel());
        List<UsersOnTaskDTO> usersOnTask = new ArrayList<>();
        for(UsersEntity user: taskRepository.findUserOnTask(entity.getId())){
            usersOnTask.add(usersEntityToUsersOnTaskDTO(user));
        }
        dto.setContributors(usersOnTask);
        dto.setTaskLists(taskListService.getTaskListDTOByTaskId(entity.getId()));
        dto.setDone(entity.isDone());
        dto.setComments(taskCommentService.getTaskCommentDTObyTaskId(entity.getId()));
        return dto;
    }

    private UsersOnTaskDTO usersEntityToUsersOnTaskDTO(UsersEntity entity){
        UsersOnTaskDTO dto = new UsersOnTaskDTO();
        dto.setUserId(entity.getId());
        dto.setUserName(entity.getUserName());
        dto.setUserEmail(entity.getUserEmail());
        return dto;
    }

    @Transactional
    public AddUserToTaskDTO addUserToTask(AddUserToTaskDTO dto){
        TaskEntity entity = taskRepository.findById(dto.getTaskId());
        List<UsersEntity> users = new ArrayList<>();
        for(Long userId : dto.getUserId()){
            users.add(usersRepository.findById(userId));
        }
        entity.setContributors(users);
        entity = taskRepository.update(entity);
        return dto;
    }

    @Transactional
    public String removeUserFromTask(RemoveUserFromTaskDTO dto) {
        TaskEntity entity = taskRepository.findById(dto.getTaskId());
        List<UsersEntity> users = entity.getContributors();
        users.remove(usersRepository.findById(dto.getUserId()));
        entity.setContributors(users);
        entity = taskRepository.update(entity);

        return "removed";
    }

    @Transactional
    public UpdateTaskDeadlineDTO updateDeadline(UpdateTaskDeadlineDTO data) {
        TaskEntity entity = taskRepository.findById(data.getTaskId());
        if(data.getDeadline()!=null){
            entity.setDeadline(data.getDeadline());
        }
        entity=taskRepository.update(entity);
        data.setDeadline(entity.getDeadline());
        return data;
    }

    @Transactional
    public UpdateTaskLevelDTO updateLevel(UpdateTaskLevelDTO data) {
        TaskEntity entity = taskRepository.findById(data.getTaskId());
        if(data.getPriority()!=null){
            entity.setLevel(data.getPriority());
        }
        entity=taskRepository.update(entity);
        data.setPriority(entity.getLevel());
        return data;
    }
}
