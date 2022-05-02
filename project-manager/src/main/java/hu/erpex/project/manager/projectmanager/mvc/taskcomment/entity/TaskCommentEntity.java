package hu.erpex.project.manager.projectmanager.mvc.taskcomment.entity;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreEntity;
import hu.erpex.project.manager.projectmanager.mvc.task.entity.TaskEntity;
import hu.erpex.project.manager.projectmanager.mvc.user.entity.UsersEntity;

import javax.persistence.*;
import java.util.Date;
@Table(name = "comment")
@Entity
public class TaskCommentEntity extends CoreEntity {

    @Column
    private String commentName;
    @Column
    private Date commentDate;

    @ManyToOne(optional = false)
    private UsersEntity createdBy;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private TaskEntity task;

    public TaskEntity getTask() {
        return task;
    }

    public void setTask(TaskEntity task) {
        this.task = task;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentName() {
        return commentName;
    }

    public void setCommentName(String commentName) {
        this.commentName = commentName;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }


    public UsersEntity  getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UsersEntity createdBy) {
        this.createdBy = createdBy;
    }

}
