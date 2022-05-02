package hu.erpex.project.manager.projectmanager.mvc.list.service;

import hu.erpex.project.manager.projectmanager.mvc.list.dto.*;
import hu.erpex.project.manager.projectmanager.mvc.list.entity.ListEntity;
import hu.erpex.project.manager.projectmanager.mvc.task.entity.TaskEntity;
import hu.erpex.project.manager.projectmanager.mvc.list.repository.ListRepository;
import hu.erpex.project.manager.projectmanager.mvc.project.repository.ProjectRepository;
import hu.erpex.project.manager.projectmanager.mvc.task.repository.TaskRepository;
import hu.erpex.project.manager.projectmanager.mvc.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListService {

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskService taskService;

    @Transactional
    public UpdateListDTO updateList(UpdateListDTO dto){
        ListEntity entity = listRepository.findById(dto.getListId());
        entity.setListName(dto.getListName());
        entity=listRepository.update(entity);
        dto.setListName(entity.getListName());
        return dto;
    }


    public ListEntity findEntityByProjectAndPosition(Long projectId, Integer position){
        System.out.println(listRepository.findByProjectAndPosition(projectId, position));
        return listRepository.findByProjectAndPosition(projectId, position);
    }

    public List<DetailedListDTO> findByProject(Long projectId){
        List<DetailedListDTO> lists = new ArrayList<>();
        for(ListEntity list : listRepository.findAllByProject(projectId)){
            DetailedListDTO detailedList = new DetailedListDTO();
            detailedList.setListId(list.getId());
            detailedList.setListName(list.getListName());
            detailedList.setTasks(taskService.findByList(list.getId()));
            detailedList.setListPosition(list.getPositionInProject());
            lists.add(detailedList);
        }
        return lists;
    }

    @Transactional
    public ListEntity setPosition(ListEntity entity, Integer position){
        entity.setPositionInProject(position);
        entity = listRepository.update(entity);
        return entity;
    }




    @Transactional
    public CreateListDTO createList(CreateListDTO dto){
        ListEntity list = new ListEntity();
        list.setListName(dto.getListName());
        list.setProject(projectRepository.findById(dto.getProjectId()));
        list.setPositionInProject(dto.getPosition());
        list = listRepository.save(list);
        return entityToCreateListDTO(list);
    }

    private CreateListDTO entityToCreateListDTO(ListEntity entity){
        CreateListDTO dto = new CreateListDTO();
        dto.setPosition(entity.getPositionInProject());
        dto.setProjectId(entity.getProject().getId());
        dto.setListName(entity.getListName());
        System.out.println(entity.getProject().getId());
        return dto;
    }

    @Transactional
    public MoveDTO moveRight(MoveDTO dto) {
        ListEntity list = listRepository.findByProjectAndPosition(dto.getProjectId(), dto.getListPosition());
        ListEntity list2 = listRepository.findByProjectAndPosition(dto.getProjectId(), dto.getListPosition() + 1);
        list2.setPositionInProject(-1);
        list2 = listRepository.update(list2);
        list.setPositionInProject(dto.getListPosition() + 1);
        list = listRepository.update(list);
        list2.setPositionInProject(dto.getListPosition());
        list2 = listRepository.update(list2);
        return dto;
    }

    @Transactional
    public MoveDTO moveLeft(MoveDTO dto) {
        ListEntity list = listRepository.findByProjectAndPosition(dto.getProjectId(), dto.getListPosition());
        ListEntity list2 = listRepository.findByProjectAndPosition(dto.getProjectId(), dto.getListPosition() - 1);
        list2.setPositionInProject(-1);
        list2 = listRepository.update(list2);
        list.setPositionInProject(dto.getListPosition() - 1);
        list = listRepository.update(list);
        list2.setPositionInProject(dto.getListPosition());
        list2 = listRepository.update(list2);
        return dto;
    }

    public ListEntity findByID(Long listId){
        return listRepository.findById(listId);
    }

    public  List<ListEntity> findAllByProjectId(Long id){
        return listRepository.findAllByProject(id);
    }

    @Transactional
    public void delete(Long id){
        for (TaskEntity task: taskService.findAllByListId(id)){
            taskService.delete(task.getId());
        }

        listRepository.delete(id);
    }

    @Transactional
    public void moveLists(int pos, Long projectId){

        for(ListEntity list : findAllByProjectId(projectId)){
            if(list.getPositionInProject().equals(pos+1)){
                list.setPositionInProject(pos);
                listRepository.update(list);
                pos++;
            }
        }
    }
}
