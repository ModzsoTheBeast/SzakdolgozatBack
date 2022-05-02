package hu.erpex.project.manager.projectmanager.mvc.role.entity;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreEntity;
import hu.erpex.project.manager.projectmanager.mvc.project.entity.ProjectEntity;
import hu.erpex.project.manager.projectmanager.mvc.enums.RoleEnum;
import hu.erpex.project.manager.projectmanager.mvc.user.entity.UsersEntity;

import javax.persistence.*;

@Table
@Entity
public class RoleEntity extends CoreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private UsersEntity user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "project")
    private ProjectEntity project;

    @Column
    private RoleEnum role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    public ProjectEntity getProject() {
        return project;
    }

    public void setProject(ProjectEntity project) {
        this.project = project;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
