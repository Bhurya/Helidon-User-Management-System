package com.helidon.ums.Service;

import com.helidon.ums.Entity.Role;
import com.helidon.ums.Exception.UserNotFoundException;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.jboss.logging.Logger;

import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RoleService {

    @PersistenceContext(unitName = "u1")
    private EntityManager entityManager;


    private final Logger log = Logger.getLogger(RoleService.class.getName());

    @Transactional
    public Role saveRole(Role role) {
        Role saveRole = new Role();
        log.info("save role : " + role.getRole());
        saveRole.setRole(role.getRole());
        entityManager.persist(saveRole);
        return saveRole;
    }

    public Role getRoleByName(String roleName) {
        Role role = entityManager.createNamedQuery("findByRole", Role.class)
                .setParameter("role", roleName.toUpperCase()).getSingleResult();
        if (role == null) {
            throw new UserNotFoundException("ROLE " + roleName + " not found");
        }
        return role;
    }

    public Role getRole(Long id) {
       /* Role role = entityManager.createNamedQuery("findById", Role.class)
                .setParameter("id", id).getSingleResult();
       */
        Role role = entityManager.find(Role.class, id);
        if (role == null) {
            log.info("Role: " + role.getRole());
            throw new UserNotFoundException("ROLE " + id + " not found");
        }
        return role;
    }



}
