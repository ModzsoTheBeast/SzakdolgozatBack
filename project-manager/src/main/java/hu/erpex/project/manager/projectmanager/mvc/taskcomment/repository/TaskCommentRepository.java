package hu.erpex.project.manager.projectmanager.mvc.taskcomment.repository;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreRepository;
import hu.erpex.project.manager.projectmanager.mvc.taskcomment.entity.TaskCommentEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class TaskCommentRepository extends CoreRepository<TaskCommentEntity> {

    @PersistenceContext
    EntityManager em;

    @Override
    protected Class<TaskCommentEntity> getManagedClass() {
        return TaskCommentEntity.class;
    }

    public List<TaskCommentEntity> findAllByTaskId(Long id) {
        return em.createQuery("select n from TaskCommentEntity n where n.task.id = "+id).getResultList();
    }
}
