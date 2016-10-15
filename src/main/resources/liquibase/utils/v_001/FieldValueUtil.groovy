package liquibase.utils.v_001

/**
 * @author AMiklo on 2016.10.10.
 */
class FieldValueUtil {

  private static final DEFAULT_SEQ = 'base_id_generator_seq'

  static String nextSeq() {
    nextSeq(DEFAULT_SEQ);
  }

  static String nextSeq(String sequenceName) {
    sequenceName + ".nextval"
  }

  static String currSeq() {
    currSeq(DEFAULT_SEQ);
  }

  static String currSeq(String sequenceName) {
    sequenceName + ".currval"
  }

  static String stringValue(String value) {
    value ? "'" + value + "'" : "NULL"
  }

  static String intValue(Integer value) {
    intValue(value, "NULL")
  }

  static String intValue(Integer value, Integer defaultValue) {
    intValue(value, defaultValue.toString())
  }

  static String intValue(Integer value, String defaultValue) {
    value != null ? value : defaultValue
  }

  static String longValue(Long value) {
    longValue(value, "NULL")
  }

  static String longValue(Long value, Long defaultValue) {
    longValue(value, defaultValue.toString())
  }

  static String longValue(Long value, String defaultValue) {
    value != null ? value : defaultValue
  }

  static String findIdByCode(String table, String code) {
    "select id from $table where code = ${stringValue(code)}"
  }

  static String findIdByIntField(String table, String field, Integer value) {
    "select id from $table where $field = ${intValue(value)}"
  }

}
