package com.example.szakdolgozatBack.Authentication.Security;

public enum ApplicationUserPermission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    CAN_COMMENT("can:comment");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
