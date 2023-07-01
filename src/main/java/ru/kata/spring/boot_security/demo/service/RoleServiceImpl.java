package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.entity.Role;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Set<Role> getallrole() {
        return roleRepository.getallrole();
    }

    @Override
    public Role getRolebyId(long id) {
        return roleRepository.getRolebyId(id);
    }

    @Override
    public Role getByName(String rolename) {
        return roleRepository.getByName(rolename);
    }
}