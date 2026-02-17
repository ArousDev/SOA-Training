package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("/module")
public class ModuleRestApi {
    static ModuleBusiness helper = new ModuleBusiness();

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Module module) {
        if(helper.addModule(module))
            return Response
                    .status(Response.Status.CREATED)
                    .entity(Map.of(
                            "message", "module added successfully.",
                            "data", module
                    ))
                    .build();

        else
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("Could not add module")
                    .build();
    }

    @GET
    @Path("/get/matricule/{matr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByMatricule(@PathParam("matr") String matricule) {
        Module module = helper.getModuleByMatricule(matricule);
        if(module != null)
            return Response
                    .status(Response.Status.FOUND)
                    .entity(Map.of(
                            "message", "module with matricule = " + matricule + " exists.",
                            "data", module
                    ))
                    .build();

        else
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("no module with matricule = " + matricule + " in List.")
                    .build();
    }

    @GET
    @Path("/get/type/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByType(@PathParam("type")Module.TypeModule type) {
        List<Module> list = helper.getModulesByType(type);
        if(!list.isEmpty())
            return Response
                    .status(Response.Status.FOUND)
                    .entity(Map.of(
                            "message", list.size() + " modules with type = " + type + " exists.",
                            "data", list
                    ))
                    .build();

        else
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("no modules with type = " + type + " in List.")
                    .build();
    }

    @PUT
    @Path("/update/{matr}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("matr") String matricule, Module module) {
        if(helper.updateModule(matricule,module))
            return Response
                    .status(Response.Status.OK)
                    .entity(Map.of(
                            "message", "module successfully updated",
                            "data", module
                    ))
                    .build();

        else
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("no module with matricule = " + matricule + " in List.")
                    .build();
    }

    @DELETE
    @Path("/delete/{matr}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("matr") String matricule) {
        Module module = helper.getModuleByMatricule(matricule);
        if(helper.deleteModule(matricule))
            return Response
                    .status(Response.Status.OK)
                    .entity(Map.of(
                            "message", "module successfully deleted.",
                            "data", module
                    ))
                    .build();

        else
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("no module with matricule = " + matricule + " in List.")
                    .build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        return Response
                .status(200)
                .entity(helper.getAllModules())
                .build();
    }

}
