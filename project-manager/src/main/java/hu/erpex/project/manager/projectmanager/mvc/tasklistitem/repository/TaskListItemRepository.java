package hu.erpex.project.manager.projectmanager.mvc.tasklistitem.repository;

import hu.erpex.project.manager.projectmanager.mvc.tasklistitem.entity.TaskListItemEntity;
import hu.erpex.project.manager.projectmanager.mvc.core.CoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TaskListItemRepository extends CoreRepository<TaskListItemEntity> {

    @PersistenceContext
    EntityManager em;

    @Override
    protected Class<TaskListItemEntity> getManagedClass() {
        return TaskListItemEntity.class;
    }

    public List<TaskListItemEntity> findAllByTaskListId(Long id){
        return em.createQuery("select n from TaskListItemEntity n where n.taskList.id="+id).getResultList();
    }
}
