package org.ival.controller;


import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.ival.model.Employee;
import org.ival.model.User;
import org.ival.model.dto.EmployeeRequest;
import org.ival.model.dto.EmployeeResponse;
import org.ival.model.dto.UserRequest;
import org.ival.service.EmployeeService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.util.Map;

@Path("/api/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    @Inject
    JsonWebToken jwt;

    @Inject
    EmployeeService employeeService;

    @POST
    @RolesAllowed("user")
    @SecurityRequirement(name = "Bearer JWT Token")
    @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema (implementation = Employee.class)))
    @APIResponses({
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema (implementation = Map.class)))
    })
    public Response post(EmployeeRequest request) throws ParseException {
        return employeeService.post(request, jwt.getClaim("userId").toString());
    }

    @POST
    @Path("/{employeeId}")
    @RolesAllowed("user")
    @SecurityRequirement(name = "Bearer JWT Token")
    @RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema (implementation = Employee.class)))
    @APIResponses({
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema (implementation = Map.class)))
    })
    public Response put(EmployeeRequest request, @PathParam("{employeeId}") String employeeId) throws ParseException {
        return employeeService.put(request, jwt.getClaim("userId").toString(), employeeId);
    }

    @DELETE
    @Path("/{employeeId}")
    @RolesAllowed("user")
    @SecurityRequirement(name = "Bearer JWT Token")
    @APIResponses({
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema (implementation = Map.class)))
    })
    public Response delete(@PathParam("{employeeId}") String employeeId) throws ParseException {
        return employeeService.delete(jwt.getClaim("userId").toString(), employeeId);
    }

    @GET
    @RolesAllowed("user")
    @SecurityRequirement(name = "Bearer JWT Token")
    @APIResponses({
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema (implementation = EmployeeResponse.class)))
    })
    public Response list(
            @DefaultValue("1") @QueryParam("page") Integer page,
            @QueryParam("jobPosition") String jobPositionId){
        return employeeService.list(page, jobPositionId);
    }

    @GET
    @Path("/detail/{employeeId}")
    @RolesAllowed("user")
    @SecurityRequirement(name = "Bearer JWT Token")
    @APIResponses({
            @APIResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema (implementation = Employee.class)))
    })
    public Response detail(@PathParam("employeeId") String employeeId){
        return employeeService.detail(employeeId);
    }
    

}
