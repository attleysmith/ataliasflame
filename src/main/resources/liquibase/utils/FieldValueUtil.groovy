package liquibase.utils

/**
 * @author AMiklo on 2016.10.10.
 */
class FieldValueUtil {

  static stringValue(String value) {
    value ? "'" + value + "'" : "NULL"
  }

  static intValue(Integer value) {
    intValue(value, "NULL")
  }

  static intValue(Integer value, Integer defaultValue) {
    intValue(value, defaultValue.toString())
  }

  static intValue(Integer value, String defaultValue) {
    value != null ? value : defaultValue
  }

}
