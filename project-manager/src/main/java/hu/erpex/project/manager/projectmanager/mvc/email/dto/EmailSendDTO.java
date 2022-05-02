package hu.erpex.project.manager.projectmanager.mvc.email.dto;

public class EmailSendDTO {
    private Long projectId;
    private String toEmail;

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

}
