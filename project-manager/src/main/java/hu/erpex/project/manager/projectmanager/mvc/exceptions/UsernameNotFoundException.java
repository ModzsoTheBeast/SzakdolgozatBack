package hu.erpex.project.manager.projectmanager.mvc.exceptions;

public class UsernameNotFoundException extends RuntimeException{
    private String username;

    public UsernameNotFoundException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
