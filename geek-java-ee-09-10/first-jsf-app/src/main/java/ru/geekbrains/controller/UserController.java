package ru.geekbrains.controller;

import ru.geekbrains.service.RoleService;
import ru.geekbrains.service.UserService;
import ru.geekbrains.service.dto.RoleDto;
import ru.geekbrains.service.dto.UserDto;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class UserController implements Serializable {

    @EJB
    private UserService userService;

    @EJB
    private RoleService roleService;

    @Inject
    private HttpSession session;

    private UserDto user;

    private List<RoleDto> roles;

    private List<UserDto> users;

    public void preLoad() {
        this.roles = roleService.getAllRoles();
        this.users = userService.getAllUsers();
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<UserDto> getAllUsers() {
        return users;
    }

    public String editUser(UserDto user) {
        this.user = user;
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserDto user) {
        userService.delete(user.getId());
    }

    public String createUser() {
        this.user = new UserDto();
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public String saveUser() {
        userService.saveOrUpdate(this.user);
        return "/admin/user.xhtml?faces-redirect=true";
    }

    public List<RoleDto> getAllRoles() {
        return roles;
    }

    public String logout() {
        session.invalidate();
        return "/product.xhtml?faces-redirect=true";
    }
}
