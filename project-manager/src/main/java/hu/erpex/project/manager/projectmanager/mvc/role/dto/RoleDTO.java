package hu.erpex.project.manager.projectmanager.mvc.role.dto;

import hu.erpex.project.manager.projectmanager.mvc.enums.RoleEnum;

public class RoleDTO {
        private Long userId;
        private Long projectId;
        private RoleEnum role;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }
}
