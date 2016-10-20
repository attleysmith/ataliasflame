package liquibase.utils.v_001

import org.liquibase.groovy.delegate.ChangeSetDelegate

import static liquibase.utils.v_001.FieldValueUtil.*

/**
 * @author AMiklo on 2016.10.17.
 */
class UserUtil {

  static void insertUser(ChangeSetDelegate delegate, Map<String, Object> map) {
    delegate.insert(tableName: 'USER') {
      column(name: 'ID', valueComputed: nextSeq())
      column(name: 'USERNAME', valueComputed: stringValue(map.USERNAME))
      column(name: 'PASSWORD', valueComputed: passValue(map.PASSWORD))
      column(name: 'EMAIL', valueComputed: stringValue(map.EMAIL))
      column(name: 'STATE', valueComputed: stringValue(map.STATE, 'NORMAL'))
    }
    //TODO: user_role_map(user_id, role_id)
    //TODO: user_permission_map(user_id, permission_id)
  }
}
