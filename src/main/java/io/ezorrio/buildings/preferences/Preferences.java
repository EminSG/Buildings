package io.ezorrio.buildings.preferences;

import io.ezorrio.buildings.db.DBHelper;

/**
 * Created by golde on 05.04.2017.
 */
public class Preferences {
    private static final int UNKNOWN_VALUE = 100;

    private static int searchCriteria = UNKNOWN_VALUE;
    private static int searchMoreOrLess = UNKNOWN_VALUE;
    private static int sortCriteria = UNKNOWN_VALUE;
    private static String importPath = DBHelper.DEFAULT_DB_PATH;

    private static boolean needExtendedInfo = false;

    public static void setNeedExtendedInfo(boolean value) {
        needExtendedInfo = value;
    }

    public static void setImportPath(String value) {
        importPath = value;
    }

    public static void setSearchCriteria(int value) {
        searchCriteria = value;
    }

    public static void setSearchMoreOrLess(int value) {
        searchMoreOrLess = value;
    }

    public static int getSearchCriteria() {
        return searchCriteria;
    }

    public static int getSearchMoreOrLess() {
        return searchMoreOrLess;
    }

    public static String getImportPath() {
        return importPath;
    }

    public static boolean isNeedExtendedInfo() {
        return needExtendedInfo;
    }

    public static boolean isUnknown(int value) {
        return value == UNKNOWN_VALUE;
    }

    public static void setSortCriteria(int value) {
        sortCriteria = value;
    }

    public static int getSortCriteria() {
        return sortCriteria;
    }
}
