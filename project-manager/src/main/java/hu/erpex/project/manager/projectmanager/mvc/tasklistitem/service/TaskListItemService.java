package hu.erpex.project.manager.projectmanager.mvc.tasklistitem.service;

import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.dto.CreateTaskListItemDTO;
import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.dto.TaskListItemDTO;
import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.entity.TaskListItemEntity;
import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.repository.TaskListItemRepository;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.repository.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskListItemService {

    @Autowired
    private TaskListItemRepository taskListItemRepository;

    @Autowired
    private TaskListRepository taskListRepository;

    public List<TaskListItemEntity> findAllByTaskListId(Long id){
        return taskListItemRepository.findAllByTaskListId(id);
    }

    @Transactional
    public void delete(Long id) {
        taskListItemRepository.delete(id);
    }

    @Transactional
    public CreateTaskListItemDTO save(CreateTaskListItemDTO dto){
        TaskListItemEntity entity = new TaskListItemEntity();
        entity.setItemName(dto.getItemName());
        entity.setTaskList(taskListRepository.findById(dto.getTaskListId()));
        entity.setDone(false);
        entity=taskListItemRepository.save(entity);
        return entityToDTO(entity);
    }

    private CreateTaskListItemDTO entityToDTO(TaskListItemEntity entity){
        CreateTaskListItemDTO dto = new CreateTaskListItemDTO();
        dto.setTaskListId(entity.getTaskList().getId());
        dto.setItemName(entity.getItemName());
        dto.setDone(entity.isDone());
        dto.setId(entity.getId());
        return dto;
    }

    public List<TaskListItemDTO> getTaskListItemDTOByTaskListId(Long id){
        List<TaskListItemDTO> dtos = new ArrayList<>();
        for (TaskListItemEntity entity : taskListItemRepository.findAllByTaskListId(id)){
            dtos.add(entityToTaskListItemDTO(entity));
        }
        return dtos;
    }

    @Transactional
    public TaskListItemDTO update (TaskListItemDTO data){
        TaskListItemEntity entity = taskListItemRepository.findById(data.getTaskListItemId());
        if(data.getIsDone()!=null){
            entity.setDone(data.getIsDone());
        }
        else{
            entity.setDone(false);
        }
        entity.setItemName(data.getTaskListItemName());
        entity=taskListItemRepository.update(entity);
        return entityToTaskListItemDTO(entity);
    }

    private TaskListItemDTO entityToTaskListItemDTO(TaskListItemEntity entity){
        TaskListItemDTO dto = new TaskListItemDTO();
        dto.setIsDone(entity.isDone());
        dto.setTaskListItemId(entity.getId());
        dto.setTaskListItemName(entity.getItemName());
        return dto;
    }
}
















