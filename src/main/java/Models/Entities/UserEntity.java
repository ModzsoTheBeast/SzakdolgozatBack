package Models.Entities;

import javax.persistence.*;

@Entity
@Table(name = "USER")
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name="USER_NAME", length=50, nullable=false, unique=true)
    private String user_name;

    @Column(name="PASSWORD", length=50, nullable=false, unique=false)
    private String password;

    @Column(name="EMAIL", length=250, nullable=false, unique=true)
    private String email;

    @Column(name="FULL_NAME", length=100, nullable=false, unique=false)
    private String full_name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

}
