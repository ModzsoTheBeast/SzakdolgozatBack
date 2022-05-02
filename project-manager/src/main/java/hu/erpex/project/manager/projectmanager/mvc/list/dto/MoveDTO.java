package hu.erpex.project.manager.projectmanager.mvc.list.dto;

public class MoveDTO {
    private Long projectId;
    private Integer listPosition;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Integer getListPosition() {
        return listPosition;
    }

    public void setListPosition(Integer listPosition) {
        this.listPosition = listPosition;
    }
}
