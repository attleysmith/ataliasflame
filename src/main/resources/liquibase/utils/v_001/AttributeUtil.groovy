package liquibase.utils.v_001

import org.liquibase.groovy.delegate.ChangeSetDelegate

import static liquibase.utils.v_001.FieldValueUtil.*

/**
 * @author AMiklo on 2016.10.10.
 */
class AttributeUtil {

  static insertAttribute(ChangeSetDelegate delegate, Map<String, Object> map) {
    delegate.insert(tableName: 'ATTRIBUTE') {
      column(name: 'ID', valueComputed: nextSeq())
      column(name: 'CODE', valueComputed: stringValue(map.CODE))
      column(name: 'NAME', valueComputed: stringValue(map.NAME))
      column(name: 'ATTACK_MULTIPLIER', valueComputed: intValue(map.ATTACK_MULTIPLIER, 0))
      column(name: 'DEFENSE_MULTIPLIER', valueComputed: intValue(map.DEFENSE_MULTIPLIER, 0))
      column(name: 'DAMAGE_MULTIPLIER', valueComputed: intValue(map.DAMAGE_MULTIPLIER, 0))
      column(name: 'HEALTH_MULTIPLIER', valueComputed: intValue(map.HEALTH_MULTIPLIER, 0))
      column(name: 'MAGIC_MODIFIER', valueComputed: intValue(map.MAGIC_MODIFIER, 0))
      column(name: 'DESCRIPTION', valueComputed: stringValue(map.DESCRIPTION))
    }
  }
}
