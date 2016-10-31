package liquibase.changelogs.v_001

import static liquibase.utils.v_001.UserUtil.insertUser

/**
 * @author AMiklo on 2016.10.17.
 */
databaseChangeLog() {

  changeSet(id: 'INSERT_USERS', author: 'amiklo') {
    comment "Initial insertion of users"

    insertUser(delegate, [DISPLAY_NAME: 'Admin', USERNAME: 'admin', PASSWORD: 'admin', EMAIL: 'admin@asgames.hu'])

  }

}

