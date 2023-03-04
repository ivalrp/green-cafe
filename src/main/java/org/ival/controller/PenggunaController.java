package org.ival.controller;

import org.ival.model.dto.PenggunaRequest;
import org.ival.service.PenggunaService;

import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;

@Path("/api/pengguna")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PenggunaController {

    @Inject
    PenggunaService penggunaService;

    @POST
    public Response addPengguna(JsonObject request) throws ParseException {
        return penggunaService.add(request);
    }
}
