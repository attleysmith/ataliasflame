package liquibase.changelogs

databaseChangeLog() {

  include(file: 'v_001/user_inserts.groovy', relativeToChangelogFile: true)

  include(file: 'v_001/attribute_inserts.groovy', relativeToChangelogFile: true)
  include(file: 'v_001/god_inserts.groovy', relativeToChangelogFile: true)
  // caste must be after god
  include(file: 'v_001/caste_inserts.groovy', relativeToChangelogFile: true)
  // race must be after attribute, god and caste
  include(file: 'v_001/race_inserts.groovy', relativeToChangelogFile: true)
  // gender must be after god, caste and race
  include(file: 'v_001/gender_inserts.groovy', relativeToChangelogFile: true)
  include(file: 'v_001/skill_inserts.groovy', relativeToChangelogFile: true)
  include(file: 'v_001/level_inserts.groovy', relativeToChangelogFile: true)

}
