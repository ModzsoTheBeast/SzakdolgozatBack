package hu.erpex.project.manager.projectmanager.mvc.role.service;

import hu.erpex.project.manager.projectmanager.mvc.enums.RoleEnum;
import hu.erpex.project.manager.projectmanager.mvc.project.repository.ProjectRepository;
import hu.erpex.project.manager.projectmanager.mvc.role.entity.RoleEntity;
import hu.erpex.project.manager.projectmanager.mvc.role.dto.RoleDTO;
import hu.erpex.project.manager.projectmanager.mvc.role.repository.RoleRepository;
import hu.erpex.project.manager.projectmanager.mvc.user.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Transactional
    public void deleteByIds(RoleDTO data){
        roleRepository.delete(roleRepository.findByIds(data.getProjectId(), data.getUserId()).getId());
    }

    public RoleDTO findRoleDTOByIds(RoleDTO data){
        return entityToDTO(roleRepository.findByIds(data.getProjectId(), data.getUserId()));
    }

    public RoleEntity findRoleEntityByIds(RoleDTO data){
        return roleRepository.findByIds(data.getProjectId(), data.getUserId());
    }

    @Transactional
    public RoleDTO update(RoleDTO dto){
        RoleEntity entity = roleRepository.findByIds(dto.getProjectId(), dto.getUserId());
        if(dto.getRole()!=null){
            entity.setRole(dto.getRole());
        }
        entity = roleRepository.update(entity);
        return entityToDTO(entity);
    }


    private RoleDTO entityToDTO(RoleEntity entity){
        RoleDTO dto = new RoleDTO();
        dto.setRole(entity.getRole());
        dto.setProjectId(entity.getProject().getId());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }

    @Transactional
    public void deleteRoleByProjectId(Long projectId){
        for (RoleEntity role: roleRepository.findAll()){
            if(role.getProject().getId().equals(projectId)){
                roleRepository.delete(role.getId());
            }
        }
    }

    @Transactional
    public RoleDTO create(RoleEnum role, Long projectId, Long userId){
        RoleEntity entity = new RoleEntity();
        if(role!=null){
            entity.setRole(role);
        }
        else{
            entity.setRole(RoleEnum.DEVELOPER);
        }
        entity.setProject(projectRepository.findById(projectId));
        entity.setUser(usersRepository.findById(userId));
        entity = roleRepository.save(entity);
        return entityToDTO(entity);
    }
}
