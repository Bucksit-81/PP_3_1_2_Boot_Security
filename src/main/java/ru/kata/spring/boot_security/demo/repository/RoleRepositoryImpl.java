package ru.kata.spring.boot_security.demo.repository;

import ru.kata.spring.boot_security.demo.entity.Role;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl implements RoleRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getallrole() {
        return entityManager.createQuery("from Role", Role.class).getResultStream().collect(Collectors.toSet());
    }

    @Override
    public Role getRolebyId(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role getByName(String rolename) {
        try {
            TypedQuery<Role> role = (TypedQuery<Role>) entityManager.createQuery(
                            "from Role r where r.role = :name").
                    setParameter("name", rolename);
            return role.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
}

