package si.fri.rso.napitnina.api.v1.resources;

import si.fri.rso.napitnina.lib.Tip;
import si.fri.rso.napitnina.services.beans.TipBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

@ApplicationScoped
@Path("/napitnina")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TipResource {

    @Inject
    private TipBean tipBean;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response getTips() {

        List<Tip> tip = tipBean.getTips();

        return Response.status(Response.Status.OK).entity(tip).build();
    }

    @GET
    @Path("/{id}")
    public Response getTip(@PathParam("id") Integer id) {

        Tip tip = tipBean.getTip(id);

        if (tip == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.status(Response.Status.OK).entity(tip).build();
    }

    @POST
    public Response createTip(Tip data) {

        if (data.getClient() == null || data.getOrder() == null || data.getTipValue() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } else {
            data = tipBean.createTip(data);
        }

        return Response.status(Response.Status.CONFLICT).entity(data).build();
    }

    @PUT
    @Path("{id}")
    public Response putTip(@PathParam("id") Integer id, Tip data) {

        data = tipBean.putTip(id, data);

        if (data == null) {
            return Response.status(Response.Status.NOT_MODIFIED).build();
        }
        return Response.status(Response.Status.OK).entity(data).build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteTip(@PathParam("id") Integer id, Tip data) {

        boolean deleted = tipBean.deleteTip(id);

        if (deleted) {
            return  Response.status(Response.Status.OK).build();
        } else return Response.status(Response.Status.NOT_FOUND).entity(data).build();
    }
}

