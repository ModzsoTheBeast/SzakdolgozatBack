package hu.erpex.project.manager.projectmanager.mvc.user.dto;

import hu.erpex.project.manager.projectmanager.mvc.enums.RoleEnum;

public class UsersInProjectDTO {
    private Long userId;
    private String userEmail;
    private String userName;
    private RoleEnum role;

    public RoleEnum getRole() {
        return role;
    }

    public void setRole(RoleEnum role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
