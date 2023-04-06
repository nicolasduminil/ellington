package fr.simplex_software.jakartaee.wildfly.facade;

import fr.simplex_software.jakartaee.wildfly.domain.*;
import jakarta.annotation.*;
import jakarta.ejb.Singleton;
import jakarta.inject.*;

import java.io.*;
import java.util.*;

@Singleton
@Named
public class ItemFacade implements Serializable
{
  private List<Item> itemList;

  public ItemFacade()
  {
  }

  @PostConstruct
  public void postConstruct()
  {
    itemList = new ArrayList<Item>();
  }

  public List<Item> getItemList()
  {
    return itemList;
  }

  public void setItemList(List<Item> itemList)
  {
    this.itemList = itemList;
  }

  public int addToList(Item item)
  {
    itemList.add(item);
    return itemList.size();
  }

  public int addToList(String key, String value)
  {
    itemList.add(new Item(key, value));
    return itemList.size();
  }
}
