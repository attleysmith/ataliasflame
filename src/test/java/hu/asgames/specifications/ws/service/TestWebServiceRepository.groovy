package hu.asgames.specifications.ws.service

import groovyx.net.http.RESTClient
import org.springframework.http.MediaType
import spock.lang.Shared
import spock.lang.Specification

/**
 * @author AMiklo on 2016.10.21.
 */
class TestWebServiceRepository extends Specification {

  private static final String ROOT_ENDPOINT = "http://localhost:3080"
  private static final String USER = "user"
  private static final String PASSWORD = "pw"
  private static final String CONTENT_TYPE = MediaType.APPLICATION_JSON

  @Shared
  private RESTClient restClient = new RESTClient(ROOT_ENDPOINT)

  @Shared
  UserTestWebService userService = new UserTestWebService(restClient)

  void setupSpec() {
    restClient.auth.basic(USER, PASSWORD)
    restClient.setContentType(CONTENT_TYPE)
  }

}
