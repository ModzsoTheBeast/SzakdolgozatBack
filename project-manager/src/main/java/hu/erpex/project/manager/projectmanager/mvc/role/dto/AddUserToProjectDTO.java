package hu.erpex.project.manager.projectmanager.mvc.role.dto;

import hu.erpex.project.manager.projectmanager.mvc.enums.RoleEnum;

public class AddUserToProjectDTO {
    private String userEmail;
    private Long projectId;
    private RoleEnum role;

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
