package fr.simplex_software.jakartaee.wildfly.test;

import org.jboss.arquillian.container.test.api.*;
import org.jboss.arquillian.junit5.*;
import org.jboss.shrinkwrap.api.*;
import org.jboss.shrinkwrap.api.importer.*;
import org.jboss.shrinkwrap.api.spec.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;

import java.io.*;


@ExtendWith(ArquillianExtension.class)
public class SampleIT
{
  @Deployment
  public static Archive<?> getEarArchive()
  {
    WebArchive war = ShrinkWrap.create(ZipImporter.class, "duke.war")
      .importFrom(new File("./target/duke.war")).as(WebArchive.class);
    war.addPackage("fr.simplex_software.jakartaee.wildfly.test");
    return war;
  }

  @Test
  public void test()
  {
    System.out.println("Test is invoked...");
  }
}
