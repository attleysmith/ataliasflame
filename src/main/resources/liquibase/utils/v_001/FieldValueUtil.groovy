package liquibase.utils.v_001

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/**
 * @author AMiklo on 2016.10.10.
 */
class FieldValueUtil {

  private static final String DEFAULT_SEQ = 'base_id_generator_seq'
  private static final String NULL = 'NULL'
  private static final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

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
    stringValue(value, null)
  }

  static String stringValue(String value, String defaultValue) {
    if (value != null) {
      return stringify(value)
    } else if (defaultValue != null) {
      return stringify(defaultValue)
    } else {
      return NULL
    }
  }

  static String intValue(Integer value) {
    intValue(value, null)
  }

  static String intValue(Integer value, Integer defaultValue) {
    if (value != null) {
      return value.toString()
    } else if (defaultValue != null) {
      return defaultValue.toString()
    } else {
      return NULL
    }
  }

  static String longValue(Long value) {
    longValue(value, null)
  }

  static String longValue(Long value, Long defaultValue) {
    if (value != null) {
      return value.toString()
    } else if (defaultValue != null) {
      return defaultValue.toString()
    } else {
      return NULL
    }
  }

  static String passValue(String rawPassword) {
    if (rawPassword != null) {
      String encodedPassword = ENCODER.encode(rawPassword)
      return stringify(encodedPassword)
    } else {
      return NULL
    }
  }

  static String findIdByCode(String table, String code) {
    "select id from $table where code = ${stringValue(code)}"
  }

  static String findIdByIntField(String table, String field, Integer value) {
    "select id from $table where $field = ${intValue(value)}"
  }

  private static String stringify(String value) {
    "'" + value + "'"
  }

}
