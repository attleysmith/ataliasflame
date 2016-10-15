package liquibase.changelogs.v_001

import static liquibase.utils.v_001.SkillUtil.insertSkill

/**
 * @author AMiklo on 2016.10.14.
 */
databaseChangeLog() {

  changeSet(id: 'INSERT_SKILLS', author: 'amiklo') {
    comment "Initial insertion of skills"

    insertSkill(delegate, [CODE: 'CLOSE_COMBAT', NAME: 'közelfegyver', SKILL_TYPE: 'COMBAT'])
    insertSkill(delegate, [CODE: 'POLEARM', NAME: 'szálfegyver', SKILL_TYPE: 'COMBAT'])
    insertSkill(delegate, [CODE: 'MISSILE', NAME: 'lőfegyver', SKILL_TYPE: 'COMBAT'])
    insertSkill(delegate, [CODE: 'THROWING', NAME: 'dobófegyver', SKILL_TYPE: 'COMBAT'])
    insertSkill(delegate, [CODE: 'SHIELD', NAME: 'pajzs', SKILL_TYPE: 'COMBAT'])
    insertSkill(delegate, [CODE: 'ARMOR', NAME: 'páncél', SKILL_TYPE: 'COMBAT'])
    insertSkill(delegate, [CODE: 'TACTICS', NAME: 'taktika', SKILL_TYPE: 'COMBAT'])
    insertSkill(delegate, [CODE: 'HEALER', NAME: 'gyógyító', SKILL_TYPE: 'MAGIC'])
    insertSkill(delegate, [CODE: 'DEFENSIVE', NAME: 'védő', SKILL_TYPE: 'MAGIC'])
    insertSkill(delegate, [CODE: 'OFFENSIVE', NAME: 'támadó', SKILL_TYPE: 'MAGIC'])
    insertSkill(delegate, [CODE: 'CURSE', NAME: 'átok', SKILL_TYPE: 'MAGIC'])
    insertSkill(delegate, [CODE: 'SUMMONER', NAME: 'idéző', SKILL_TYPE: 'MAGIC'])
    insertSkill(delegate, [CODE: 'ANIMALS', NAME: 'állatok', SKILL_TYPE: 'NATURE'])
    insertSkill(delegate, [CODE: 'PLANTS', NAME: 'növények', SKILL_TYPE: 'NATURE'])
    insertSkill(delegate, [CODE: 'MINERALS', NAME: 'ásványok', SKILL_TYPE: 'NATURE'])
    insertSkill(delegate, [CODE: 'HUNTING', NAME: 'vadászat', SKILL_TYPE: 'NATURE'])
    insertSkill(delegate, [CODE: 'ASTRONOMY', NAME: 'csillagászat', SKILL_TYPE: 'SCIENCE'])
    insertSkill(delegate, [CODE: 'ALCHEMY', NAME: 'alkímia', SKILL_TYPE: 'SCIENCE'])
    insertSkill(delegate, [CODE: 'ECONOMY', NAME: 'gazdaság', SKILL_TYPE: 'SCIENCE'])
    insertSkill(delegate, [CODE: 'CRAFT', NAME: 'kézművesség', SKILL_TYPE: 'SCIENCE'])


  }

}

