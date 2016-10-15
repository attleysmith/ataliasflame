package liquibase.utils.v_001

import org.liquibase.groovy.delegate.ChangeSetDelegate

import static liquibase.utils.v_001.FieldValueUtil.*

/**
 * @author AMiklo on 2016.10.12.
 */
class RaceUtil {

  static void insertRace(ChangeSetDelegate delegate, Map<String, Object> map) {
    delegate.insert(tableName: 'RACE') {
      column(name: 'ID', valueComputed: nextSeq())
      column(name: 'CODE', valueComputed: stringValue(map.CODE))
      column(name: 'NAME', valueComputed: stringValue(map.NAME))
      column(name: 'DESCRIPTION', valueComputed: stringValue(map.DESCRIPTION))
    }

    // insertion of race mapping to enabled castes
    if (map.CASTES) {
      List<String> casteList = map.CASTES
      casteList.each { casteCode ->
        delegate.insert(tableName: 'RACE_ENABLED_CASTE_MAP') {
          column(name: 'RACE_ID', valueComputed: currSeq())
          column(name: 'CASTE_ID', valueComputed: findIdByCode('CASTE', casteCode))
        }
      }
    }

    // insertion of race mapping to enabled gods
    if (map.GODS) {
      List<String> godList = map.GODS
      godList.each { godCode ->
        delegate.insert(tableName: 'RACE_ENABLED_GOD_MAP') {
          column(name: 'RACE_ID', valueComputed: currSeq())
          column(name: 'GOD_ID', valueComputed: findIdByCode('GOD', godCode))
        }
      }
    }

    // insertion of race mapping to attribute multipliers
    if (map.ATTRIBUTE_MULTIPLIERS) {
      Map<String, Integer> multiplierList = map.ATTRIBUTE_MULTIPLIERS
      multiplierList.each { attributeCode, multiplier ->
        delegate.insert(tableName: 'RACE_ATTRIBUTE_MULTIPLIER_MAP') {
          column(name: 'RACE_ID', valueComputed: currSeq())
          column(name: 'ATTRIBUTE_ID', valueComputed: findIdByCode('ATTRIBUTE', attributeCode))
          column(name: 'MULTIPLIER', valueComputed: intValue(multiplier))
        }
      }
    }
  }
}
