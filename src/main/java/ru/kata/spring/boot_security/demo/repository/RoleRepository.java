package ru.kata.spring.boot_security.demo.repository;


import ru.kata.spring.boot_security.demo.entity.Role;
import java.util.Set;

public interface RoleRepository {
    Set<Role> getallrole();

    Role getRolebyId(long id);
    Role getByName(String rolename);

}

