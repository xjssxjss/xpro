package com.spro.entity.au;

public class RoleMenu {
    private Integer id;

    private Integer roleIs;

    private Integer menuIs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleIs() {
        return roleIs;
    }

    public void setRoleIs(Integer roleIs) {
        this.roleIs = roleIs;
    }

    public Integer getMenuIs() {
        return menuIs;
    }

    public void setMenuIs(Integer menuIs) {
        this.menuIs = menuIs;
    }

    @Override
    public String toString() {
        return "RoleMenu{" +
                "id=" + id +
                ", roleIs=" + roleIs +
                ", menuIs=" + menuIs +
                '}';
    }
}