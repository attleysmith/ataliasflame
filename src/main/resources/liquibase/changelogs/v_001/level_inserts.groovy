package liquibase.changelogs.v_001

import static liquibase.utils.v_001.LevelUtil.insertLevel

/**
 * @author AMiklo on 2016.10.14.
 */
databaseChangeLog() {

  changeSet(id: 'INSERT_LEVELS', author: 'amiklo') {
    comment "Initial insertion of levels"

    insertLevel(delegate, [LEVEL: 100])
    insertLevel(delegate, [LEVEL: 99, EXP_TO_LEVEL_UP: 310000000, NEXT_LEVEL: 100])
    insertLevel(delegate, [LEVEL: 98, EXP_TO_LEVEL_UP: 270000000, NEXT_LEVEL: 99])
    insertLevel(delegate, [LEVEL: 97, EXP_TO_LEVEL_UP: 235000000, NEXT_LEVEL: 98])
    insertLevel(delegate, [LEVEL: 96, EXP_TO_LEVEL_UP: 205000000, NEXT_LEVEL: 97])
    insertLevel(delegate, [LEVEL: 95, EXP_TO_LEVEL_UP: 178000000, NEXT_LEVEL: 96])
    insertLevel(delegate, [LEVEL: 94, EXP_TO_LEVEL_UP: 155000000, NEXT_LEVEL: 95])
    insertLevel(delegate, [LEVEL: 93, EXP_TO_LEVEL_UP: 135000000, NEXT_LEVEL: 94])
    insertLevel(delegate, [LEVEL: 92, EXP_TO_LEVEL_UP: 118000000, NEXT_LEVEL: 93])
    insertLevel(delegate, [LEVEL: 91, EXP_TO_LEVEL_UP: 102000000, NEXT_LEVEL: 92])
    insertLevel(delegate, [LEVEL: 90, EXP_TO_LEVEL_UP: 89000000, NEXT_LEVEL: 91])
    insertLevel(delegate, [LEVEL: 89, EXP_TO_LEVEL_UP: 77000000, NEXT_LEVEL: 90])
    insertLevel(delegate, [LEVEL: 88, EXP_TO_LEVEL_UP: 67000000, NEXT_LEVEL: 89])
    insertLevel(delegate, [LEVEL: 87, EXP_TO_LEVEL_UP: 58000000, NEXT_LEVEL: 88])
    insertLevel(delegate, [LEVEL: 86, EXP_TO_LEVEL_UP: 51000000, NEXT_LEVEL: 87])
    insertLevel(delegate, [LEVEL: 85, EXP_TO_LEVEL_UP: 44000000, NEXT_LEVEL: 86])
    insertLevel(delegate, [LEVEL: 84, EXP_TO_LEVEL_UP: 38000000, NEXT_LEVEL: 85])
    insertLevel(delegate, [LEVEL: 83, EXP_TO_LEVEL_UP: 33000000, NEXT_LEVEL: 84])
    insertLevel(delegate, [LEVEL: 82, EXP_TO_LEVEL_UP: 29000000, NEXT_LEVEL: 83])
    insertLevel(delegate, [LEVEL: 81, EXP_TO_LEVEL_UP: 25000000, NEXT_LEVEL: 82])
    insertLevel(delegate, [LEVEL: 80, EXP_TO_LEVEL_UP: 22000000, NEXT_LEVEL: 81])
    insertLevel(delegate, [LEVEL: 79, EXP_TO_LEVEL_UP: 19000000, NEXT_LEVEL: 80])
    insertLevel(delegate, [LEVEL: 78, EXP_TO_LEVEL_UP: 16500000, NEXT_LEVEL: 79])
    insertLevel(delegate, [LEVEL: 77, EXP_TO_LEVEL_UP: 14500000, NEXT_LEVEL: 78])
    insertLevel(delegate, [LEVEL: 76, EXP_TO_LEVEL_UP: 12500000, NEXT_LEVEL: 77])
    insertLevel(delegate, [LEVEL: 75, EXP_TO_LEVEL_UP: 11000000, NEXT_LEVEL: 76])
    insertLevel(delegate, [LEVEL: 74, EXP_TO_LEVEL_UP: 9500000, NEXT_LEVEL: 75])
    insertLevel(delegate, [LEVEL: 73, EXP_TO_LEVEL_UP: 8250000, NEXT_LEVEL: 74])
    insertLevel(delegate, [LEVEL: 72, EXP_TO_LEVEL_UP: 7200000, NEXT_LEVEL: 73])
    insertLevel(delegate, [LEVEL: 71, EXP_TO_LEVEL_UP: 6250000, NEXT_LEVEL: 72])
    insertLevel(delegate, [LEVEL: 70, EXP_TO_LEVEL_UP: 5500000, NEXT_LEVEL: 71])
    insertLevel(delegate, [LEVEL: 69, EXP_TO_LEVEL_UP: 4750000, NEXT_LEVEL: 70])
    insertLevel(delegate, [LEVEL: 68, EXP_TO_LEVEL_UP: 4100000, NEXT_LEVEL: 69])
    insertLevel(delegate, [LEVEL: 67, EXP_TO_LEVEL_UP: 3600000, NEXT_LEVEL: 68])
    insertLevel(delegate, [LEVEL: 66, EXP_TO_LEVEL_UP: 3100000, NEXT_LEVEL: 67])
    insertLevel(delegate, [LEVEL: 65, EXP_TO_LEVEL_UP: 2700000, NEXT_LEVEL: 66])
    insertLevel(delegate, [LEVEL: 64, EXP_TO_LEVEL_UP: 2350000, NEXT_LEVEL: 65])
    insertLevel(delegate, [LEVEL: 63, EXP_TO_LEVEL_UP: 2050000, NEXT_LEVEL: 64])
    insertLevel(delegate, [LEVEL: 62, EXP_TO_LEVEL_UP: 1770000, NEXT_LEVEL: 63])
    insertLevel(delegate, [LEVEL: 61, EXP_TO_LEVEL_UP: 1550000, NEXT_LEVEL: 62])
    insertLevel(delegate, [LEVEL: 60, EXP_TO_LEVEL_UP: 1340000, NEXT_LEVEL: 61])
    insertLevel(delegate, [LEVEL: 59, EXP_TO_LEVEL_UP: 1160000, NEXT_LEVEL: 60])
    insertLevel(delegate, [LEVEL: 58, EXP_TO_LEVEL_UP: 1015000, NEXT_LEVEL: 59])
    insertLevel(delegate, [LEVEL: 57, EXP_TO_LEVEL_UP: 880000, NEXT_LEVEL: 58])
    insertLevel(delegate, [LEVEL: 56, EXP_TO_LEVEL_UP: 770000, NEXT_LEVEL: 57])
    insertLevel(delegate, [LEVEL: 55, EXP_TO_LEVEL_UP: 670000, NEXT_LEVEL: 56])
    insertLevel(delegate, [LEVEL: 54, EXP_TO_LEVEL_UP: 580000, NEXT_LEVEL: 55])
    insertLevel(delegate, [LEVEL: 53, EXP_TO_LEVEL_UP: 505000, NEXT_LEVEL: 54])
    insertLevel(delegate, [LEVEL: 52, EXP_TO_LEVEL_UP: 440000, NEXT_LEVEL: 53])
    insertLevel(delegate, [LEVEL: 51, EXP_TO_LEVEL_UP: 380000, NEXT_LEVEL: 52])
    insertLevel(delegate, [LEVEL: 50, EXP_TO_LEVEL_UP: 330000, NEXT_LEVEL: 51])
    insertLevel(delegate, [LEVEL: 49, EXP_TO_LEVEL_UP: 290000, NEXT_LEVEL: 50])
    insertLevel(delegate, [LEVEL: 48, EXP_TO_LEVEL_UP: 250000, NEXT_LEVEL: 49])
    insertLevel(delegate, [LEVEL: 47, EXP_TO_LEVEL_UP: 218000, NEXT_LEVEL: 48])
    insertLevel(delegate, [LEVEL: 46, EXP_TO_LEVEL_UP: 190000, NEXT_LEVEL: 47])
    insertLevel(delegate, [LEVEL: 45, EXP_TO_LEVEL_UP: 165000, NEXT_LEVEL: 46])
    insertLevel(delegate, [LEVEL: 44, EXP_TO_LEVEL_UP: 143000, NEXT_LEVEL: 45])
    insertLevel(delegate, [LEVEL: 43, EXP_TO_LEVEL_UP: 125000, NEXT_LEVEL: 44])
    insertLevel(delegate, [LEVEL: 42, EXP_TO_LEVEL_UP: 108000, NEXT_LEVEL: 43])
    insertLevel(delegate, [LEVEL: 41, EXP_TO_LEVEL_UP: 94000, NEXT_LEVEL: 42])
    insertLevel(delegate, [LEVEL: 40, EXP_TO_LEVEL_UP: 81500, NEXT_LEVEL: 41])
    insertLevel(delegate, [LEVEL: 39, EXP_TO_LEVEL_UP: 71000, NEXT_LEVEL: 40])
    insertLevel(delegate, [LEVEL: 38, EXP_TO_LEVEL_UP: 61500, NEXT_LEVEL: 39])
    insertLevel(delegate, [LEVEL: 37, EXP_TO_LEVEL_UP: 53500, NEXT_LEVEL: 38])
    insertLevel(delegate, [LEVEL: 36, EXP_TO_LEVEL_UP: 46500, NEXT_LEVEL: 37])
    insertLevel(delegate, [LEVEL: 35, EXP_TO_LEVEL_UP: 40500, NEXT_LEVEL: 36])
    insertLevel(delegate, [LEVEL: 34, EXP_TO_LEVEL_UP: 35000, NEXT_LEVEL: 35])
    insertLevel(delegate, [LEVEL: 33, EXP_TO_LEVEL_UP: 30500, NEXT_LEVEL: 34])
    insertLevel(delegate, [LEVEL: 32, EXP_TO_LEVEL_UP: 26500, NEXT_LEVEL: 33])
    insertLevel(delegate, [LEVEL: 31, EXP_TO_LEVEL_UP: 23000, NEXT_LEVEL: 32])
    insertLevel(delegate, [LEVEL: 30, EXP_TO_LEVEL_UP: 20000, NEXT_LEVEL: 31])
    insertLevel(delegate, [LEVEL: 29, EXP_TO_LEVEL_UP: 17250, NEXT_LEVEL: 30])
    insertLevel(delegate, [LEVEL: 28, EXP_TO_LEVEL_UP: 15000, NEXT_LEVEL: 29])
    insertLevel(delegate, [LEVEL: 27, EXP_TO_LEVEL_UP: 13000, NEXT_LEVEL: 28])
    insertLevel(delegate, [LEVEL: 26, EXP_TO_LEVEL_UP: 11200, NEXT_LEVEL: 27])
    insertLevel(delegate, [LEVEL: 25, EXP_TO_LEVEL_UP: 9700, NEXT_LEVEL: 26])
    insertLevel(delegate, [LEVEL: 24, EXP_TO_LEVEL_UP: 8400, NEXT_LEVEL: 25])
    insertLevel(delegate, [LEVEL: 23, EXP_TO_LEVEL_UP: 7250, NEXT_LEVEL: 24])
    insertLevel(delegate, [LEVEL: 22, EXP_TO_LEVEL_UP: 6250, NEXT_LEVEL: 23])
    insertLevel(delegate, [LEVEL: 21, EXP_TO_LEVEL_UP: 5400, NEXT_LEVEL: 22])
    insertLevel(delegate, [LEVEL: 20, EXP_TO_LEVEL_UP: 4650, NEXT_LEVEL: 21])
    insertLevel(delegate, [LEVEL: 19, EXP_TO_LEVEL_UP: 4000, NEXT_LEVEL: 20])
    insertLevel(delegate, [LEVEL: 18, EXP_TO_LEVEL_UP: 3450, NEXT_LEVEL: 19])
    insertLevel(delegate, [LEVEL: 17, EXP_TO_LEVEL_UP: 2950, NEXT_LEVEL: 18])
    insertLevel(delegate, [LEVEL: 16, EXP_TO_LEVEL_UP: 2500, NEXT_LEVEL: 17])
    insertLevel(delegate, [LEVEL: 15, EXP_TO_LEVEL_UP: 2150, NEXT_LEVEL: 16])
    insertLevel(delegate, [LEVEL: 14, EXP_TO_LEVEL_UP: 1800, NEXT_LEVEL: 15])
    insertLevel(delegate, [LEVEL: 13, EXP_TO_LEVEL_UP: 1550, NEXT_LEVEL: 14])
    insertLevel(delegate, [LEVEL: 12, EXP_TO_LEVEL_UP: 1300, NEXT_LEVEL: 13])
    insertLevel(delegate, [LEVEL: 11, EXP_TO_LEVEL_UP: 1100, NEXT_LEVEL: 12])
    insertLevel(delegate, [LEVEL: 10, EXP_TO_LEVEL_UP: 900, NEXT_LEVEL: 11])
    insertLevel(delegate, [LEVEL: 9, EXP_TO_LEVEL_UP: 750, NEXT_LEVEL: 10])
    insertLevel(delegate, [LEVEL: 8, EXP_TO_LEVEL_UP: 600, NEXT_LEVEL: 9])
    insertLevel(delegate, [LEVEL: 7, EXP_TO_LEVEL_UP: 500, NEXT_LEVEL: 8])
    insertLevel(delegate, [LEVEL: 6, EXP_TO_LEVEL_UP: 400, NEXT_LEVEL: 7])
    insertLevel(delegate, [LEVEL: 5, EXP_TO_LEVEL_UP: 300, NEXT_LEVEL: 6])
    insertLevel(delegate, [LEVEL: 4, EXP_TO_LEVEL_UP: 225, NEXT_LEVEL: 5])
    insertLevel(delegate, [LEVEL: 3, EXP_TO_LEVEL_UP: 150, NEXT_LEVEL: 4])
    insertLevel(delegate, [LEVEL: 2, EXP_TO_LEVEL_UP: 100, NEXT_LEVEL: 3])
    insertLevel(delegate, [LEVEL: 1, EXP_TO_LEVEL_UP: 50, NEXT_LEVEL: 2])

  }

}
