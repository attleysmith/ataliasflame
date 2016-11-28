package hu.asgames.ws.service.json

import net.sf.json.JsonConfig
import net.sf.json.processors.JsonValueProcessor

import java.time.LocalDate
import java.time.LocalDateTime

/**
 * @author AMiklo on 2016.11.07.
 */
class LocalDateJsonValueProcessor implements JsonValueProcessor {

  @Override
  Object processArrayValue(final Object o, final JsonConfig jsonConfig) {
    processObjectValue(null, o, jsonConfig)
  }

  @Override
  Object processObjectValue(final String s, final Object o, final JsonConfig jsonConfig) {
    if (o instanceof LocalDate || o instanceof LocalDateTime) {
      return o.toString()
    }
    throw new IllegalArgumentException("Usage of ${this.class.simpleName} for class ${o.class.name} is not allowed!")
  }
}
