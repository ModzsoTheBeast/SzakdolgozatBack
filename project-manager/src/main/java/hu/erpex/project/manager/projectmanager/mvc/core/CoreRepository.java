package hu.erpex.project.manager.projectmanager.mvc.core;

import hu.erpex.project.manager.projectmanager.mvc.exceptions.IdNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public abstract class CoreRepository<T extends CoreEntity>{

    @PersistenceContext
    private EntityManager em;


    public List<T> findAll(){
        return em.createQuery("select n from " + getManagedClass().getSimpleName() + " n", getManagedClass()).getResultList();
    }

    public T save(T entity){
        em.persist(entity);
        return entity;
    }

    public T update(T data){
        return em.merge(data);
    }

    public void delete(Long id){
        em.remove(findById(id));
    }

    public T findById(Long id){
        T entity= (T) em.find(getManagedClass(), id);
        if(entity==null){
            throw new IdNotFoundException(id);
        }
        return entity;
    }

    protected abstract Class<T> getManagedClass();
}
