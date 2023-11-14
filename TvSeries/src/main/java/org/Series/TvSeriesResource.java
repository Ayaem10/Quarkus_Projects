package org.Series;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.Series.Proxy.EpisodeProxy;
import org.Series.Proxy.TvSeriesProxy;
import org.Series.model.Episode;
import org.Series.model.Serie;
import org.eclipse.microprofile.rest.client.inject.RestClient;


import java.util.ArrayList;
import java.util.List;

@Path("/series")
public class TvSeriesResource {
    @RestClient
    TvSeriesProxy tvSeriesProxy;

    @RestClient
    EpisodeProxy episodeProxy;

    private List<Serie> series = new ArrayList<>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSerie(@QueryParam("title") String title){
        Serie serie = tvSeriesProxy.get(title);
        series.add(serie);
        List<Episode> episodes = episodeProxy.get(serie.getId());
        return Response.ok(episodes).build();
    }
}
