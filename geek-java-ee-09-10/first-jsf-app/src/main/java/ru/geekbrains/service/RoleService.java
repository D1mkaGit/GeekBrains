package ru.geekbrains.service;

import ru.geekbrains.persist.RoleRepository;
import ru.geekbrains.service.dto.RoleDto;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleService implements Serializable {

    @Inject
    private RoleRepository roleRepository;

    @TransactionAttribute
    public List<RoleDto> getAllRoles() {
        return roleRepository.getAllRoles().stream()
                .map(RoleDto::new)
                .collect(Collectors.toList());
    }
}
