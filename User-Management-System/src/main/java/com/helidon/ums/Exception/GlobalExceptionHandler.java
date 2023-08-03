package com.helidon.ums.Exception;

import com.helidon.ums.Response.ApiResponse;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
@Produces(MediaType.APPLICATION_JSON)
public class GlobalExceptionHandler implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException t) {

        if (t instanceof UserNotFoundException) {

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage(t.getMessage());
            apiResponse.setStatus(false);
            apiResponse.setData(null);
            apiResponse.setCode((long) Response.Status.NOT_FOUND.getStatusCode());
            return Response.accepted(apiResponse).status(Response.Status.NOT_FOUND).build();
        }

        if (t instanceof EmailAlreadyFoundException) {

            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage(t.getMessage());
            apiResponse.setStatus(false);
            apiResponse.setData(null);
            apiResponse.setCode((long) Response.Status.CONFLICT.getStatusCode());
            return Response.accepted(apiResponse).status(Response.Status.CONFLICT).build();
        }

        if (t instanceof CustomGlobalException) {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setMessage(t.getMessage());
            apiResponse.setStatus(false);
            apiResponse.setData(null);
            apiResponse.setCode((long) Response.Status.NO_CONTENT.getStatusCode());
            return Response.accepted(apiResponse).status(Response.Status.NO_CONTENT).build();
        }

        return Response.serverError()
                .entity(  t.getMessage())
                .build();
    }
}
