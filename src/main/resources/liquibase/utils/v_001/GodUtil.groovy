package liquibase.utils.v_001

import org.liquibase.groovy.delegate.ChangeSetDelegate

import static liquibase.utils.v_001.FieldValueUtil.nextSeq
import static liquibase.utils.v_001.FieldValueUtil.stringValue

/**
 * @author AMiklo on 2016.10.10.
 */
class GodUtil {

  static void insertGod(ChangeSetDelegate delegate, Map<String, Object> map) {
    delegate.insert(tableName: 'GOD') {
      column(name: 'ID', valueComputed: nextSeq())
      column(name: 'CODE', valueComputed: stringValue(map.CODE))
      column(name: 'NAME', valueComputed: stringValue(map.NAME))
      column(name: 'INFLUENCE', valueComputed: stringValue(map.INFLUENCE))
      column(name: 'DESCRIPTION', valueComputed: stringValue(map.DESCRIPTION))
    }
  }
}
