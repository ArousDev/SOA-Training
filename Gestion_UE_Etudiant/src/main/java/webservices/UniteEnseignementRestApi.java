package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/ue")
public class UniteEnseignementRestApi {
    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response
                .status(200)
                .entity(helper.getListeUE())
                .build();
    }

    @GET
    @Path("/get/id/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        UniteEnseignement ue = helper.getUEByCode(id);
        if(ue != null)
            return Response
                    .status(Response.Status.FOUND)
                    .entity(Map.of(
                            "message", "ue with id = " + id + " exists.",
                            "data", ue
                    ))
                    .build();

        else
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("no ue with id = " + id + " in List.")
                    .build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(UniteEnseignement ue) {
        if(helper.addUniteEnseignement(ue))
            return Response
                    .status(Response.Status.CREATED)
                    .entity(Map.of(
                            "message", "ue added successfully.",
                            "data", ue
                    ))
                    .build();

        else
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Could not add ue")
                    .build();
    }

    @GET
    @Path("/get/sem/{nb}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBySemester(@PathParam("nb") int nb) {
        List<UniteEnseignement> list = helper.getUEBySemestre(nb);
        if(!list.isEmpty())
            return Response
                    .status(Response.Status.FOUND)
                    .entity(Map.of(
                            "message", list.size() + " ues with semester = " + nb + " were found.",
                            "data", list
                    ))
                    .build();

        else
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("no ue with semester = " + nb + " in List.")
                    .build();
    }

    @DELETE
    @Path("/delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") int id) {
        UniteEnseignement ue = helper.getUEByCode(id);
        if(helper.deleteUniteEnseignement(id))
            return Response
                    .status(Response.Status.OK)
                    .entity(Map.of(
                            "message", "ue successfully deleted.",
                            "data", ue
                    ))
                    .build();

        else
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("no ue with code = " + id + " in List.")
                    .build();
    }

    @PUT
    @Path("/update/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, UniteEnseignement ue) {
        if(helper.updateUniteEnseignement(id,ue))
            return Response
                    .status(Response.Status.OK)
                    .entity(Map.of(
                            "message", "ue successfully updated",
                            "data", ue
                    ))
                    .build();

        else
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("no ue with code = " + id + " in List.")
                    .build();
    }

}
