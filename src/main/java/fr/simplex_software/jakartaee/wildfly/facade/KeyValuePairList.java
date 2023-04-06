package fr.simplex_software.jakartaee.wildfly.facade;

import fr.simplex_software.jakartaee.wildfly.domain.*;
import jakarta.ejb.*;
import jakarta.ejb.Singleton;
import jakarta.inject.*;

import java.io.*;
import java.util.*;

@Singleton
@Named
public class KeyValuePairList implements Serializable
{
  private List<KeyValuePair> keyValuePairList;

  public KeyValuePairList()
  {
  }

  public List<KeyValuePair> getKeyValuePairList()
  {
    return keyValuePairList;
  }

  public void setKeyValuePairList(List<KeyValuePair> keyValuePairList)
  {
    this.keyValuePairList = keyValuePairList;
  }

  public int addToList (KeyValuePair keyValuePair)
  {
    keyValuePairList.add(keyValuePair);
    return keyValuePairList.size();
  }

  public int addToList (String key, String value)
  {
    keyValuePairList.add(new KeyValuePair(key, value));
    return keyValuePairList.size();
  }
}
