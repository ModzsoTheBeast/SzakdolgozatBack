package hu.erpex.project.manager.projectmanager.mvc.project.dto;

import hu.erpex.project.manager.projectmanager.mvc.list.dto.ListDTOforDiagram;

import java.util.List;

public class ProjectDTO {
    private Long projectId;
    private String ProjectName;
    private List<ListDTOforDiagram> lists;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public List<ListDTOforDiagram> getLists() {
        return lists;
    }

    public void setLists(List<ListDTOforDiagram> lists) {
        this.lists = lists;
    }
}
