package webservices;

import entities.UniteEnseignement;
import metiers.UniteEnseignementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ue")
public class UniteEnseignementRestApi {
    static UniteEnseignementBusiness helper = new UniteEnseignementBusiness();

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response
                .status(200)
                .entity(this.helper.getListeUE())
                .build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        return Response
                .status(200)
                .entity(this.helper.getUEByCode(id))
                .build();
    }

    @GET
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(UniteEnseignement ue) {
        ue = new UniteEnseignement(6, "add", "Mme Maroua Douiri", 6, 1);

        return Response
                .status(200)
                .entity(this.helper.addUniteEnseignement(ue))
                .build();
    }



}
