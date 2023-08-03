/*
package com.helidon.ums.Exception;

import com.helidon.ums.Response.ApiResponse;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CustomExceptionHandler implements ExceptionMapper<UserNotFoundException> {
   */
/* @Override
    public Response toResponse(UserNotFoundException exception) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(exception.getMessage())
                .build();
    }*//*

    @Override
    public Response toResponse(UserNotFoundException exception) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage(exception.getMessage());
        apiResponse.setStatus(Response.Status.NOT_FOUND);
        apiResponse.setObject(null);
        return Response.accepted(apiResponse).status(Response.Status.NOT_FOUND).build();
    }

}
*/
