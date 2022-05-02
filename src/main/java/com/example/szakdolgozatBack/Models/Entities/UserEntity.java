package com.example.szakdolgozatBack.Models.Entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Table(name = "USER")
@Entity
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "USER_ID")
    private Long user_id;

    @Column(name="USER_NAME", length=50, nullable=false, unique=true)
    private String user_name;

    @Column(name="PASSWORD", length=300, nullable=false, unique=false)
    private String password;

    @Column(name="EMAIL", length=250, nullable=false, unique=true)
    private String email;

    @Column(name="FULL_NAME", length=100, nullable=false, unique=false)
    private String full_name;

    @OneToMany(mappedBy = "user")
    private Set<ProjectEntity> project;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Set<ProjectEntity> getProject() {
        return project;
    }

    public void setProject(Set<ProjectEntity> project) {
        this.project = project;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public Set<? extends GrantedAuthority> getGrantedAuthorities() {
        return grantedAuthorities;
    }

    public void setGrantedAuthorities(Set<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public Long getId() {
        return user_id;
    }

    public void setId(Long id) {
        this.user_id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return getUser_name();
    }

    @Column(name="IS_ACCOUNT_NON_EXPIRED")
    private boolean isAccountNonExpired;
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Column(name="IS_ACCOUNT_NON_LOCKED")
    private boolean isAccountNonLocked;
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Column(name="IS_CREDENTIALS_NON_EXPIRED")
    private boolean isCredentialsNonExpired;
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Column(name="IS_ENABLED")
    private boolean isEnabled;
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public void setGrantedAuthorities(){

    }

    @Transient
    private Set<? extends GrantedAuthority> grantedAuthorities;

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

    public void setFirstname(String full_name) {
        this.full_name = full_name;
    }

}
