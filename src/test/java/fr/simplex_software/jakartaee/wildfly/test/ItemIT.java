package fr.simplex_software.jakartaee.wildfly.test;

import fr.simplex_software.jakartaee.wildfly.domain.*;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.*;
import org.apache.http.*;
import org.junit.jupiter.api.*;

import java.net.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemIT
{
  private static final String lbUrl = "http://duke-Publi-1G2Y0M1JQK8E5-859083319.eu-west-3.elb.amazonaws.com/duke";
  private URL url;
  private Client client;
  private WebTarget webTarget;

  @BeforeEach
  public void beforeEach() throws MalformedURLException
  {
    client = ClientBuilder.newClient();
    url = new URL(lbUrl);
    webTarget = client.target(url.toString()).path("/api/items");
  }

  @AfterEach
  public void afterEach()
  {
    if (client != null)
    {
      client.close();
      client = null;
    }
    webTarget = null;
  }

  @Test
  @Order(10)
  public void testAdd()
  {
    Response response = webTarget.request().accept(MediaType.TEXT_HTML_TYPE).post(Entity.form(new Form().param("key", "myKey").param("value", "myValue")));
    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
  }

  @Test
  @Order(20)
  public void testList()
  {
    List<Item> itemList = webTarget.path("list").request().accept(MediaType.APPLICATION_XML).get(new GenericType<ArrayList<Item>>(){});
    assertThat(itemList).isNotNull();
    assertThat(itemList).hasSize(1);
    assertThat(itemList.get(0).getValue()).isEqualTo("myValue");
  }

  @Test
  @Order(30)
  public void testXml()
  {
    Item item = webTarget.queryParam("id", 0).request().accept(MediaType.APPLICATION_XML).get(Item.class);
    assertThat(item).isNotNull();
    assertThat(item.getValue().equals("myValue"));
  }

  @Test
  @Order(40)
  public void testXml2()
  {
    Item item = webTarget.path("{id}").resolveTemplate("id", 0).request().accept(MediaType.APPLICATION_XML).get(Item.class);
    assertThat(item).isNotNull();
    assertThat(item.getValue().equals("myValue"));
  }

  @Test
  @Order(50)
  public void testRemoveItem()
  {
    Response response = webTarget.path("{id}").resolveTemplate("id", 0).request().delete();
    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
    assertThat(response.readEntity(Integer.class)).isEqualTo(0);
  }

  @Test
  @Order(60)
  public void testRemoveAll()
  {
    Response response = webTarget.request().delete();
    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
  }
}
