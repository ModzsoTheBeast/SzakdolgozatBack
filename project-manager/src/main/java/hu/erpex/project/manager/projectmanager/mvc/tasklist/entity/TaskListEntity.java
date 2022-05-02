package hu.erpex.project.manager.projectmanager.mvc.tasklist.entity;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreEntity;
import hu.erpex.project.manager.projectmanager.mvc.task.entity.TaskEntity;

import javax.persistence.*;

@Table(name = "task_list")
@Entity
public class TaskListEntity extends CoreEntity {
    @Column
    private String taskListName;

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

    public String getTaskListName() {
        return taskListName;
    }

    public void setTaskListName(String taskListName) {
        this.taskListName = taskListName;
    }

}
