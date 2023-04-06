package fr.simplex_software.jakartaee.wildfly.test;

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

import static org.assertj.core.api.Assertions.*;

@ExtendWith(ArquillianExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SampleIT
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
    webTarget = client.target(url.toString()).path("/api/kv");
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
  public void test() throws MalformedURLException
  {
    Response response = webTarget.path("add").request(MediaType.APPLICATION_FORM_URLENCODED).post(Entity.form(new Form().param("key", "myKey").param("value", "myValue")));
    assertThat(response).isNotNull();
    assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
  }
}
