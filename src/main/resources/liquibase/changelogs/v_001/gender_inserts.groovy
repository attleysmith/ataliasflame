package liquibase.changelogs.v_001

import static liquibase.utils.v_001.GenderUtil.insertGender

/**
 * @author AMiklo on 2016.10.13.
 */
databaseChangeLog() {

  changeSet(id: 'INSERT_GENDERS', author: 'amiklo') {
    comment "Initial insertion of genders"

    insertGender(delegate, [CODE  : 'MALE', NAME: 'férfi',
                            CASTES: ['ROGUE', 'WIZARD', 'MAGE', 'WITCHMASTER', 'AVATAR', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER',
                                     'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST', 'MONK', 'PRIEST', 'HIERARCH', 'ARCHANGEL'],
                            RACES : ['HUMAN', 'ELF', 'HALF_ELF', 'NIGHT_ELF', 'DWARF', 'ORC', 'MINOTAUR', 'ARIMASPO', 'HALFLING'],
                            GODS  : ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON', 'ATALIA']])
    insertGender(delegate, [CODE  : 'FEMALE', NAME: 'nő',
                            CASTES: ['ROGUE', 'WIZARD', 'MAGE', 'WITCHMASTER', 'AVATAR', 'FIGHTER', 'PALADIN', 'GRANDMASTER', 'TITAN', 'TRACKER', 'RANGER',
                                     'PILGRIM', 'FREE_SOUL', 'HERMIT', 'DRUID', 'ARCHDRUID', 'ATALIAS_PRIEST', 'MONK', 'PRIEST', 'HIERARCH', 'ARCHANGEL'],
                            RACES : ['HUMAN', 'ELF', 'HALF_ELF', 'NIGHT_ELF', 'DWARF', 'ORC', 'ARIMASPO', 'NYMPH', 'HALFLING'],
                            GODS  : ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON', 'ATALIA']])

  }

}
