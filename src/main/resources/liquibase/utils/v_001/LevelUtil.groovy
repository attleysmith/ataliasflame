package liquibase.utils.v_001

import org.liquibase.groovy.delegate.ChangeSetDelegate

import static liquibase.utils.v_001.FieldValueUtil.*

/**
 * @author AMiklo on 2016.10.14.
 */
class LevelUtil {

  static void insertLevel(ChangeSetDelegate delegate, Map<String, Object> map) {
    delegate.insert(tableName: 'LEVEL') {
      column(name: 'ID', valueComputed: nextSeq())
      column(name: 'EXP_TO_LEVEL_UP', valueComputed: longValue(map.EXP_TO_LEVEL_UP))
      column(name: 'LEVEL', valueComputed: intValue(map.LEVEL))
      column(name: 'NEXT_LEVEL_ID', valueComputed: findIdByIntField('LEVEL', 'LEVEL', map.NEXT_LEVEL))
    }
  }
}
