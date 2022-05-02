package hu.erpex.project.manager.projectmanager.mvc.project.entity;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreEntity;
import hu.erpex.project.manager.projectmanager.mvc.user.entity.UsersEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "project")
@Entity
public class ProjectEntity extends CoreEntity {

    @Column
    private String ProjectName;

    @Column
    private Date CreatedOn;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private UsersEntity createdBy;

    public UsersEntity getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UsersEntity createdBy) {
        this.createdBy = createdBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public Date getCreatedOn() {
        return CreatedOn;
    }

    public void setCreatedOn(Date createdOn) {
        CreatedOn = createdOn;
    }

}
