package com.example.szakdolgozatBack.Models.Entities;

import javax.persistence.*;
import java.util.Set;

@Table(name = "PROJECT")
@Entity
public class ProjectEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PROJECT_ID")
    private Long id;

    @Column(name="PROJECT_NAME", length=50, nullable=false, unique=true)
    private String project_name;

    @Column(name="PROJECT_PERCENT", nullable=false)
    private int project_percent;

    @ManyToOne
    @JoinColumn(name="USER_ID")
    private UserEntity user;

    public Set<TaskEntity> getTask() {
        return task;
    }

    public void setTask(Set<TaskEntity> task) {
        this.task = task;
    }

    @OneToMany(mappedBy = "project")
    private Set<TaskEntity> task;

    //@Column(name="OWNER_ID", nullable = true, unique = true)
    //private Long owner_id;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public int getProject_percent() {
        return project_percent;
    }

    public void setProject_percent(int project_percent) {
        this.project_percent = project_percent;
    }

    //public Long getOwner_id() {
    //    return owner_id;
    //}

    //public void setOwner_id(Long owner_id) {
    //    this.owner_id = owner_id;
    //}


}
