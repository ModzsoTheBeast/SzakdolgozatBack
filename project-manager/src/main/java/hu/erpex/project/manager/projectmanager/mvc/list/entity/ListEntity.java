package hu.erpex.project.manager.projectmanager.mvc.list.entity;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreEntity;
import hu.erpex.project.manager.projectmanager.mvc.project.entity.ProjectEntity;

import javax.persistence.*;
@Table(name = "list")
@Entity
public class ListEntity extends CoreEntity {

    @Column
    private String listName;

    @ManyToOne(optional = false)
    private ProjectEntity project;

    @Column(nullable = false)
    private Integer positionInProject;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    public Integer getPositionInProject() {
        return positionInProject;
    }

    public void setPositionInProject(Integer positionInProject) {
        this.positionInProject = positionInProject;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

}
