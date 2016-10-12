package liquibase.utils.v_001

import org.liquibase.groovy.delegate.ChangeSetDelegate

import static liquibase.utils.v_001.FieldValueUtil.*

/**
 * @author AMiklo on 2016.10.10.
 */
class CasteUtil {

  static insertCaste(ChangeSetDelegate delegate, Map<String, Object> map) {
    delegate.insert(tableName: 'CASTE') {
      column(name: 'ID', valueComputed: nextSeq())
      column(name: 'CODE', valueComputed: stringValue(map.CODE))
      column(name: 'NAME', valueComputed: stringValue(map.NAME))
      column(name: 'DESCRIPTION', valueComputed: stringValue(map.DESCRIPTION))
    }

    // insertion of caste mapping to next castes
    if (map.NEXT_CASTE) {
      List<String> nextCasteList = map.NEXT_CASTE
      nextCasteList.each { casteCode ->
        delegate.insert(tableName: 'CASTE_NEXT_CASTE_MAP') {
          column(name: 'CASTE_ID', valueComputed: currSeq())
          column(name: 'NEXT_CASTE_ID', valueComputed: findIdByCode('CASTE', casteCode))
        }
      }
    }

    // insertion of caste mapping to enabled gods
    if (map.GODS) {
      List<String> godList = map.GODS
      godList.each { godCode ->
        delegate.insert(tableName: 'CASTE_ENABLED_GOD_MAP') {
          column(name: 'CASTE_ID', valueComputed: currSeq())
          column(name: 'GOD_ID', valueComputed: findIdByCode('GOD', godCode))
        }
      }
    }
  }
}
