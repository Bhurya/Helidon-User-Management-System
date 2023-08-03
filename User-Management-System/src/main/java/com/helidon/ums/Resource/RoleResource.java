package com.helidon.ums.Resource;

import com.helidon.ums.Entity.Role;
import com.helidon.ums.Service.RoleService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("api")
public class RoleResource {

    private final RoleService roleService;

    @Inject
    public RoleResource(RoleService roleService) {
        this.roleService = roleService;
    }

    @POST
    @Path("role/save")
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional(Transactional.TxType.REQUIRED)
    public Response saveRole(Role role) {
        Role userRole = roleService.saveRole(role);
        return Response.accepted(userRole)
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("role/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRole(@PathParam("id") Long id) {
        Role userRole = roleService.getRole(id);
        return Response.accepted(userRole)
                .status(Response.Status.OK)
                .build();
    }

    @GET
    @Path("roleName")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRoleName(@QueryParam("name") String name) {
        Role userRole = roleService.getRoleByName(name);
        return Response.accepted(userRole)
                .status(Response.Status.OK)
                .build();
    }


}
