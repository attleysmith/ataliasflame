package liquibase.utils.v_001

import org.liquibase.groovy.delegate.ChangeSetDelegate

import static liquibase.utils.v_001.FieldValueUtil.*

/**
 * @author AMiklo on 2016.10.14.
 */
class GenderUtil {

  static void insertGender(ChangeSetDelegate delegate, Map<String, Object> map) {
    delegate.insert(tableName: 'GENDER') {
      column(name: 'ID', valueComputed: nextSeq())
      column(name: 'CODE', valueComputed: stringValue(map.CODE))
      column(name: 'NAME', valueComputed: stringValue(map.NAME))
      column(name: 'DESCRIPTION', valueComputed: stringValue(map.DESCRIPTION))
    }

    // insertion of gender mapping to enabled castes
    if (map.CASTES) {
      List<String> casteList = map.CASTES
      casteList.each { casteCode ->
        delegate.insert(tableName: 'GENDER_ENABLED_CASTE_MAP') {
          column(name: 'GENDER_ID', valueComputed: currSeq())
          column(name: 'CASTE_ID', valueComputed: findIdByCode('CASTE', casteCode))
        }
      }
    }

    // insertion of gender mapping to enabled races
    if (map.RACES) {
      List<String> raceList = map.RACES
      raceList.each { raceCode ->
        delegate.insert(tableName: 'GENDER_ENABLED_RACE_MAP') {
          column(name: 'GENDER_ID', valueComputed: currSeq())
          column(name: 'RACE_ID', valueComputed: findIdByCode('RACE', raceCode))
        }
      }
    }

    // insertion of gender mapping to enabled gods
    if (map.GODS) {
      List<String> godList = map.GODS
      godList.each { godCode ->
        delegate.insert(tableName: 'GENDER_ENABLED_GOD_MAP') {
          column(name: 'GENDER_ID', valueComputed: currSeq())
          column(name: 'GOD_ID', valueComputed: findIdByCode('GOD', godCode))
        }
      }
    }
  }
}
