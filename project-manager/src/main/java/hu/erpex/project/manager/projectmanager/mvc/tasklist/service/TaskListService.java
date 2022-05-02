package hu.erpex.project.manager.projectmanager.mvc.tasklist.service;

import hu.erpex.project.manager.projectmanager.mvc.exceptions.IdNotFoundException;
import hu.erpex.project.manager.projectmanager.mvc.task.service.TaskService;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.dto.CreateTaskListDTO;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.dto.TaskListDTO;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.entity.TaskListEntity;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.repository.TaskListRepository;
import hu.erpex.project.manager.projectmanager.mvc.task.repository.TaskRepository;
import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.dto.TaskListItemDTO;
import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.entity.TaskListItemEntity;
import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.service.TaskListItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskListService {

    @Autowired
    private TaskListRepository taskListRepository;

    @Autowired
    private TaskListItemService taskListItemService;

    @Autowired
    private TaskRepository taskRepository;

    public List<TaskListEntity> findAllByTaskId(Long id){
        return taskListRepository.findAllByTaskId(id);
    }

    @Transactional
    public void delete(Long id) {
        for(TaskListItemEntity taskListItem : taskListItemService.findAllByTaskListId(id)){
            taskListItemService.delete(taskListItem.getId());
        }
        taskListRepository.delete(id);
    }

    public TaskListEntity findById(Long taskListId){
        try {
            return taskListRepository.findById(taskListId);
        }catch (IdNotFoundException e){
            return null;
        }
    }

    @Transactional
    public CreateTaskListDTO save(CreateTaskListDTO dto){
        TaskListEntity entity= new TaskListEntity();
        entity.setTaskListName(dto.getTaskListName());
        entity.setTask(taskRepository.findById(dto.getTaskId()));
        entity=taskListRepository.save(entity);
        return entityToDTO(entity);

    }




    @Transactional
    public TaskListEntity updateTaskList(TaskListDTO dto){
        TaskListEntity entity = taskListRepository.findById(dto.getTaskListId());
        entity.setTaskListName(dto.getTaskListName());
        entity = taskListRepository.update(entity);
        return entity;
    }

    private CreateTaskListDTO entityToDTO(TaskListEntity entity){
        CreateTaskListDTO dto = new CreateTaskListDTO();
        dto.setTaskId(entity.getTask().getId());
        dto.setTaskListName(entity.getTaskListName());
        dto.setTaskListId(entity.getId());
        return dto;
    }

    public List<TaskListDTO> getTaskListDTOByTaskId(Long taskId){
        List<TaskListDTO> taskLists = new ArrayList<>();
        for(TaskListEntity entity: taskListRepository.findAllByTaskId(taskId)){
            taskLists.add(entityToTaskListDTO(entity));
        }
        return taskLists;
    }

    private TaskListDTO entityToTaskListDTO(TaskListEntity entity){
        TaskListDTO dto = new TaskListDTO();
        dto.setTaskListId(entity.getId());
        dto.setTaskListName(entity.getTaskListName());
        dto.setTaskListItems(taskListItemService.getTaskListItemDTOByTaskListId(entity.getId()));
        return dto;
    }
}
