package hu.erpex.project.manager.projectmanager.mvc.task.repository;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreRepository;
import hu.erpex.project.manager.projectmanager.mvc.list.entity.ListEntity;
import hu.erpex.project.manager.projectmanager.mvc.task.entity.TaskEntity;
import hu.erpex.project.manager.projectmanager.mvc.user.entity.UsersEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Repository
public class TaskRepository extends CoreRepository<TaskEntity> {

    @PersistenceContext
    EntityManager em;

    @Override
    protected Class<TaskEntity> getManagedClass() {
        return TaskEntity.class;
    }

    public List<TaskEntity> findAllByList(Long listId){
        return em.createQuery("select n from TaskEntity n where n.list.id = " +listId+ " order by n.positionInList asc ").getResultList();
    }

    public List<TaskEntity> findByListAndPosition(Long listId, Integer position){
        return em.createQuery("select n from TaskEntity n where n.list.id = " +listId+ " and n.positionInList = " +position).getResultList();
    }


    public List<TaskEntity> findAllByListId(Long id) {
        return em.createQuery("select n from TaskEntity n where n.list.id = " + id).getResultList();
    }


    public List<UsersEntity> findUserOnTask(Long taskId){
        List<UsersEntity> contributors = new ArrayList<>();
        for(UsersEntity entity: findById(taskId).getContributors()){
            contributors.add(entity);
        }
        return contributors;
    }
}
