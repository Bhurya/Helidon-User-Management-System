package com.helidon.ums.Service;

import com.helidon.ums.Dto.MessageDto;
import com.helidon.ums.Dto.UpdateMessage;
import com.helidon.ums.Entity.Message;
import com.helidon.ums.Entity.User;
import com.helidon.ums.Exception.CustomGlobalException;
import com.helidon.ums.Exception.UserNotFoundException;
import com.helidon.ums.Repository.MessageRepository;
import com.helidon.ums.Response.ApiResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.Optional;

@ApplicationScoped
public class MessageService {

    @Inject
    UserService userService;

    @PersistenceContext(unitName = "u1")
    private EntityManager entityManager;

    @Inject
    private MessageRepository messageRepository;

    private final Logger log = Logger.getLogger(UserService.class.getName());


    @Transactional
    public Message createMessage(MessageDto messageDto) {
        User user = userService.getUserById(messageDto.getUserId());
        if(user == null){
            throw new UserNotFoundException("User not found");
        }
        log.info("user: " + user);
        return Optional.of(user)
                .map(
                        user1 -> {
                            Message messages = new Message();
                            messages.setMessage(messageDto.getMessage());
                            messages.setUser(user);
                            entityManager.persist(messages);
                            return messages;
                        }).orElseThrow(() -> {
                            throw new UserNotFoundException("Something went wrong with the Message");
                        }
                );
    }


    public ApiResponse updateMessage(UpdateMessage updateMessage) {
        User user = userService.getUserById(updateMessage.getUserId());
        if(user == null){
            throw new UserNotFoundException("User not found");
        }
        log.info("Procedure update query executed");
        StoredProcedureQuery query = messageRepository.updateMessage(updateMessage);
        log.info("Procedure update data: " + query.getSingleResult());
        if (query.getSingleResult().equals(0)){
            log.info("Error updating message");
            throw new CustomGlobalException("Message not found with user");
        }else {
            log.info(" Return Procedure update message");
            return new ApiResponse(
                    true,"Success updating message with user",query.getSingleResult(),(long) Response.Status.ACCEPTED.getStatusCode());
        }
    }
}
