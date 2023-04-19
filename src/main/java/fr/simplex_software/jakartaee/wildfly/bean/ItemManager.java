package fr.simplex_software.jakartaee.wildfly.bean;

import fr.simplex_software.jakartaee.wildfly.domain.*;
import fr.simplex_software.jakartaee.wildfly.facade.*;
import jakarta.enterprise.inject.*;
import jakarta.faces.application.*;
import jakarta.faces.context.*;
import jakarta.inject.*;

import java.util.*;

@Model
public class ItemManager
{
  @Inject
  private ItemFacade itemFacade;
  @Produces
  @Named
  private Item item = new Item();

  @Produces
  @Named
  public List<Item> getItemList()
  {
    return itemFacade.getItemList();
  }

  public void save()
  {
    itemFacade.addToList(item);
    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Added item " + item.getKey(), null);
    FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    item = new Item();
  }

  public void delete(Item item)
  {
    itemFacade.removeFromList(item);
  }

  public boolean isFull()
  {
    return (itemFacade.getItemList().size() > 0);
  }
}
