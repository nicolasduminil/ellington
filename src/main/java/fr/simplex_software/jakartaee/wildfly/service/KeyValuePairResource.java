package fr.simplex_software.jakartaee.wildfly.service;

import fr.simplex_software.jakartaee.wildfly.facade.*;
import jakarta.inject.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("kv")
public class KeyValuePairResource
{
  private static final String RESPONSE_OK="<p>Property has been added ! </p> <p><a href=\"http://localhost:8080/duke\">Back</a></p>";
  @Inject
  private KeyValuePairList keyValuePairList;

  @GET
  @Path("/xml/{id}")
  @Produces(MediaType.APPLICATION_XML)
  public Response getPropertyByPathParam(@PathParam("id")int id)
  {
    return Response.ok().entity(keyValuePairList.getKeyValuePairList().get(id)).build();
  }

  @GET
  @Path("/xml")
  @Produces(MediaType.APPLICATION_XML)
  public Response getPropertyByQueryId(@QueryParam("id") int id)
  {
    return Response.ok().entity(keyValuePairList.getKeyValuePairList().get(id)).build();
  }

  @GET
  @Path("/list")
  @Produces(MediaType.APPLICATION_XML)
  public Response getProperty()
  {
    return Response.ok().entity(keyValuePairList.getKeyValuePairList()).build();
  }

  @POST
  @Produces("text/html")
  public Response createProperty(@FormParam("key")String key, @FormParam("value")String value)
  {
    int n = keyValuePairList.addToList(key,value);
    return Response.ok(RESPONSE_OK).build();
  }
}
