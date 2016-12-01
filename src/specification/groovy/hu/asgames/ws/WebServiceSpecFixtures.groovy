package hu.asgames.ws

import hu.asgames.ws.service.TestWebServiceRepository

/**
 * @author AMiklo on 2016.10.20.
 */
class WebServiceSpecFixtures extends TestWebServiceRepository {

  static String dummy() {
    UUID.randomUUID()
  }

  static boolean hasErrorResponse(AssertionError error) {
    error.message.contains("responseStatus:ERROR")
  }

}
