/*
package com.helidon.ums.Exception;

import com.helidon.ums.Response.ApiResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EmailExceptionHandler implements ExceptionMapper<EmailAlreadyFoundException> {
    */
/*@Override
    public Response toResponse(EmailAlreadyFoundException exception) {
        return Response.status(Response.Status.BAD_GATEWAY)
                .entity(exception.getMessage())
                .build();
    }*//*

    @Override
    public Response toResponse(EmailAlreadyFoundException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(exception.getMessage());
        apiResponse.setStatus(Response.Status.CONFLICT);
        apiResponse.setObject(null);
        return Response.accepted(apiResponse).status(Response.Status.CONFLICT).build();
    }

}*/
