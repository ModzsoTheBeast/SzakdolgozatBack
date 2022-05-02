package hu.erpex.project.manager.projectmanager.mvc.project.service;

import hu.erpex.project.manager.projectmanager.mvc.email.dto.EmailSendDTO;
import hu.erpex.project.manager.projectmanager.mvc.list.entity.ListEntity;
import hu.erpex.project.manager.projectmanager.mvc.list.repository.ListRepository;
import hu.erpex.project.manager.projectmanager.mvc.list.service.ListService;
import hu.erpex.project.manager.projectmanager.mvc.project.entity.ProjectEntity;
import hu.erpex.project.manager.projectmanager.mvc.role.entity.RoleEntity;
import hu.erpex.project.manager.projectmanager.mvc.role.repository.RoleRepository;
import hu.erpex.project.manager.projectmanager.mvc.project.repository.ProjectRepository;
import hu.erpex.project.manager.projectmanager.mvc.task.entity.TaskEntity;
import hu.erpex.project.manager.projectmanager.mvc.enums.RoleEnum;
import hu.erpex.project.manager.projectmanager.mvc.project.dto.CreateProjectDTO;
import hu.erpex.project.manager.projectmanager.mvc.list.dto.ListDTOforDiagram;
import hu.erpex.project.manager.projectmanager.mvc.project.dto.ProjectDTO;
import hu.erpex.project.manager.projectmanager.mvc.task.repository.TaskRepository;
import hu.erpex.project.manager.projectmanager.mvc.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ListService listService;

    public List<ProjectDTO> findAllByUserId(Long id){
        try
        {



            List<ProjectDTO> lists = new ArrayList<>();


            for(RoleEntity role : roleRepository.findAll()){
                if(role.getUser().getId().equals(id)){
                    Long projectId = role.getProject().getId();
                    lists.add(entityToProjectDTO(projectRepository.findById(projectId)));
                }
            }
            return lists;

        }catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return null;
    }

    private ProjectDTO entityToProjectDTO(ProjectEntity entity){
        ProjectDTO dto = new ProjectDTO();
        dto.setProjectName(entity.getProjectName());
        dto.setProjectId(entity.getId());
        List<ListDTOforDiagram> lists = new ArrayList<>();
        for(ListEntity list: listRepository.findAll()){
            if(Objects.equals(list.getProject().getId(), entity.getId()))
                lists.add(listEntityToListDTO(list));
        }
        dto.setLists(lists);
        return dto;

    }

    private ListDTOforDiagram listEntityToListDTO(ListEntity entity){
        ListDTOforDiagram list = new ListDTOforDiagram();
        list.setListName(entity.getListName());
        Integer tasksNumber = 0;
        for(TaskEntity task: taskRepository.findAll()){
            if(task.getList().getId() == entity.getId()) tasksNumber++;
        }
        list.setTaskNumber(tasksNumber);
        return list;
    }


    @Transactional
    public CreateProjectDTO save(CreateProjectDTO dto){

        ProjectEntity entity = new ProjectEntity();

        entity.setProjectName(dto.getProjectName());
        entity.setCreatedOn(new Date());
        entity.setCreatedBy(usersRepository.findById(dto.getUserId()));
        entity = projectRepository.save(entity);
        dto.setProjectId(entity.getId());
        RoleEntity role = new RoleEntity();
        role.setRole(RoleEnum.valueOf("PRODUCT_OWNER"));
        role.setProject(projectRepository.findById(dto.getProjectId()));
        role.setUser(usersRepository.findById(dto.getUserId()));
        role= roleRepository.save(role);
        return dto;
    }

    @Transactional
    public void delete(Long projectid) {
        for(ListEntity list: listService.findAllByProjectId(projectid)){
            listService.delete(list.getId());
        }
        projectRepository.delete(projectid);
    }


}
