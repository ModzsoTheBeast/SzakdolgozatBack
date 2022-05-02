package hu.erpex.project.manager.projectmanager.mvc.list.repository;

import hu.erpex.project.manager.projectmanager.mvc.list.entity.ListEntity;
import hu.erpex.project.manager.projectmanager.mvc.core.CoreRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ListRepository extends CoreRepository<ListEntity>{

    @PersistenceContext
    EntityManager em;

    @Override
    protected Class<ListEntity> getManagedClass() {
        return ListEntity.class;
    }

    public ListEntity findByProjectAndPosition(Long projectId, Integer position){
        ListEntity entity = new ListEntity();
        for(ListEntity list : findAll()){
            if(list.getPositionInProject().equals(position) && list.getProject().getId().equals(projectId)){
                entity=list;
            }
        }
        return entity;
    }

    public List<ListEntity> findAllByProject(Long projectId){
        return em.createQuery("select n from ListEntity n where n.project.id = " +projectId+ " order by n.positionInProject asc ").getResultList();
    }
}
