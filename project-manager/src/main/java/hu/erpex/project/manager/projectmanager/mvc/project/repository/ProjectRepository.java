package hu.erpex.project.manager.projectmanager.mvc.project.repository;

import hu.erpex.project.manager.projectmanager.mvc.project.entity.ProjectEntity;
import hu.erpex.project.manager.projectmanager.mvc.core.CoreRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ProjectRepository extends CoreRepository<ProjectEntity> {

    @Override
    protected Class<ProjectEntity> getManagedClass() {
        return ProjectEntity.class;
    }
}
