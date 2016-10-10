package liquibase.changelogs

databaseChangeLog() {

  include(file: 'v_001/attribute_inserts.groovy', relativeToChangelogFile: true)
  include(file: 'v_001/god_inserts.yml', relativeToChangelogFile: true)
  // caste must be after god
  include(file: 'v_001/caste_inserts.yml', relativeToChangelogFile: true)
  // race must be after attribute, god and caste
  include(file: 'v_001/race_inserts.yml', relativeToChangelogFile: true)
  // gender must be after god, caste and race
  include(file: 'v_001/gender_inserts.yml', relativeToChangelogFile: true)
  include(file: 'v_001/skill_inserts.yml', relativeToChangelogFile: true)
  include(file: 'v_001/level_inserts.yml', relativeToChangelogFile: true)

}
