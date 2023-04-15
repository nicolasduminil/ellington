package fr.simplex_software.jakartaee.wildfly.service;

import fr.simplex_software.jakartaee.wildfly.domain.*;
import fr.simplex_software.jakartaee.wildfly.facade.*;
import jakarta.inject.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.*;

@Path("items")
public class ItemResource
{
  private static final String RESPONSE_OK="<p>Property has been added ! </p> <p><a href=\"/duke\">Back</a></p>";
  @Inject
  private ItemFacade itemFacade;

  @GET
  @Path("{id}")
  @Produces(MediaType.APPLICATION_XML)
  public Item getItemByPathParam(@PathParam("id")int id)
  {
    return itemFacade.getItemList().get(id);
  }

  @GET
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
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @Produces(MediaType.TEXT_HTML)
  public Response createItem(@FormParam("key")String key, @FormParam("value")String value)
  {
    int n = itemFacade.addToList(key,value);
    return Response.ok(RESPONSE_OK).build();
  }

  @DELETE
  @Path("{id}")
  public Response removeItem(@PathParam("id")int id)
  {
    return Response.ok(itemFacade.removeFromList(id)).build();
  }

  @DELETE
  public Response removeItems()
  {
    itemFacade.removeAll();
    return Response.ok().build();
  }












}
