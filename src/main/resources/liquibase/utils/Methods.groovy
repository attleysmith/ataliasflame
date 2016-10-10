package liquibase.utils

import org.liquibase.groovy.delegate.ChangeSetDelegate

/**
 * Sample methods for technology test
 *
 * @author AMiklo on 2016.09.19.
 */
@Deprecated
class Methods {

  def static String concatenate(String a, String b) {
    a + b
  }

  def static void insertAlma(ChangeSetDelegate delegate, String a) {
    delegate.insert(tableName: 'TEST') {
      column(name: 'A', value: a)
      column(name: 'B', value: 1)
      column(name: 'C', value: 10.2)
    }
  }

  def static void insertAlmaExt(ChangeSetDelegate delegate, Map<String, Object> map) {
    delegate.insert(tableName: 'TEST') {
      map.forEach() { key, value ->
        column(name: key, value: value)
      }
    }
  }
}
