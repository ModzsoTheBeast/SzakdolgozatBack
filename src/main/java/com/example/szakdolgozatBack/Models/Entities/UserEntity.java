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
    private Long id;

    @Column(name="USER_NAME", length=50, nullable=false, unique=true)
    private String user_name;

    @Column(name="PASSWORD", length=300, nullable=false, unique=false)
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

    @Column(name="IS_ACCOUNT_NON_EXPIRED", columnDefinition="default 'true'")
    private boolean isAccountNonExpired;
    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Column(name="IS_ACCOUNT_NON_LOCKED", columnDefinition="default 'true'")
    private boolean isAccountNonLocked;
    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Column(name="IS_CREDENTIALS_NON_EXPIRED", columnDefinition="default 'true'")
    private boolean isCredentialsNonExpired;
    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Column(name="IS_ENABLED", columnDefinition="default 'true'")
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

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

}
