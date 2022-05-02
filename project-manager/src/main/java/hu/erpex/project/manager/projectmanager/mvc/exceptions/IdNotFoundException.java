package hu.erpex.project.manager.projectmanager.mvc.exceptions;
/*
Akkor dobjuk ezt a hibát, ha nem találjuk az ID alapján az elemet.
 */

public class IdNotFoundException extends RuntimeException {
    private Long id;

    public IdNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
