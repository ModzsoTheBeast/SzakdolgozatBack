package hu.erpex.project.manager.projectmanager.mvc.tasklistitem.entity;

import hu.erpex.project.manager.projectmanager.mvc.core.CoreEntity;
import hu.erpex.project.manager.projectmanager.mvc.tasklist.entity.TaskListEntity;

import javax.persistence.*;

@Table(name = "task_list_item")
@Entity
public class TaskListItemEntity extends CoreEntity {
    @Column
    private String itemName;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(optional = false)
    private TaskListEntity taskList;


    @Column
    private boolean done;

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public TaskListEntity getTaskList() {
        return taskList;
    }

    public void setTaskList(TaskListEntity taskList) {
        this.taskList = taskList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
