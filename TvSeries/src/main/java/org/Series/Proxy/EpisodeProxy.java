package org.Series.Proxy;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.Series.model.Episode;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@Path("shows/")
@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(baseUri = "http://api.tvmaze.com/")
public interface EpisodeProxy {

    @Path("{id}/episodes")
    @GET
    List<Episode> get(@PathParam("id") long id);

}
