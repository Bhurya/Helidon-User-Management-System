package com.helidon.ums.Resource;

import com.helidon.ums.Dto.UserDetails;
import com.helidon.ums.Dto.UserDto;
import com.helidon.ums.Entity.User;
import com.helidon.ums.Exception.EmailAlreadyFoundException;
import com.helidon.ums.Exception.UserNotFoundException;
import com.helidon.ums.Response.ApiResponse;
import com.helidon.ums.Service.UserService;
import io.netty.handler.codec.http.HttpStatusClass;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;


@Path("users")
public class UserResource {


    private final UserService userService;

    @Inject
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response userList() {
        Object userList = userService.getAllUserList();
        return Response.accepted(userList)
                .status(Response.Status.OK)
                .build();
    }


    @GET
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response getUserById(@PathParam("userId") Long userId) throws UserNotFoundException {
        UserDetails user = userService.findUserById(userId);
        return Response.accepted(user)
                .status(Response.Status.OK)
                .build();
    }


    @POST
    @Path("save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response save(UserDto userDto) throws EmailAlreadyFoundException {

        User user = userService.saveUser(userDto);
        if (user != null) {
            return Response.accepted(user)
                    .status(Response.Status.CREATED)
                    .build();
        }
        return Response.status(Response.Status.BAD_REQUEST)
                .entity("Something went wrong")
                .build();
    }

    @DELETE
    @Path("delete/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response deleteUser(@PathParam("userId") Long userId) {
       ApiResponse apiResponse = userService.deleteUserById(userId);
       if (apiResponse.getCode() == 200) {
           return Response.accepted(apiResponse)
                   .status(Response.Status.OK)
                   .build();
       }else {
           return Response.serverError()
                   .status(Response.Status.INTERNAL_SERVER_ERROR)
                   .entity("Something went wrong")
                   .build();
       }
    }

    @DELETE
    @Path("deleteUser/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response deleteByUser(@PathParam("userId") Long userId) {
        Object userByIdSP = userService.deleteUserByIdSP(userId);
            return Response.accepted(userByIdSP)
                    .entity("User deleted successfully")
                    .status(Response.Status.OK)
                    .build();
    }

    @POST
    @Path("update/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response save(@PathParam("userId") Long userId, UserDto userDto) {

        User user = userService.updateUser(userId, userDto);
        if (user != null) {
            return Response.ok(user)
                    .status(Response.Status.OK)
                    .build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .build();
    }

    @GET
    @Path("email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response getUserByEmail(@PathParam("email") String email) throws UserNotFoundException {
        UserDetails user = userService.getUserByEmail(email);
        return Response.accepted(user)
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("firstname/{firstname}")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response getUserByFirstName(@PathParam("firstname") String firstname){
        Optional<Object> user = userService.getFirstNameLike(firstname);
        if (user.isPresent()) {
            ApiResponse response =(ApiResponse) user.get();
            return Response.accepted(user)
                    .status(Response.Status.fromStatusCode(response.getCode().intValue()))
                    .build();
        }
        return null;
    }

    @GET
    @Path("lastname")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response getUserByLastName(@QueryParam("lastname") String lastname) throws UserNotFoundException {
        UserDetails user = userService.getUserLastName(lastname);
        return Response.accepted(user)
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("lastnameLike")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response findUserByLastName(@QueryParam("lastname") String lastname) throws UserNotFoundException {
        Object user = userService.getLastNameLike(lastname);
        ApiResponse response = (ApiResponse) user;
        return Response.accepted(user)
                .status(Response.Status.fromStatusCode(response.getCode().intValue()))
                .build();
    }


}
