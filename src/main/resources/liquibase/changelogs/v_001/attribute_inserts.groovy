package liquibase.changelogs.v_001

import static liquibase.utils.AttributeUtil.insertAttribute

databaseChangeLog() {

  changeSet(id: 'INSERT_ATTRIBUTES', author: 'amiklo') {
    comment "Initial insertion of attributes"

    insertAttribute(delegate, ['CODE': 'STRENGTH', 'NAME': 'erő', 'DEFENSE_MULTIPLIER': 1, 'DAMAGE_MULTIPLIER': 2])
    insertAttribute(delegate, ['CODE': 'DEXTERITY', 'NAME': 'ügyesség', 'ATTACK_MULTIPLIER': 2, 'DEFENSE_MULTIPLIER': 1, 'DAMAGE_MULTIPLIER': 1])
    insertAttribute(delegate, ['CODE': 'CONSTITUTION', 'NAME': 'állóképesség', 'HEALTH_MULTIPLIER': 10])
    insertAttribute(delegate, ['CODE': 'AGILITY', 'NAME': 'gyorsaság', 'ATTACK_MULTIPLIER': 1, 'DEFENSE_MULTIPLIER': 1])
    insertAttribute(delegate, ['CODE': 'INTELLIGENCE', 'NAME': 'értelem', 'MAGIC_MODIFIER': 5])
    insertAttribute(delegate, ['CODE': 'LORE', 'NAME': 'tudás', 'MAGIC_MODIFIER': 2])
    insertAttribute(delegate, ['CODE': 'MENTAL_POWER', 'NAME': 'szellemi erő', 'MAGIC_MODIFIER': 10])
    insertAttribute(delegate, ['CODE': 'SPIRITUAL_POWER', 'NAME': ' lelki erő', 'MAGIC_MODIFIER': 1])
  }

}
