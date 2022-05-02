package hu.erpex.project.manager.projectmanager.mvc.role.repository;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreRepository;
import hu.erpex.project.manager.projectmanager.mvc.role.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class RoleRepository extends CoreRepository<RoleEntity> {

    @PersistenceContext
    EntityManager em;


    @Override
    protected Class<RoleEntity> getManagedClass() {
        return RoleEntity.class;
    }


    public RoleEntity findByIds(Long projectId, Long userId){
        RoleEntity entity = new RoleEntity();
        for(RoleEntity role : findAll()){
            if(role.getUser().getId().equals(userId) && role.getProject().getId().equals(projectId)){

                entity = role;
            }
        }
        return entity;
    }



    public List<RoleEntity> findAllByProjectId(Long id){
        return em.createQuery("select n from RoleEntity n where n.project.id = :value").setParameter("value", id).getResultList();
    }
}
