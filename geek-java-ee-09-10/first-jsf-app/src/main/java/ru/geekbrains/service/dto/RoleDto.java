package ru.geekbrains.service.dto;

import ru.geekbrains.persist.Role;

import java.io.Serializable;
import java.util.Objects;

public class RoleDto implements Serializable {

    private long id;

    private String name;

    public RoleDto() {
    }

    public RoleDto(Role r) {
        this.id = r.getId();
        this.name = r.getName();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return name.equals(roleDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return name;
    }
}
