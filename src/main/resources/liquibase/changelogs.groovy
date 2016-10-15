package liquibase

databaseChangeLog() {

  include(file: 'changelogs/v_001.groovy', relativeToChangelogFile: true)

}
