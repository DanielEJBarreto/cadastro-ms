package daniel.caixa.resource;


import daniel.caixa.dto.UserRequest;
import daniel.caixa.dto.UserResponse;
import daniel.caixa.service.UserService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/{id}")
    public UserResponse findById(@PathParam("id") Long id){
        return userService.findById(id);
    }

    @GET
    public List<UserResponse> listAll() {
        return userService.listAll();
    }

    @POST
    public Response create(@Valid UserRequest dto){
        UserResponse created = userService.createUser(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("/{id}/delete")
    public Response delete(@PathParam("id") Long id) {
        userService.deleteUser(id);
        return Response.ok().build();
    }
}
