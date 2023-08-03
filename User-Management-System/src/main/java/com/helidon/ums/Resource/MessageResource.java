package com.helidon.ums.Resource;

import com.helidon.ums.Dto.MessageDto;
import com.helidon.ums.Dto.UpdateMessage;
import com.helidon.ums.Entity.Message;
import com.helidon.ums.Response.ApiResponse;
import com.helidon.ums.Service.MessageService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("messages")
public class MessageResource {

    @Inject
    MessageService messageService;

    @POST
    @Path("createMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response createMessageResource(MessageDto messageDto) {
        Message messages = messageService.createMessage(messageDto);

       /* if (messages != null) {
            return Response.noContent().entity("Something went wrong with the message").build();
        }
*/
        return Response.accepted(messages)
                .status(Response.Status.CREATED)
                .build();
    }

    @POST
    @Path("updateMessage")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response updateMessageResource(UpdateMessage updateMessage) {
        ApiResponse messages = messageService.updateMessage(updateMessage);

        return Response.accepted(messages)
                .status(messages.getCode().intValue())
                .build();
    }


}
