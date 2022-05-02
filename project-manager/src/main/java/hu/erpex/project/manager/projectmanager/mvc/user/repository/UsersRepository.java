package hu.erpex.project.manager.projectmanager.mvc.user.repository;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreRepository;
import hu.erpex.project.manager.projectmanager.mvc.user.dto.UsersOnTaskDTO;
import hu.erpex.project.manager.projectmanager.mvc.user.entity.UsersEntity;
import hu.erpex.project.manager.projectmanager.mvc.exceptions.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class UsersRepository extends CoreRepository<UsersEntity> {

    @PersistenceContext
    EntityManager em;

    public UsersEntity findByUsername(String username){
        UsersEntity user = new UsersEntity();
        for(UsersEntity entity : findAll()){
            if (entity.getUserName().equals(username)){
                user = entity;
            }
        }

        return user;
    }
    public UsersEntity findByEmail(String email){
        UsersEntity user = new UsersEntity();
        for(UsersEntity entity : findAll()){
            if (entity.getUserEmail().equals(email)){
                return entity;
            }
        }
        return user;
    }


    @Override
    protected Class<UsersEntity> getManagedClass() {
        return UsersEntity.class;
    }
}
