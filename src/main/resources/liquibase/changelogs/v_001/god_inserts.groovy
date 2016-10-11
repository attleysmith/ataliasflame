package liquibase.changelogs.v_001

import static liquibase.utils.GodUtil.insertGod

/**
 * @author AMiklo on 2016.10.10.
 */
databaseChangeLog() {

  changeSet(id: 'INSERT_GODS', author: 'amiklo') {
    comment "Initial insertion of gods"

    insertGod(delegate, [CODE: 'HORA', NAME: 'Hora', INFLUENCE: 'A halhatatlan tudás istene.'])
    insertGod(delegate, [CODE: 'SIFER', NAME: 'Sifer', INFLUENCE: 'A természeti elemek istene.'])
    insertGod(delegate, [CODE: 'GETON', NAME: 'Geton', INFLUENCE: 'A halál istene.'])
    insertGod(delegate, [CODE: 'RUNID', NAME: 'Runid', INFLUENCE: 'A fény istene.'])
    insertGod(delegate, [CODE: 'ALATE', NAME: 'Alate', INFLUENCE: 'A harc istennője.'])
    insertGod(delegate, [CODE: 'GINDON', NAME: 'Gindon', INFLUENCE: 'A megújulás istene.'])
    insertGod(delegate, [CODE: 'ATALIA', NAME: 'Atalia', INFLUENCE: 'Teremtő isten, a mindenség anyja.'])
  }

}
