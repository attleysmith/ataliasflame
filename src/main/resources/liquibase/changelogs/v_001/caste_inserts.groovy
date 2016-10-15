package liquibase.changelogs.v_001

import static liquibase.utils.v_001.CasteUtil.insertCaste

/**
 * @author AMiklo on 2016.10.10.
 */
databaseChangeLog() {

  changeSet(id: 'INSERT_CASTES', author: 'amiklo') {
    comment "Initial insertion of castes"

    insertCaste(delegate, [CODE: 'ARCHANGEL', NAME: 'arkangyal', GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'HIERARCH', NAME: 'főpap', NEXT_CASTE: ['ARCHANGEL'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'PRIEST', NAME: 'pap', NEXT_CASTE: ['HIERARCH'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'MONK', NAME: 'szerzetes', NEXT_CASTE: ['PRIEST'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'ATALIAS_PRIEST', NAME: 'Atalia papja', GODS: ['ATALIA']])
    insertCaste(delegate, [CODE: 'ARCHDRUID', NAME: 'fődruida', NEXT_CASTE: ['ATALIAS_PRIEST'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'DRUID', NAME: 'druida', NEXT_CASTE: ['ARCHDRUID'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'HERMIT', NAME: 'remete', NEXT_CASTE: ['DRUID'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'FREE_SOUL', NAME: 'szabad lélek', GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'PILGRIM', NAME: 'zarándok', NEXT_CASTE: ['FREE_SOUL'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'RANGER', NAME: 'kósza', NEXT_CASTE: ['PILGRIM'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'TRACKER', NAME: 'nyomkereső', NEXT_CASTE: ['RANGER'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'TITAN', NAME: 'titán', GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'GRANDMASTER', NAME: 'nagymester', NEXT_CASTE: ['TITAN'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'PALADIN', NAME: 'lovag', NEXT_CASTE: ['GRANDMASTER'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'FIGHTER', NAME: 'harcos', NEXT_CASTE: ['PALADIN'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])
    insertCaste(delegate, [CODE: 'AVATAR', NAME: 'avatár', GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'GINDON']])
    insertCaste(delegate, [CODE: 'WITCHMASTER', NAME: 'boszorkánymester', NEXT_CASTE: ['AVATAR'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'GINDON']])
    insertCaste(delegate, [CODE: 'MAGE', NAME: 'mágus', NEXT_CASTE: ['WITCHMASTER'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'GINDON']])
    insertCaste(delegate, [CODE: 'WIZARD', NAME: 'varázsló', NEXT_CASTE: ['MAGE'], GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'GINDON']])
    insertCaste(delegate, [CODE: 'ROGUE', NAME: 'csavargó', NEXT_CASTE: ['WIZARD', 'FIGHTER', 'TRACKER', 'HERMIT', 'MONK'],
                           GODS: ['HORA', 'SIFER', 'GETON', 'RUNID', 'ALATE', 'GINDON']])

  }

}
