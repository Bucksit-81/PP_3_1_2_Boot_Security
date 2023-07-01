package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.entity.Role;
import java.util.Set;

public interface RoleService {
    Set<Role> getallrole();

    Role getRolebyId(long id);

    Role getByName(String rolename);
}