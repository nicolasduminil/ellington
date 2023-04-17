package fr.simplex_software.jakartaee.wildfly.test;

import fr.simplex_software.jakartaee.wildfly.domain.*;
import jakarta.ws.rs.client.*;
import jakarta.ws.rs.core.*;
import org.apache.http.*;
import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit5.*;
import org.jboss.arquillian.test.api.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.importer.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import java.io.*;
import java.net.*;
import java.util.*;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(ArquillianExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ItemArqIT
{
  @ArquillianResource
  private URL url;
  private Client client;
  private WebTarget webTarget;

  @Deployment(testable = false)
  public static Archive<?> getEarArchive()
  {
    WebArchive war = ShrinkWrap.create(ZipImporter.class, "duke.war")
      .importFrom(new File("./target/duke.war")).as(WebArchive.class);
    war.addPackage("fr.simplex_software.jakartaee.wildfly.test");
    return war;
  }

  @BeforeEach
  public void beforeEach()
  {
    client = ClientBuilder.newClient();
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
