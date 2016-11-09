package hu.asgames.specifications.ws.service

import groovyx.net.http.EncoderRegistry
import groovyx.net.http.RESTClient
import hu.asgames.specifications.ws.service.json.LocalDateJsonValueProcessor
import net.sf.json.JSON
import net.sf.json.JSONArray
import net.sf.json.JSONObject
import net.sf.json.JsonConfig
import net.sf.json.groovy.JsonGroovyBuilder
import org.springframework.http.MediaType
import spock.lang.Shared
import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @author AMiklo on 2016.10.21.
 */
class TestWebServiceRepository extends Specification {

  private static final String CLIENT_CODE = "SPECIFICATION"
  private static final String ROOT_ENDPOINT = "http://localhost:3080"
  private static final String USER = "user"
  private static final String PASSWORD = "pw"
  private static final String CONTENT_TYPE = MediaType.APPLICATION_JSON

  @Shared
  private RESTClient restClient = new RESTClient(ROOT_ENDPOINT)

  @Shared
  UserTestWebService userService = new UserTestWebService(restClient, CLIENT_CODE)

  void setupSpec() {
    restClient.auth.basic(USER, PASSWORD)
    restClient.setContentType(CONTENT_TYPE)

    JsonConfig jsonConfig = new JsonConfig()
    jsonConfig.registerJsonValueProcessor(LocalDate.class, new LocalDateJsonValueProcessor())
    jsonConfig.registerJsonValueProcessor(LocalDateTime.class, new LocalDateJsonValueProcessor())

    EncoderRegistry encoderRegistry = restClient.getEncoder();
    encoderRegistry[groovyx.net.http.ContentType.JSON] = { Object model, Object contentType ->
      Object json;
      if (model instanceof Map) {
        json = new JSONObject();
        ((JSONObject) json).putAll((Map) model);
      } else if (model instanceof Collection) {
        json = new JSONArray();
        ((JSONArray) json).addAll((Collection) model);
      } else if (model instanceof Closure) {
        Closure closure = (Closure) model;
        closure.setDelegate(new JsonGroovyBuilder());
        json = (JSON) closure.call();
      } else if (model instanceof String || model instanceof GString) {
        json = model
      } // assume string is valid JSON already.
      else {
        json = JSONObject.fromObject(model, jsonConfig)
      }; // Assume object is a JavaBean

      if (contentType == null) {
        contentType = ContentType.JSON
      };
      return encoderRegistry.createEntity(contentType, json.toString());
    }
  }

}
