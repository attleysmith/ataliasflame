package liquibase.changelogs.v_001

import static liquibase.utils.v_001.RaceUtil.insertRace

/**
 * @author AMiklo on 2016.10.12.
 */
databaseChangeLog() {

  changeSet(id: 'INSERT_RACES', author: 'amiklo') {
    comment "Initial insertion of races"

    insertRace(delegate, [CODE  : 'HUMAN', NAME: 'ember',
                          CASTES: ['ROGUE', 'WIZARD', 'MAGE', 'WITCHMASTER', 'AVATAR', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER', 'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST', 'MONK', 'PRIEST', 'HIERARCH', 'ARCHANGEL'],
                          GODS  : ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON', 'ATALIA']])
    insertRace(delegate, [CODE                 : 'ELF', NAME: 'tünde',
                          CASTES               : ['ROGUE', 'WIZARD', 'MAGE', 'WITCHMASTER', 'AVATAR', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER', 'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST', 'MONK', 'PRIEST', 'HIERARCH', 'ARCHANGEL'],
                          GODS                 : ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON', 'ATALIA'],
                          ATTRIBUTE_MULTIPLIERS: [STRENGTH: -30, DEXTERITY: 20, CONSTITUTION: -10, INTELLIGENCE: 2, MENTAL_POWER: 10]])
    insertRace(delegate, [CODE                 : 'HALF_ELF', NAME: 'féltünde',
                          CASTES               : ['ROGUE', 'WIZARD', 'MAGE', 'WITCHMASTER', 'AVATAR', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER', 'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST', 'MONK', 'PRIEST', 'HIERARCH', 'ARCHANGEL'],
                          GODS                 : ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON', 'ATALIA'],
                          ATTRIBUTE_MULTIPLIERS: [STRENGTH: -15, DEXTERITY: 10, CONSTITUTION: -5, INTELLIGENCE: 1, MENTAL_POWER: 5]])
    insertRace(delegate, [CODE                 : 'NIGHT_ELF', NAME: 'éjtünde',
                          CASTES               : ['ROGUE', 'WIZARD', 'MAGE', 'WITCHMASTER', 'AVATAR', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER', 'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST', 'MONK', 'PRIEST', 'HIERARCH', 'ARCHANGEL'],
                          GODS                 : ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON', 'ATALIA'],
                          ATTRIBUTE_MULTIPLIERS: [DEXTERITY: 10, CONSTITUTION: -1, MENTAL_POWER: -3]])
    insertRace(delegate, [CODE                 : 'DWARF', NAME: 'törpe',
                          CASTES               : ['ROGUE', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER', 'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST'],
                          GODS                 : ['SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON', 'ATALIA'],
                          ATTRIBUTE_MULTIPLIERS: [STRENGTH: 10, DEXTERITY: -10, CONSTITUTION: 20, AGILITY: -20, MENTAL_POWER: -15]])
    insertRace(delegate, [CODE                 : 'ORC', NAME: 'ork',
                          CASTES               : ['ROGUE', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER', 'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST'],
                          GODS                 : ['SIFER', 'GETON', 'ALATE', 'GINDON', 'ATALIA'],
                          ATTRIBUTE_MULTIPLIERS: [STRENGTH: 10, DEXTERITY: -5, CONSTITUTION: 20, INTELLIGENCE: -2, MENTAL_POWER: -20]])
    insertRace(delegate, [CODE                 : 'MINOTAUR', NAME: 'minotaurusz',
                          CASTES               : ['ROGUE', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER', 'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST', 'MONK', 'PRIEST', 'HIERARCH', 'ARCHANGEL'],
                          GODS                 : ['SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON', 'ATALIA'],
                          ATTRIBUTE_MULTIPLIERS: [STRENGTH: 20, DEXTERITY: -10, CONSTITUTION: 20, AGILITY: -10, MENTAL_POWER: -20]])
    insertRace(delegate, [CODE                 : 'ARIMASPO', NAME: 'arimaszpó',
                          CASTES               : ['ROGUE', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER', 'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST'],
                          GODS                 : ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON', 'ATALIA'],
                          ATTRIBUTE_MULTIPLIERS: [STRENGTH: 5, DEXTERITY: 5, CONSTITUTION: 5, AGILITY: 5, INTELLIGENCE: -1, MENTAL_POWER: -9]])
    insertRace(delegate, [CODE                 : 'NYMPH', NAME: 'nimfa',
                          CASTES               : ['ROGUE', 'WIZARD', 'MAGE', 'WITCHMASTER', 'AVATAR', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST', 'MONK', 'PRIEST', 'HIERARCH', 'ARCHANGEL'],
                          GODS                 : ['HORA', 'SIFER', 'RUNID', 'GINDON', 'ATALIA'],
                          ATTRIBUTE_MULTIPLIERS: [STRENGTH: -10, CONSTITUTION: -20, INTELLIGENCE: 10, LORE: 5, MENTAL_POWER: 20, SPIRITUAL_POWER: -30]])
    insertRace(delegate, [CODE                 : 'HALFLING', NAME: 'félszerzet',
                          CASTES               : ['ROGUE', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER', 'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST', 'MONK', 'PRIEST', 'HIERARCH', 'ARCHANGEL'],
                          GODS                 : ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON', 'ATALIA'],
                          ATTRIBUTE_MULTIPLIERS: [STRENGTH: -30, DEXTERITY: 50, CONSTITUTION: -10, AGILITY: -5]])

  }

}
