package fr.simplex_software.jakartaee.wildfly.domain;

import jakarta.xml.bind.annotation.*;

import java.io.*;

@XmlRootElement
public class Item implements Serializable
{
  private String key;
  private String value;

  public Item()
  {
  }

  public Item(String key, String value)
  {
    this.key = key;
    this.value = value;
  }

  public String getKey()
  {
    return key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }
}
