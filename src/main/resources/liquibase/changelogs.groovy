package liquibase

//import static liquibase.utils.Methods.*

databaseChangeLog() {

  include(file: 'changelogs/v_001.groovy', relativeToChangelogFile: true)

//  changeSet(id: 'alma-1', author: 'almafa') {
//    comment concatenate("alma", "körte")
//
//    createTable(tableName: 'TEST') {
//      column(name: 'A', type: 'VARCHAR(50)')
//      column(name: 'B', type: 'INT')
//      column(name: 'C', type: 'decimal(14,8)')
//    }
//  }
//
//  changeSet(id: 'alma-2', author: 'almafa') {
//    comment concatenate("dió", "répa")
//
//    insertAlma(delegate, "alma")
//    insertAlmaExt(delegate, ['A': 'öregördög', 'B': 128, 'C': 7.32])
//  }

}
