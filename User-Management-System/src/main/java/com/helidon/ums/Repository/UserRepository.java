package com.helidon.ums.Repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

@ApplicationScoped
//@NamedStoredProcedureQuery(
//        name = "findLastNameLike",
//        procedureName = "findLastNameLike",
//       // resultSetMappings = {"getFirstNameLike"},
//        parameters = {
//                @StoredProcedureParameter(name = "lastname_p", type = String.class, mode = ParameterMode.IN)
//        }
//)

public class UserRepository {

    @PersistenceContext(unitName = "u1")
    private EntityManager entityManager;


    public StoredProcedureQuery findByLikeLastName(String lastName) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("findLastNameLike", "getFirstNameLike");
        //StoredProcedureQuery query = entityManager.createStoredProcedureQuery("findLastNameLike", String.class);

        query.registerStoredProcedureParameter("lastname_p", String.class, ParameterMode.IN);
        query.setParameter("lastname_p", lastName);
        query.execute();
        return query;
    }

}
