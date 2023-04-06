package fr.simplex_software.jakartaee.wildfly.service;

import fr.simplex_software.jakartaee.wildfly.domain.*;
import fr.simplex_software.jakartaee.wildfly.facade.*;
import jakarta.inject.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;

@Path("kv")
public class ItemResource
{
  private static final String RESPONSE_OK="<p>Property has been added ! </p> <p><a href=\"http://localhost:8080/duke\">Back</a></p>";
  @Inject
  private ItemFacade itemFacade;

  @GET
  @Path("/xml/{id}")
  @Produces(MediaType.APPLICATION_XML)
  public Item getItemByPathParam(@PathParam("id")int id)
  {
    return itemFacade.getItemList().get(id);
  }

  @GET
  @Path("/xml")
  @Produces(MediaType.APPLICATION_XML)
  public Item getItemByQueryId(@QueryParam("id")int id)
  {
    return itemFacade.getItemList().get(id);
  }

  @GET
  @Path("/list")
  @Produces(MediaType.APPLICATION_XML)
  public List<Item> getItems()
  {
    return itemFacade.getItemList();
  }

  @POST
  @Produces(MediaType.TEXT_HTML)
  public Response createItem(@FormParam("key")String key, @FormParam("value")String value)
  {
    int n = itemFacade.addToList(key,value);
    return Response.ok(RESPONSE_OK).build();
  }
}
