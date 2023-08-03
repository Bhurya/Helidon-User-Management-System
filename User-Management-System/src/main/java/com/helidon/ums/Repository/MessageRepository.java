package com.helidon.ums.Repository;

import com.helidon.ums.Dto.UpdateMessage;
import com.helidon.ums.Dto.UserDetails;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;

import java.util.Optional;

@ApplicationScoped
public class MessageRepository {

    @PersistenceContext(unitName = "u1")
    private EntityManager entityManager;

    public StoredProcedureQuery updateMessage(UpdateMessage message) {
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("updateMessage", String.class);
        //StoredProcedureQuery query = entityManager.createStoredProcedureQuery("findLastNameLike", String.class);

        query.registerStoredProcedureParameter("user_p", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("msgid_p", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("msg_p", String.class, ParameterMode.IN);

        query.setParameter("user_p", message.getUserId());
        query.setParameter("msgid_p", message.getMessageId());
        query.setParameter("msg_p", message.getMessage());
        query.execute();
        return (StoredProcedureQuery) query.getSingleResult();
    }

       /* return Optional.of(query)
                .map(q -> {
                    UserDetails user = new UserDetails();
                    user.setUserid((Integer) query.getOutputParameterValue("user_id"));
                    return (StoredProcedureQuery) query.getSingleResult();
                }

;}*/
}
