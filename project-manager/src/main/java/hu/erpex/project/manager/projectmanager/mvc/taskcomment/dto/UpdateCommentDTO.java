package hu.erpex.project.manager.projectmanager.mvc.taskcomment.dto;

public class UpdateCommentDTO {
    private String comment;
    private Long taskCommentId;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getTaskCommentId() {
        return taskCommentId;
    }

    public void setTaskCommentId(Long taskCommentId) {
        this.taskCommentId = taskCommentId;
    }
}
