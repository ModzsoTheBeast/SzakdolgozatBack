package hu.erpex.project.manager.projectmanager.mvc.tasklist.repository;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreRepository;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.entity.TaskListEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class TaskListRepository extends CoreRepository<TaskListEntity> {

    @PersistenceContext
    EntityManager em;

    @Override
    protected Class<TaskListEntity> getManagedClass() {
        return TaskListEntity.class;
    }

    public List<TaskListEntity> findAllByTaskId(Long id){
        return em.createQuery("select n from TaskListEntity n where n.task.id="+id).getResultList();
    }

}
